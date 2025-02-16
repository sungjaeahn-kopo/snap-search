package com.snap_search.lvalue.service;

import java.io.InputStream;

public interface AzureBlobService {
	String uploadBlob(String directoryPath, String fileName, InputStream inputStream, long fileSize,
		String contentType);
}