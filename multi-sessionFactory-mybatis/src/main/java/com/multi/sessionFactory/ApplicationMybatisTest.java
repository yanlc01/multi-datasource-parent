package com.multi.sessionFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.multi.sessionFactory.component.MultiDataSource;
import com.multi.sessionFactory.component.MultiSessionFactory;
import com.multi.sessionFactory.dao.bean.User;
import com.multi.sessionFactory.dao.mapper.UserMapper;

/**
 * 不同sessionFactory，每个sessionFactory都对应一个数据源（DataSource）
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationMybatisTest {

	@Autowired @Qualifier("multiDataSource") public MultiDataSource multiDataSource;
	@Autowired @Qualifier("multiSessionFactory") public MultiSessionFactory multiSessionFactory;
	@Autowired private UserMapper userMapper;
	
	@Before
	public void setup() {
		
	}
	
	@Test
	public void test() {
		multiDataSource.setDataSource("dataSourcePrimary");
		User user = userMapper.selectByPrimaryKey(1L);
		System.out.println(user.getName());
		
		multiDataSource.setDataSource("dataSourceSecondary");
		user = userMapper.selectByPrimaryKey(1L);
		System.out.println(user.getName());
	}
	
}
