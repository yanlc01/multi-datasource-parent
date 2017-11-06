package com.multi.datasource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.multi.datasource.dao.bean.primary.User;
import com.multi.datasource.dao.bean.primary.UserRepository;
import com.multi.datasource.dao.bean.secondary.User2;
import com.multi.datasource.dao.bean.secondary.User2Repository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationJpaTests {

	@Autowired private UserRepository userRepository;
	
	@Autowired private User2Repository user2Repository;
	
	@Before
	public void setup() {
		userRepository.deleteAll();
		user2Repository.deleteAll();
	}
	
	@Test
	public void test() {
		userRepository.save(new User("小李", 1));
		userRepository.save(new User("小张", 2));
		Assert.assertEquals(2, userRepository.findAll().size());
		
		user2Repository.save(new User2("小狗", 3));
		user2Repository.save(new User2("小强", 4));
		Assert.assertEquals(2, user2Repository.findAll().size());
	}
	
}
