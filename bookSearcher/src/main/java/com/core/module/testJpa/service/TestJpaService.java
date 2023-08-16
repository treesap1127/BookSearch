package com.core.module.testJpa.service;

import java.util.List;

import com.core.module.testJpa.vo.TestJpaVo;

public interface TestJpaService {

	List<TestJpaVo> getTestJpaVoList();

	TestJpaVo selectJpa(int i);

	void insertTestJpa(TestJpaVo testJpaVo);

	void updateTestJpa(int id, TestJpaVo testJpaVo);

}
