package com.example.comment.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan({"com.example.comment.repo"})
@PropertySource("classpath:/config/dbconfig.properties")
public class CommentConfig {
	
	private static Logger logger = LoggerFactory.getLogger(CommentConfig.class);
	
	@Bean
	public DataSource dataSource(
				@Value("${db.driverClassName}") String driverClassName,
				@Value("${db.url}") String url,
				@Value("${db.username}") String username,
				@Value("${db.password}") String password
			){
		logger.trace("url: {}", url);
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driverClassName);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		return ds;
	}
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource ds){
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(ds);
		String loc = "mybatis/mybatis-config.xml";
		bean.setConfigLocation(new ClassPathResource(loc));
		return bean;
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactoryBean sfb) throws Exception{
		SqlSessionTemplate template = new SqlSessionTemplate(sfb.getObject());
		return template;
	}
}
