package com.multi.sessionFactory.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.github.pagehelper.PageHelper;

/**
 * MyBatis基础配置
 * 默认sessionFactory配置
 */
@Configuration
@EnableTransactionManagement
public class SqlSessionFactoryConfig implements TransactionManagementConfigurer {
	
	@Autowired @Qualifier("dataSourcePrimary") private DataSource dataSourcePrimary;
	@Autowired @Qualifier("dataSourceSecondary") private DataSource dataSourceSecondary;
	
	@Primary
    @Lazy
    @Bean(name = "sqlSessionFactoryPrimary")
    public SqlSessionFactory sqlSessionFactoryBeanPrimary() throws IOException {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSourcePrimary);
        bean.setTypeAliasesPackage("com.multi.sessionFactory.entity.primary");
        {
            org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
            configuration.setMapUnderscoreToCamelCase(true);
            configuration.setUseGeneratedKeys(true);
            configuration.setUseActualParamName(false);
            configuration.setLogImpl(StdOutImpl.class);
            bean.setConfiguration(configuration);
        }

        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "false");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加插件
        bean.setPlugins(new Interceptor[]{pageHelper});

        try {
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Primary
    @Bean(name = "sqlSessionTemplatePrimary")
    public SqlSessionTemplate sqlSessionTemplatePrimary(@Autowired @Qualifier("sqlSessionFactoryPrimary") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    @Lazy
    @Bean(name = "sqlSessionFactorySecondary")
    public SqlSessionFactory sqlSessionFactoryBeanSecondary() throws IOException {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSourceSecondary);
        bean.setTypeAliasesPackage("com.multi.sessionFactory.entity.secondary");
        {
            org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
            configuration.setMapUnderscoreToCamelCase(true);
            configuration.setUseGeneratedKeys(true);
            configuration.setUseActualParamName(false);
            configuration.setLogImpl(StdOutImpl.class);
            bean.setConfiguration(configuration);
        }

        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "false");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加插件
        bean.setPlugins(new Interceptor[]{pageHelper});

        try {
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "sqlSessionTemplateSecondary")
    public SqlSessionTemplate sqlSessionTemplateSecondary(@Autowired @Qualifier("sqlSessionTemplateSecondary") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    @Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
    	return new DataSourceTransactionManager(dataSourcePrimary);
	}

}
