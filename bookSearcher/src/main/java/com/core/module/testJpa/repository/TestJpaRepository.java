package com.core.module.testJpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.core.module.testJpa.vo.TestJpaVo;

@Repository
public interface TestJpaRepository extends JpaRepository<TestJpaVo, Integer> {
    TestJpaVo findByTest(int i);
}
