package com.core.book.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
//import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;

import com.core.book.model.Book;
import com.core.elasitcSearch.dao.Indexing;
import com.core.elasitcSearch.model.IndexVo;
import com.core.utils.BookCsvUploader;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final Indexing<?> indexing;
//    private String dirPath = "/Users/jihunjang/Downloads/2인 프로젝트/도서 데이터/origin";


    /**
     * 벌크 인덱싱
     */
    @Override
    public String bookUpload(IndexVo indexVo) throws IOException {
        List<Map<String, Object>> list = BookCsvUploader.ReadCsvFile(indexVo.getExcelFile());
        return indexing.bulkIndexing(indexVo.getIndexName(), list);
    }

    @Override
    public List<SearchHit[]> searchTest(String keyword) throws IOException {
        List<SearchHit[]> result = new ArrayList<>();
        Map<String, SearchResponse> searched = indexing.search(keyword);
        for (String s : searched.keySet()) {
            SearchHit[] hits = searched.get(s).getHits().getHits();
            Arrays.stream(hits)
                    .forEach(v -> {
                        System.out.println(v.getScore());
                    });
            result.add(hits);
        }
        return result;
    }

//    @Override
//    public String uploadByFolder() throws IOException {
//        File folder = new File(this.dirPath);
//        File[] files = folder.listFiles();
//
//        if (files != null) {
//            ExecutorService executorService = Executors.newFixedThreadPool(10);
//
//            for (File file : files) {
//                if (file.isFile() && file.getName().endsWith(".csv")) {
//                    executorService.submit(() -> {
//                        try {
//                            log.info("[thread]: new thread created");
//                            List<Map<String, Object>> fileData = BookCsvUploader.ReadCsvFile(this.convertFileToMultipartFile(file));
//                            indexing.bulkIndexing("book", fileData);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } finally {
//                            log.info("[thread]: completed");
//                        }
//                    });
//                }
//            }
//
//            executorService.shutdown();
//            // 모든 작업이 완료될 때까지 대기
//        }
//        String msg = "[thread]: upload completed";
//        log.info(msg);
//        return msg;
//    }

//    private MultipartFile convertFileToMultipartFile(File file) throws IOException {
//        FileInputStream input = new FileInputStream(file);
//        MultipartFile multipartFile = new MockMultipartFile(
//                "file",
//                file.getName(),
//                Files.probeContentType(Paths.get(file.getAbsolutePath())),
//                input
//        );
//        return multipartFile;
//    }

    @Override
    public List<Book> search(String keyword) throws IOException {
        List<Book> result = new ArrayList<>();
        Map<String, SearchResponse> searched = indexing.search(keyword);
        Map<String, Object> titles = new HashMap<>();

        for (String k : searched.keySet()) {
            SearchHit[] hits = searched.get(k).getHits().getHits();
            Arrays.stream(hits)
                    .forEach(v -> {
//                        제목을 기준으로 중복된 검색 결과 제외
                        String title = String.valueOf(v.getSourceAsMap().get("title"));
                        String titleTrimmed = title.replace("\s", "");
                        if (titles.get(titleTrimmed) == null) {
                            result.add(new Book(v));
                            titles.put(titleTrimmed, titleTrimmed);
                        }
                    });
        }
        return result;
    }
}