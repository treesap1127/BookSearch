package com.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.core.module.book.vo.Book;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class BookCsvUpload {
		public static List<Map<String, Object>> ReadCsvFile(MultipartFile file) throws IOException {

			List<Map<String, Object>> data = new ArrayList<>();
	        
	        if (file.isEmpty()) {
	            throw new IllegalArgumentException("Uploaded file is empty");
	        }
	        
	        try (InputStream inputStream = file.getInputStream();
	        	     InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	        	     CSVParser csvParser = new CSVParser(inputStreamReader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
	        	  	int cnt = 0;
	        	    for (CSVRecord record : csvParser) {
	        	    	if(String.valueOf(record.get("ISBN_THIRTEEN_NO")).toLowerCase().contains("x")) {
	        	            Book book = new Book();
	        	            Long isbn = Long.parseLong(record.get("ISBN_THIRTEEN_NO"));
	        	            String title = record.get("TITLE_NM");
	        	            String author = record.get("AUTHR_NM");
	        	            String publisher = record.get("PUBLISHER_NM");
	        	            String image = record.get("IMAGE_URL");
	        	            String description = record.get("BOOK_INTRCN_CN");
	        	            String kdcStr = record.get("KDC_NM");
	
		        	        Map<String, Object> itemMap = new HashMap<String, Object>();
		        	        
		        	        itemMap.put("isbn",isbn);
		        	        itemMap.put("title",title);
		        	        itemMap.put("author",author);
		        	        itemMap.put("publisher",publisher);
		        	        itemMap.put("image",image);
		        	        itemMap.put("description",description);
		        	        if(!StringUtils.isBlank(kdcStr)) itemMap.put("kdc",kdcStr);
	
		        	        data.add(itemMap);
		        	        cnt ++;
		        	        if(cnt % 30000 == 0) {
		        	        	log.debug(cnt+"cnt 갯수");
		        	        }
	        	    	
        	        	}
	        	    }
	        	} catch (IOException e) {
	        	    log.error("filePasing error ",e);
	        	}
	        log.info("pasing succese");
	        return data;
    }
}
