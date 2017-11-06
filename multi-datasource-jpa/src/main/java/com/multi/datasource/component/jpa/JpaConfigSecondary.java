package com.multi.datasource.component.jpa;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		transactionManagerRef = "transactionManagerSecondary",
		entityManagerFactoryRef = "entityManagerFactorySecondary",
		basePackages = "com.multi.datasource.dao.bean.secondary")//设置数据访问对象(Repository)所在位置
public class JpaConfigSecondary {

	@Autowired @Qualifier("dataSourceSecondary") private DataSource dataSourceSecondary;
	@Autowired JpaProperties jpaProperties;
	
	/** 实体类 */
	@Bean(name = "entityManagerFactorySecondary")
	public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary(EntityManagerFactoryBuilder builder) {
		return builder
					.dataSource(dataSourceSecondary)
					.properties(this.getVendorProperties(dataSourceSecondary))
					.packages("com.multi.datasource.dao.bean.secondary")//设置实体类(Entity)所在位置
					.persistenceUnit("secondaryPersistenceUnit")
					.build();
	}
	
	/** 实体类管理配置 */
	@Bean(name = "entityManagerSecondary")
	public EntityManager entityManagerSecondary(EntityManagerFactoryBuilder builder) {
		return this.entityManagerFactorySecondary(builder).getObject().createEntityManager();
	}
	
	/** JPA事务管理配置 */
	@Bean(name = "transactionManagerSecondary")
	public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(this.entityManagerFactorySecondary(builder).getObject());
	}
	
	private Map<String, String> getVendorProperties(DataSource dataSource) {
		return jpaProperties.getHibernateProperties(dataSource);
	}
	
}
