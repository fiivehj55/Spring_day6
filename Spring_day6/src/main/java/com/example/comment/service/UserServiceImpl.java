package com.example.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.comment.dto.Comment;
import com.example.comment.dto.User;
import com.example.comment.repo.CommentRepo;
import com.example.comment.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo urepo;
	
	@Autowired
	CommentRepo crepo;

	@Override
	@Transactional // 트랜잭션을 쓸곳을 지정
	public int join(User user) {
		// 사용자 정보 추가
		int result = urepo.insert(user);
		// Comment 공지 추가
		//int i = 1/0; // 트랜잭션 확인을 위한 설정
		Comment comment = new Comment(0, "admin", user.getUserId() + "님이 가입했습니다.");
		result = crepo.insert(comment);
		return result;
	}

}
