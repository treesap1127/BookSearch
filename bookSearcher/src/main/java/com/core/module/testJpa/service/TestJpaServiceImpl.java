//package com.core.module.testJpa.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.stereotype.Service;
//
//import com.core.module.testJpa.repository.TestJpaRepository;
//import com.core.module.testJpa.vo.TestJpaVo;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class TestJpaServiceImpl implements TestJpaService {
//	private final TestJpaRepository jpaRepository;
////	https://docs.spring.io/spring-data/jpa/docs/2.3.3.RELEASE/reference/html/#jpa.repositories
//	@Override
//	public TestJpaVo selectJpa(int test) {
//		TestJpaVo jpa = jpaRepository.findByTest(test);
//		return jpa;
//	}
//
//    @Override
//    public void insertTestJpa(TestJpaVo testJpaVo) {
//    	jpaRepository.save(testJpaVo);
//    }
//
//    @Override
//    public void updateTestJpa(int id, TestJpaVo testJpaVo) {
//        Optional<TestJpaVo> optionalJpa = jpaRepository.findById(id);
//        if (optionalJpa.isPresent()) {
//            TestJpaVo jpa = optionalJpa.get();
//            jpa.setTest2(testJpaVo.getTest2());
//            jpaRepository.save(jpa);
//        }
//    }
//
//    @Override
//    public List<TestJpaVo> getTestJpaVoList() {
//        return jpaRepository.findAll();
//    }
//}
