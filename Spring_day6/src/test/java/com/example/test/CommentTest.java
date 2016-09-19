package com.example.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.comment.config.CommentConfig;
import com.example.comment.dto.Comment;
import com.example.comment.repo.CommentRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=CommentConfig.class)
public class CommentTest {
	
	private static Logger logger = LoggerFactory.getLogger(CommentTest.class);
	
	@Autowired
	DataSource ds;
	
	@Autowired
	CommentRepo repo;
	
	@Autowired
	SqlSessionTemplate template;

	@Test
	public void testComment() {	
		assertThat(repo, is(notNullValue()));
	}
	
	@Test
	public void testDataSource() throws SQLException {
		logger.trace("connection: {}", ds.getConnection());
		assertThat(ds, is(notNullValue()));
		Connection con = ds.getConnection();
		assertThat(con, is(notNullValue()));
	}
	
	@Test
	public void testTemplate() {
		assertThat(template, is(notNullValue()));
	}
	
	@Test
	public void testInsert() {
		Comment comment = new Comment(0, "hong", "hello1");
		int result = repo.insert(comment);
		logger.trace("commentNo: {}", comment.getCommentNo());
		assertThat(result, is(1));
	}

}
