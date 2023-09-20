package com.core.module.utils;

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

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class BookCsvUpload {
	public static List<Map<String, String>> ReadCsvFile(MultipartFile file) throws IOException {
        List<Map<String, String>> data = new ArrayList<>();
        
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Uploaded file is empty");
        }
        
        try (InputStream inputStream = file.getInputStream();
        	     InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        	     CSVParser csvParser = new CSVParser(inputStreamReader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
        	  	int cnt = 0;
        	    for (CSVRecord record : csvParser) {
        	        String isbnStr = record.get("ISBN_THIRTEEN_NO");
        	        String title = record.get("TITLE_NM");
        	        String author = record.get("AUTHR_NM");
        	        String publisher = record.get("PUBLISHER_NM");
        	        String image = record.get("IMAGE_URL");
        	        String description = record.get("BOOK_INTRCN_CN");
        	        String kdcStr = record.get("KDC_NM");
        	        String titleSub = record.get("TITLE_SBST_NM");
        	        String authorSub = record.get("AUTHR_SBST_NM");

        	        Map<String, String> itemMap = new HashMap<String, String>();
        	        
        	        itemMap.put("isbn",isbnStr);
        	        itemMap.put("title",title);
        	        itemMap.put("author",author);
        	        itemMap.put("publisher",publisher);
        	        itemMap.put("image",image);
        	        itemMap.put("description",description);
        	        if(!StringUtils.isBlank(kdcStr)) itemMap.put("kdc",kdcStr);
        	        itemMap.put("titleSub",titleSub);
        	        itemMap.put("authorSub",authorSub);

        	        data.add(itemMap);
        	        cnt ++;
        	        if(cnt % 30000 == 0) {
        	        	log.debug(cnt+"cnt 갯수");
        	        }
        	    }
        	} catch (IOException e) {
        	    log.error("filePasing error ",e);
        	}
        
        return data;
    }
}
