package com.multi.datasource.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.multi.datasource.bean.MysqlDataSourcePrimary;
import com.multi.datasource.bean.MysqlDataSourceSecondary;
import com.multi.datasource.component.MultiDataSource;

/**
 * DriverManagerDataSource每次访问数据库都会新建一个数据库连接，不是数据库连接池
 * 可以使用数据库连接池：
 * 		C3P0：支持最长连接时间，超过时间的连接会自动断开
 * 				c3p0-0.9.2.1.jar mchange-commons-java-0.2.3.4.jar
 * 		DBCP：支持最大连接数，超过连接数，会断开所有连接
 * 				common-dbcp.jar,common-pool.jar,common-collections.jar
 */
@Configuration
public class DriverConfiguration {

	@Bean(name = "driverManagerDataSourcePrimary")
	@Primary
	public DriverManagerDataSource generateDataSourceMysqlPrimary(@Autowired MysqlDataSourcePrimary primary) {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName(primary.getDriverClassName());
		driverManagerDataSource.setUrl(primary.getUrl());
		driverManagerDataSource.setUsername(primary.getUsername());
		driverManagerDataSource.setPassword(primary.getPassword());
		
		return driverManagerDataSource;
	}
	
	@Bean(name = "driverManagerDataSourceSecondary")
	public DriverManagerDataSource generateDataSourceMysqlSecondary(@Autowired MysqlDataSourceSecondary secondary) {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName(secondary.getDriverClassName());
		driverManagerDataSource.setUrl(secondary.getUrl());
		driverManagerDataSource.setUsername(secondary.getUsername());
		driverManagerDataSource.setPassword(secondary.getPassword());
		
		return driverManagerDataSource;
	}
	
	@Bean(name = "multiDataSource")
	public MultiDataSource generateMultiDataSource(@Autowired @Qualifier("driverManagerDataSourcePrimary") DriverManagerDataSource driverManagerDataSourcePrimary,
			@Autowired @Qualifier("driverManagerDataSourceSecondary") DriverManagerDataSource driverManagerDataSourceSecondary) {
		MultiDataSource multiDataSource = new MultiDataSource();
		multiDataSource.setDefaultTargetDataSource(driverManagerDataSourcePrimary);
		
		Map<Object, Object> dataSourceMap = new HashMap<>();
		dataSourceMap.put("dataSourcePrimary", driverManagerDataSourcePrimary);
		dataSourceMap.put("dataSourceSecondary", driverManagerDataSourceSecondary);
		multiDataSource.setTargetDataSources(dataSourceMap);
		return multiDataSource;
	}
	
}
