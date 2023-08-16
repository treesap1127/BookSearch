package com.core.module.testJpa.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "test")
public class TestJpaVo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "test")
    private Integer test;

    @Column(name = "test2")
    private String test2;
}
