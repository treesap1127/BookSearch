package com.core.utils;

import com.core.book.document.Book;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVReader {

    public List<Book> readFile(String filePath) throws IOException {
        List<Book> books = new ArrayList<>();

        try (FileReader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            boolean firstLineSkipped = false;

            for (CSVRecord csvRecord : csvParser) {
                if (!firstLineSkipped) {
                    firstLineSkipped = true;
                    continue; // Skip the first line
                }

                if (csvRecord.get(6) == null) {
                    continue;
                }

                Book book = new Book();
                book.setIsbn13(Long.parseLong(csvRecord.get(1).trim()));
                book.setTitle(csvRecord.get(2));
                book.setAuthor(csvRecord.get(3));
                book.setPublisher(csvRecord.get(4).trim());
                book.setImage(csvRecord.get(5));
                book.setDescription(csvRecord.get(6));
                book.setKdc(csvRecord.get(7).trim());

                books.add(book);
            }
        }

        return books;
    }
}
