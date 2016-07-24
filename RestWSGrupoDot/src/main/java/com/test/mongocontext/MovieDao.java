package com.test.mongocontext;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.test.model.Movies;

/**
 * interface that take the data incoming by the mongo database
 * 
 * @author NESTOR version 1.0 23/07/2016
 */


public interface MovieDao extends CrudRepository<Movies, Long> {
	@Query("{'name' : ?0}")
	public Iterable<Movies> searchByName(String name);

}
