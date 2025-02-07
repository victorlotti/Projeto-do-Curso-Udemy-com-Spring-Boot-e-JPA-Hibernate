package com.spring.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.course.entities.User;
import com.spring.course.services.UserService;

/* o @RestController simplesmente retorna
 *  o objeto e os dados do objeto
 *  diretamente na resposta HTTP como JSON ou XML.
 *   
 *  E o @ResquestMapping serve para informar uma
 *  solicitação no seu HTTP com o valor informardo.
 *  
 *  *SE NÃO FOR INFORMADO RETORNARA TODOS OBJETOS
 *  DA CLASSE*
 */
@RestController
@RequestMapping(value = "/users")
public class UserResource {
    
	@Autowired
	private UserService service;
	
	/* @GetMapping serve para fazer a requisição do metodo
	 * no HTTP informado no @RequestMapping
	 */
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	/* @GetMapping informando um valor serve para fazer a requisição do metodo
	 * no HTTP informado do GetMapping
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Integer id) {
		User u = service.findById(id);
		return ResponseEntity.ok().body(u);
	}
	
	/* @PostMapping é utilizado para postar uma requisição body de um
	 * metodo no HTTP
	 */
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User user) {
		user = service.insert(user);
		/* Esse metodo URI declarado abaixo é para criar um location do
		 * HTTP de um requisição body inserida, com o novo link da url.
		 */
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
	
	/* @DeleteMapping passando de argumento esse valor,
	 * é para deletar um usuario no banco de dados
	 * é necessario a anotação @PathVariable o id
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		  service.delete(id);
		  return ResponseEntity.noContent().build();
	}
	
	/* @PutMapping passando de argumento um valor,
	 * de id é para atualizar um usuario no banco de dados
	 * é necessario a anotação @PathVariable com id
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody User obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
}
