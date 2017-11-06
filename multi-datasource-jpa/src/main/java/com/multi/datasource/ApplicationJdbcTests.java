package com.multi.datasource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationJdbcTests {
	
	@Autowired @Qualifier("jdbcTemplatePrimary") private JdbcTemplate jdbcTemplatePrimary;
	@Autowired @Qualifier("jdbcTemplateSecondary") private JdbcTemplate jdbcTemplateSecondary;
	
	@Before
	public void setup() {
		jdbcTemplatePrimary.update("DELETE FROM USER");
		jdbcTemplateSecondary.update("DELETE FROM USER");
	}
	
	@Test
	public void test() {
		jdbcTemplatePrimary.update("INSERT INTO USER VALUE(?, ?, ?)", 1, "张三", 11);
		jdbcTemplatePrimary.update("INSERT INTO USER VALUE(?, ?, ?)", 2, "李四", 22);
		
		jdbcTemplateSecondary.update("INSERT INTO USER VALUE(?, ?, ?)", 1, "张三", 11);
		
		Assert.assertEquals("2", jdbcTemplatePrimary.queryForObject("SELECT COUNT(*) FROM USER", String.class));
		Assert.assertEquals("1", jdbcTemplateSecondary.queryForObject("SELECT COUNT(*) FROM USER", String.class));
	}
	
}
