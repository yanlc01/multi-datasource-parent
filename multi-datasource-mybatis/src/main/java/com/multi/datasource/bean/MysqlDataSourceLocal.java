package com.multi.datasource.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "mysqlLocal")
@Data
public class MysqlDataSourceLocal {

	private String driver;
	private String url;
	private String username;
	private String password;
	
}
