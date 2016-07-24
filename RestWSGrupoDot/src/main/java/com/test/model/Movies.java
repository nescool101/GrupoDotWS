package com.test.model;

import java.util.List;

import javax.persistence.Column;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import com.test.mongocontext.MovieDao;
import com.test.utilities.Utilities;

/**
 * Class that implements a POJO with the data of the movies
 * 
 * @author NESTOR version 1.0 23/07/2016
 */
@Document(collection = "movies")
public class Movies {
	@Id
	private Long id;

	private String name;

	private String code;

	private String location;

	private String desc;

	public static List<Movies> movies;


	static {
		MovieDao festivitieContext = Utilities.getBeanContext();
		movies = (List<Movies>) festivitieContext.findAll();
	}


	/**
	 * Constructor of class Initialized all the parameters of the Object
	 * Movies
	 * 
	 * @param id
	 * @param iniDate
	 * @param finDate
	 * @param location
	 * @param desc
	 */
	@PersistenceConstructor
	public Movies(Long id, String name, String code, String location, String desc) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.location = location;
		this.desc = desc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return "{id:" + id + ", name:" + desc + ", place:" + location + ", start:" + name + ", end:" + desc + "}";
	}
}
