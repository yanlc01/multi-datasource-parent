package com.multi.datasource.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "mysqlSecondary")
@Data
public class MysqlDataSourceSecondary {

	private String driverClassName;
	private String url;
	private String username;
	private String password;
	
}
