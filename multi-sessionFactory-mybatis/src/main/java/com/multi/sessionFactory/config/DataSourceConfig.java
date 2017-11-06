package com.multi.sessionFactory.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.multi.sessionFactory.component.MultiDataSource;

@Configuration
public class DataSourceConfig {

	@Bean(name = "dataSourcePrimary")
	@ConfigurationProperties(prefix = "mysqlPrimary")
	@Primary//需要该注释区分主数据源
	public DataSource generateDataSourcePrimary() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "dataSourceSecondary")
	@ConfigurationProperties(prefix = "mysqlSecondary")
	public DataSource generateDataSourceSecondary() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "multiDataSource")
	public MultiDataSource generateMultiDataSource(@Autowired @Qualifier("dataSourcePrimary") DataSource dataSourcePrimary,
			@Autowired @Qualifier("dataSourceSecondary") DataSource dataSourceSecondary) {
		MultiDataSource multiDataSource = new MultiDataSource();
		multiDataSource.setDefaultTargetDataSource(dataSourcePrimary);
		
		Map<Object, Object> dataSourceMap = new HashMap<>();
		dataSourceMap.put("dataSourcePrimary", dataSourcePrimary);
		dataSourceMap.put("dataSourceSecondary", dataSourceSecondary);
		multiDataSource.setTargetDataSources(dataSourceMap);
		return multiDataSource;
	}
	
}
