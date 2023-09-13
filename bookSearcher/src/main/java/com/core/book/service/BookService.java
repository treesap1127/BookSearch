package com.core.book.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;


public interface BookService {

	void upload() throws IOException;
}
