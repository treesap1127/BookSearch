package com.core.module.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.core.module.book.vo.BookDataVo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CsvUpload {
	public static List<BookDataVo> ReadCsvFile(MultipartFile file) throws IOException {
        List<BookDataVo> data = new ArrayList<>();
        
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Uploaded file is empty");
        }
        
        try (InputStream inputStream = file.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                BookDataVo item = new BookDataVo();
                item.setSeqNo(Long.parseLong(fields[0].replaceAll("\"","")));
                item.setIsbnThirteenNo(fields[1].replaceAll("\"",""));
                item.setVlmNm(fields[2].replaceAll("\"",""));
                item.setTitleNm(fields[3].replaceAll("\"",""));
                item.setAuthrNm(fields[4].replaceAll("\"",""));
                item.setPublisherNm(fields[5].replaceAll("\"",""));
                item.setPblcteDe(fields[6].replaceAll("\"",""));
                item.setAdtionSmblNm(fields[7].replaceAll("\"",""));
                item.setPrcValue(fields[8].replaceAll("\"",""));
                item.setImageUrl(fields[9].replaceAll("\"",""));
                item.setBookIntrcnCn(fields[10].replaceAll("\"",""));
                item.setKdcNm(fields[11].replaceAll("\"",""));
                item.setTitleSbstNm(fields[12].replaceAll("\"",""));
                item.setAuthrSbstNm(fields[13].replaceAll("\"",""));
                item.setTwoPblcteDe(fields[14].replaceAll("\"",""));
                item.setIntntBookstBookExstAt(fields[15].replaceAll("\"",""));
                item.setPortalSiteBookExstAt(fields[16].replaceAll("\"",""));
                item.setIsbnNo(fields[17].replaceAll("\"",""));
                
                data.add(item);
            }
        }
        
        return data;
    }
}
