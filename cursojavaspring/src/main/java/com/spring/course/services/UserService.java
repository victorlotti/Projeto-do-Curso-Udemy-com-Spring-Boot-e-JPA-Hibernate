package com.spring.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.spring.course.entities.User;
import com.spring.course.repositories.UserRepository;
import com.spring.course.resources.exception.ResourceExceptionHandler;
import com.spring.course.services.exception.DatabaseException;
import com.spring.course.services.exception.ResourceNotFoundException;

@Service
public class UserService {
    
	@Autowired
	private UserRepository repository;
	
    public List<User> findAll() {
    	return repository.findAll();
    }
	
    public User findById(Integer id) {
    	Optional<User> obj = repository.findById(id);
    	return obj.orElseThrow(() -> new ResourceNotFoundException(id));
   }
   
    
    public User insert(User user) {
      return repository.save(user);
    }
    
    public void delete(Integer id) {
    	try {
    	  repository.deleteById(id);
    	}
    	catch (EmptyResultDataAccessException e) {
    		throw new ResourceNotFoundException(id);
    	}
    	catch (DataIntegrityViolationException e) {
    		throw new DatabaseException(e.getMessage());
    	}
    	
    }
    
    public User update(Integer id, User user) {
    	Optional<User> entity = repository.findById(id);
        updateData(entity.get(), user);
    	return repository.save(entity.get());
    }
    
    private void updateData(User entity, User obj) {
    	entity.setName(obj.getName());
    	entity.setEmail(obj.getEmail());
    	entity.setPhone(obj.getPhone());
    }
}
