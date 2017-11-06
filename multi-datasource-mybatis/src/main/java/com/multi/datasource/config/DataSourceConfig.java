package com.multi.datasource.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

//@Configuration
public class DataSourceConfig {

//	@Bean(name = "dataSourcePrimary")
//	@ConfigurationProperties(prefix = "mysqlPrimary")
//	@Primary//需要该注释区分主数据源
//	public DataSource generateDataSourcePrimary() {
//		return DataSourceBuilder.create().build();
//	}
//	
//	@Bean(name = "dataSourceSecondary")
//	@ConfigurationProperties(prefix = "mysqlSecondary")
//	public DataSource generateDataSourceSecondary() {
//		return DataSourceBuilder.create().build();
//	}
	
}
