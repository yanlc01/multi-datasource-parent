package com.multi.datasource.dao.bean.secondary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "USER")
public class User2 {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "AGE")
	private Integer age;
	
	public User2() {}
	
	public User2(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
}
