//package com.core.module.testJpa.web;
//
//import java.util.List;
//
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.core.module.testJpa.service.TestJpaService;
//import com.core.module.testJpa.vo.TestJpaVo;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/jpaTest")
//public class TestJpaController {
//	private final TestJpaService jpaService;
//
//	@RequestMapping("/selectJpa")
//	public String selectJpa() {
//		TestJpaVo test = jpaService.selectJpa(1);
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//	        String jsonData = objectMapper.writeValueAsString(test);
//	        return jsonData;
//	    } catch (JsonProcessingException e) {
//	        e.printStackTrace();
//	        return null;
//	    }
//	}
//
//    @RequestMapping("/selectListJpa")
//    public List<TestJpaVo> getTestJpaVoList() {
//        return jpaService.getTestJpaVoList();
//    }
//
//    @PostMapping("/insertJpa")
//    public void insertTestJpa(@ModelAttribute TestJpaVo testJpaVo) {
//    	jpaService.insertTestJpa(testJpaVo);
//    }
//
//    @PostMapping("/{id}/updateJpa")
//    public void updateTestJpa(@PathVariable int id,@ModelAttribute TestJpaVo testJpaVo) {
//    	jpaService.updateTestJpa(id, testJpaVo);
//    }
//
//}
