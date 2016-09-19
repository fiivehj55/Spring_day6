package com.example.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.comment.config.CommentConfig;
import com.example.comment.dto.User;
import com.example.comment.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=CommentConfig.class)
@Sql({"/dbschema/comment_schema.sql","/dbschema/comment_data.sql"}) // TESTING 할때만 쓴다
public class UserServiceTest {
	
	private static Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
	
	@Autowired
	UserService service;
	
	//@Test
	public void testService() {
		assertThat(service, is(notNullValue()));
	}

	@Test
	public void testInsert() {
		User user = new User("jang","1234");
		int result = service.join(user);
		logger.trace("User: {}", user);
		assertThat(result, is(1));
	}

}
