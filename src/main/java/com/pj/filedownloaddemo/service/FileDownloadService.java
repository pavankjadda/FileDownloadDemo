package com.pj.filedownloaddemo.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;

public interface FileDownloadService
{
	ResponseEntity<InputStreamResource> viewAndDownloadPdfFileUsingInputStreamResource();
	byte[] viewAndDownloadPdfFileUsingResponseBody();
	void downloadPdfFileUsingResponseBody(HttpServletResponse response);
	ResponseEntity<InputStreamResource> downloadWordFileUsingInputStreamResource();
	ResponseEntity<InputStreamResource> downloadExcelFileUsingInputStreamResource();
	ResponseEntity<InputStreamResource> viewAndDownloadPngImageUsingInputStreamResource();
	ResponseEntity<InputStreamResource> viewAndDownloadJpegImageUsingInputStreamResource();

	void downloadPngImageUsingInputStreamResource(HttpServletResponse response);

	void downloadJpegImageUsingInputStreamResource(HttpServletResponse response);
}
