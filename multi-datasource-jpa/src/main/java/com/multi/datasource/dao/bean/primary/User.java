package com.multi.datasource.dao.bean.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "AGE")
	private Integer age;
	
	public User() {}
	
	public User(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
}
