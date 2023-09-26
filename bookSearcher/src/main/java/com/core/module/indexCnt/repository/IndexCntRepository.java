package com.core.module.indexCnt.repository;

import com.core.module.indexCnt.IndexCntVo;

public interface IndexCntRepository {

	IndexCntVo findIndexCnt(IndexCntVo indexCntVo);

	void createIndexCnt(IndexCntVo indexCntVo);

	void deleteIndexCnt(IndexCntVo indexCntVo);

}
