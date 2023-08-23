//package com.core.module.test.dao;
//
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class TestDaoImpl  implements TestDao {
//	@Autowired
//	private SqlSession sql;
//
//	@Override
//	public String getTestMysql() {
//		String test=sql.selectOne("test.getTestMysql");
//		return test;
//	}
//
//}
