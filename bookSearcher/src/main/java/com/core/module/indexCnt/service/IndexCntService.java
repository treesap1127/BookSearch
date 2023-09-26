package com.core.module.indexCnt.service;

import com.core.module.indexCnt.IndexCntVo;

public interface IndexCntService {

	void deleteIndexCnt(IndexCntVo indexCntVo);

	void createIndexCnt(IndexCntVo indexCntVo);

	IndexCntVo findIndexCnt(IndexCntVo indexCntVo);

	String findIndexRslt(IndexCntVo indexCntVo);

}
