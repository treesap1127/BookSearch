//package com.core.utils;
//
//import com.core.book.document.Book;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//@Component
//public class ExcelReader {
//
//    public List<Book> readFile(String filePath) throws IOException {
//        List<Book> books = new ArrayList<>();
//
//        try (FileInputStream excelFile = new FileInputStream(filePath);
//             Workbook workbook = new XSSFWorkbook(excelFile)) {
//
//            Sheet sheet = workbook.getSheetAt(0);
//            Iterator<Row> iterator = sheet.iterator();
//
//            while (iterator.hasNext()) {
//                Row currentRow = iterator.next();
//                if (currentRow.getRowNum() == 0) {
//                    continue; // Skip header row
//                }
//
//                Book book = new Book();

//                book.setId((long) currentRow.getCell(0).getNumericCellValue());
//                book.setIsbn((long) currentRow.getCell(1).getNumericCellValue());
//                book.setTitle(currentRow.getCell(2).getStringCellValue());
//                book.setAuthor(currentRow.getCell(3).getStringCellValue());
//                book.setPublisher(currentRow.getCell(4).getStringCellValue());
//                book.setImage(currentRow.getCell(5).getStringCellValue());
//                book.setDescription(currentRow.getCell(6).getStringCellValue());
//                book.setKdc(currentRow.getCell(7).getStringCellValue());
//                book.setTitleSub(currentRow.getCell(8).getStringCellValue());
//                book.setAuthorSub(currentRow.getCell(9).getStringCellValue());
//
//                books.add(book);
//            }
//        }
//
//        return books;
//    }
//}
