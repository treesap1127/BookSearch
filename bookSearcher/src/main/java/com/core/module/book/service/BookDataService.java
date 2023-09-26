package com.core.module.book.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.core.module.indexCnt.IndexCntVo;

public interface BookDataService<T> {

	String bookInitUpload(IndexCntVo indexCntVo) throws IOException;

	String bookUpload(IndexCntVo indexCntVo) throws IOException;

}
