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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		transactionManagerRef = "transactionManagerPrimary",
		entityManagerFactoryRef = "entityManagerFactoryPrimary",
		basePackages = "com.multi.datasource.dao.bean.primary")//设置数据访问对象(Repository)所在位置
public class JpaConfigPrimary {

	@Autowired @Qualifier("dataSourcePrimary") private DataSource dataSourcePrimary;
	@Autowired JpaProperties jpaProperties;
	
	/** 实体类 */
	@Primary
	@Bean(name = "entityManagerFactoryPrimary")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {
		return builder
					.dataSource(dataSourcePrimary)
					.properties(this.getVendorProperties(dataSourcePrimary))
					.packages("com.multi.datasource.dao.bean.primary")//设置实体类(Entity)所在位置
					.persistenceUnit("primaryPersistenceUnit")
					.build();
	}
	
	/** 实体类管理配置 */
	@Primary
	@Bean(name = "entityManagerPrimary")
	public EntityManager entityManagerPrimary(EntityManagerFactoryBuilder builder) {
		return this.entityManagerFactoryPrimary(builder).getObject().createEntityManager();
	}
	
	/** JPA事务管理配置 */
	@Primary
	@Bean(name = "transactionManagerPrimary")
	public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(this.entityManagerFactoryPrimary(builder).getObject());
	}
	
	private Map<String, String> getVendorProperties(DataSource dataSource) {
		return jpaProperties.getHibernateProperties(dataSource);
	}
	
}
