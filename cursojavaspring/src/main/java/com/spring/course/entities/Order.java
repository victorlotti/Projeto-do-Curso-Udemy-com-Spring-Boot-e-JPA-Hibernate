package com.spring.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.spring.course.entities.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/* O @Entity é usado para mostrar
 * que será uma entidade do jpa
 * 
 * E o @Table é usado para criar uma tabela
 * com o nome informado no banco de dados
 */
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/* o @Id é usado para criar um Id
	 * na tabela 
	 * 
	 * E o @GeneratedValue passando esse argumento
	 * é usado para criar as tabelas com esses atributos
	 * sendo o conteudo da tabela os objeto instanciados 
	 * nessas variaveis
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Instant moment;
	
	private Integer orderStatus;
	
	/* @JoinColumn indica que a entidade é a responsavel pelo
	   relacionamento
	 */
	@ManyToOne
	@JoinColumn(name = "client_id")
	private User client;
	
	@Fetch(FetchMode.JOIN)
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	/* @OneToOne é uma associação um para um
	 * E o metodo cascade = CascadeType.ALL
	 * é o metodo para mapear duas entidades com o
	 * mesmo ID (ex: se o pedido for id 5 o pagamento será também)
	 */
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;
	
	public Order() {
	}

	public Order(Integer id, Instant moment, OrderStatus orderStatus, User client) {
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	public Set<OrderItem> getItems() {
		return items;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null) {
		this.orderStatus = orderStatus.getCode();
		}
	}
	
	public double getTotal() {
		double sum = 0.0;
		for(OrderItem or : items) {
			sum = sum + or.getSubTotal();
		}
		return sum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
