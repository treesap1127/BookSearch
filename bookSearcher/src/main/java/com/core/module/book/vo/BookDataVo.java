package com.core.module.book.vo;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Alias("bookData")
public class BookDataVo {
	    private Long seqNo;
	    private String isbnThirteenNo;
	    private String vlmNm;
	    private String titleNm;
	    private String authrNm;
	    private String publisherNm;
	    private String pblcteDe;
	    private String adtionSmblNm;
	    private String prcValue;
	    private String imageUrl;
	    private String bookIntrcnCn;
	    private String kdcNm;
	    private String titleSbstNm;
	    private String authrSbstNm;
	    private String twoPblcteDe;
	    private String intntBookstBookExstAt;
	    private String portalSiteBookExstAt;
	    private String isbnNo;
}
