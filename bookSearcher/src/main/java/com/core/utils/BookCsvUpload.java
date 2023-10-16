package com.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.web.multipart.MultipartFile;

import com.core.module.book.document.Book;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class BookCsvUpload {
	public static List<IndexQuery> ReadCsvFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Uploaded file is empty");
        }
        List<IndexQuery> indexQueries = null;
        try (InputStream inputStream = file.getInputStream();
        	     InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        	     CSVParser csvParser = new CSVParser(inputStreamReader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

        	    indexQueries = csvParser.getRecords().stream()
        	        .map(record -> {
        	        	if(String.valueOf(record.get("ISBN_THIRTEEN_NO")).toLowerCase().contains("x")) {
        	        		return null;
        	        	}
        	            Book book = new Book();
        	            Long isbn = Long.parseLong(record.get("ISBN_THIRTEEN_NO"));
        	            String title = record.get("TITLE_NM");
        	            String author = record.get("AUTHR_NM");
        	            String publisher = record.get("PUBLISHER_NM");
        	            String image = record.get("IMAGE_URL");
        	            String description = record.get("BOOK_INTRCN_CN");
        	            String kdcStr = record.get("KDC_NM");
        	            
        	            book.setIsbn(isbn);
        	            book.setTitle(title);
        	            book.setAuthor(author);
        	            book.setPublisher(publisher);
        	            book.setImage(image);
        	            book.setDescription(description);
        	            if (!StringUtils.isBlank(kdcStr)) {
        	                book.setKdc(kdcStr);
        	            }

        	            IndexQuery indexQuery = new IndexQuery();
        	            indexQuery.setId(book.getIsbn().toString());
        	            indexQuery.setObject(book);
        	            return indexQuery;
        	        })
        	        .collect(Collectors.toList());
        	} catch (IOException e) {
        	    log.error("fileParsing error", e);
        	}
        log.info("pasing succese");
        return indexQueries;
    }
}
