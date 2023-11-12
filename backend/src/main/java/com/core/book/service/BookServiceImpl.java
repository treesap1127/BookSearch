package com.core.book.service;

import java.io.IOException;
import java.util.*;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.stereotype.Service;

import com.core.book.model.Book;
import com.core.elasitcSearch.Indexing;
import com.core.elasitcSearch.model.IndexVo;
import com.core.utils.BookCsvUploader;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final Indexing<?> indexing;

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

    @Override
    public List<Book> search(String keyword) throws IOException {
        List<Book> result = new ArrayList<>();
        Map<String, SearchResponse> searched = indexing.search(keyword);
        Map<String, Object> titles = new HashMap<>();

        for (String k : searched.keySet()) {
            SearchHit[] hits = searched.get(k).getHits().getHits();
            Arrays.stream(hits)
                    .forEach(v -> {
                        Object title = v.getSourceAsMap().get("title");
                        if (titles.get(String.valueOf(title)) == null) {
                            result.add(new Book(v));
                            titles.put(String.valueOf(title), title);
                        }
                    });
        }
        return result;
    }
}