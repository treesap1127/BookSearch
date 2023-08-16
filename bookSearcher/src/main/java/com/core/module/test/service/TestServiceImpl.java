package com.core.module.test.service;

import org.springframework.stereotype.Service;

import com.core.module.test.dao.TestDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
	private final TestDao testDao;
	
	@Override
	public String getTestMysql() {
		return testDao.getTestMysql();
	}

}
