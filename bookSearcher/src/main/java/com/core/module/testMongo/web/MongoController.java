package com.core.module.testMongo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.module.testMongo.service.MongoService;
import com.core.module.testMongo.vo.MongoVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mongoTest")
public class MongoController {
	private final MongoService mongoService;
	
	@RequestMapping
	public MongoVo mongoTest() {
		MongoVo mongoTest=mongoService.mongoTest();
		return mongoTest;
	}
}
