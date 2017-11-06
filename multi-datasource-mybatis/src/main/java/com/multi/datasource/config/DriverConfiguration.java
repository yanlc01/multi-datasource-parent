package com.multi.datasource.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.multi.datasource.bean.MysqlDataSourceLocal;
import com.multi.datasource.bean.MysqlDataSourceRemote;
import com.multi.datasource.component.MultiDataSource;

@Configuration
public class DriverConfiguration {

	@Bean(name = "driverManagerDataSourceLocal")
	public DriverManagerDataSource generateDataSourceMysqlLocal(@Autowired MysqlDataSourceLocal local) {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName(local.getDriver());
		driverManagerDataSource.setUrl(local.getUrl());
		driverManagerDataSource.setUsername(local.getUsername());
		driverManagerDataSource.setPassword(local.getPassword());
		
		return driverManagerDataSource;
	}
	
	@Bean(name = "driverManagerDataSourceRemote")
	public DriverManagerDataSource generateDataSourceMysqlRemote(@Autowired MysqlDataSourceRemote remote) {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName(remote.getDriver());
		driverManagerDataSource.setUrl(remote.getUrl());
		driverManagerDataSource.setUsername(remote.getUsername());
		driverManagerDataSource.setPassword(remote.getPassword());
		
		return driverManagerDataSource;
	}
	
	@Bean
	public MultiDataSource generateMultiDataSource(@Autowired DriverManagerDataSource driverManagerDataSourceLocal,
			@Autowired DriverManagerDataSource driverManagerDataSourceRemote) {
		MultiDataSource multiDataSource = new MultiDataSource();
		multiDataSource.setDefaultTargetDataSource(driverManagerDataSourceLocal);
		
		Map<Object, Object> dataSourceMap = new HashMap<>();
		dataSourceMap.put("mysqlDataSourceLocal", driverManagerDataSourceLocal);
		dataSourceMap.put("mysqlDataSourceRemote", driverManagerDataSourceRemote);
		multiDataSource.setTargetDataSources(dataSourceMap);
		return multiDataSource;
	}
	
}
