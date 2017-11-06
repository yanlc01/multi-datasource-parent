package com.multi.datasource.component.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class JdbcConfig {

	@Bean(name = "jdbcTemplatePrimary")
	public JdbcTemplate JdbcTemplatePrimary(@Qualifier("dataSourcePrimary") DataSource dataSourcePrimary) {
		return new JdbcTemplate(dataSourcePrimary);
	}
	
	@Bean(name = "jdbcTemplateSecondary")
	public JdbcTemplate JdbcTemplateSecondary(@Qualifier("dataSourceSecondary") DataSource dataSourceSecondary) {
		return new JdbcTemplate(dataSourceSecondary);
	}
	
}
