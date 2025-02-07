package com.spring.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.course.entities.Order;
import com.spring.course.services.OrderService;

/* o @RestController simplesmente retorna
 *  o objeto e os dados do objeto, são gravados
 *   diretamente na resposta HTTP como JSON ou XML.
 *   
 *  E o @ResquestMapping serve para informar uma
 *  solicitação do seu HTTP com o valor informardo.
 *  
 *  *SE NÃO FOR INFORMADO RETORNARA TODOS OBJETOS
 *  DA CLASSE*
 */
@RestController
@RequestMapping(value = "/orders")
public class OrderResource {
    
	@Autowired
	private OrderService service;
	
	/* @GetMapping serve para fazer a requisição do metodo
	 * no HTTP informado no @RequestMapping
	 */
	@GetMapping
	public ResponseEntity<List<Order>> findAll() {
		List<Order> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	/* @GetMapping informando um valor serve para fazer a requisição do metodo
	 * no HTTP informado do GetMapping
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Integer id) {
		Order u = service.findById(id);
		return ResponseEntity.ok().body(u);
	}
	
}
