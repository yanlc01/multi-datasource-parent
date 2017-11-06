package com.multi.datasource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.multi.datasource.component.MultiDataSource;
import com.multi.datasource.dao.bean.User;
import com.multi.datasource.dao.mapper.UserMapper;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationMybatisTest {

	@Autowired @Qualifier("multiDataSource") public MultiDataSource multiDataSource;
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
