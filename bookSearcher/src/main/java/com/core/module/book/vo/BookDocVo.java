package com.core.module.book.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Document(indexName="books")
public class BookDocVo {
	    @Id
	    private String isbnNo;

	    @Field(type = FieldType.Text)
	    private String titleNm;

	    @Field(type = FieldType.Text)
	    private String authrNm;

	    @Field(type = FieldType.Text)
	    private Integer price;

		public void setIsbnNo(String isbnNo2) {
			// TODO Auto-generated method stub
			
		}

}
