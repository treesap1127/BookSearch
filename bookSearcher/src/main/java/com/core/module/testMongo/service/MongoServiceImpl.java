package com.core.module.testMongo.service;

import org.springframework.stereotype.Service;

import com.core.module.testMongo.repository.MongoTestRepository;
import com.core.module.testMongo.vo.MongoVo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MongoServiceImpl implements MongoService {
    private final MongoTestRepository mongoTestRepository;
    
    @Override
    public MongoVo mongoTest() {
        return mongoTestRepository.findByBookId("1");
    }
}