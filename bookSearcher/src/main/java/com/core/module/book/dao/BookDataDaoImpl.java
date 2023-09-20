package com.core.module.book.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BookDataDaoImpl<T> implements BookDataDao<T> {
	@Autowired
	private SqlSession sql;

	

}

