package com.spring.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/* O @Entity é usado para mostrar
 * que será uma entidade do jpa
 * 
 * E o @Table é usado para criar uma tabela
 * com o nome informado no banco de dados
 */
@Entity
@Table(name = "tb_category")
public class Category implements Serializable {
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
	private String name;
	
	/* O uso do ManyToMany com o mappedBy
	 * é pelo falto de ser uma assosiação bidirecional
	 * e o mapped é pra relacionar a classe pai dessa assosiação
	 */
	@ManyToMany(mappedBy = "categories")
	@JsonIgnore
	private Set<Product> products = new HashSet<>();
	
	public Category() {
	}

	public Category(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Product> getProducts() {
		return products;
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
		Category other = (Category) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

	

		
	
	
	
}
