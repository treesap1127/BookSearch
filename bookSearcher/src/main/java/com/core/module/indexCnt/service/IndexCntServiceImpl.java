package com.core.module.indexCnt.service;

import org.springframework.stereotype.Service;

import com.core.module.indexCnt.IndexCntVo;
import com.core.module.indexCnt.repository.IndexCntRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
@Log4j2
@RequiredArgsConstructor
@Service
public class IndexCntServiceImpl implements IndexCntService {
	private final IndexCntRepository indexCntRepository;
	
	@Override
	public void deleteIndexCnt(IndexCntVo indexCntVo) {
		indexCntRepository.deleteIndexCnt(indexCntVo);
	}

	@Override
	public void createIndexCnt(IndexCntVo indexCntVo) {
		indexCntRepository.createIndexCnt(indexCntVo);

	}

	/**
	 * 인덱스 갯수 확인 후 5000만개 이상 시 false
	 */
	@Override
	public String findIndexRslt(IndexCntVo indexCntVo) {
		IndexCntVo indexVo = findIndexCnt(indexCntVo);
		if (indexVo == null || "".equals(indexVo.getIndexName()) ) {
			log.info("인덱스가 미 존재 합니다");
			return "인덱스가 존재하지 않습니다.";
		}
		if(indexVo.getIndexCnt() >=50000000) {
			log.info("인덱스가 많습니다");
			return "인덱스의 개수가 너무 많습니다..";
		}
		return "성공";
	}

	public IndexCntVo findIndexCnt(IndexCntVo indexCntVo) {
		return indexCntRepository.findIndexCnt(indexCntVo);
	}

}
