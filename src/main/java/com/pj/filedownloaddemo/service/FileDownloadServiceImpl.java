package com.pj.filedownloaddemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.pj.filedownloaddemo.constants.FileConstants.EXCEL_DOCUMENT_PATH;
import static com.pj.filedownloaddemo.constants.FileConstants.JPEG_IMAGE_PATH;
import static com.pj.filedownloaddemo.constants.FileConstants.PDF_DOCUMENT_PATH;
import static com.pj.filedownloaddemo.constants.FileConstants.PNG_IMAGE_PATH;
import static com.pj.filedownloaddemo.constants.FileConstants.WORD_DOCUMENT_PATH;

/**
 * Contains business logic to stream and down load files
 *
 * @author Pavan Kumar Jadda
 * @since 1.0.0
 */
@Service
public class FileDownloadServiceImpl implements FileDownloadService
{
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Contains business logic to View the PDF file using {@link InputStreamResource} and Download options shown in browser. We can have single end point for all document types like PDF, Excel, Word, Image etc.
	 *
	 * @author Pavan Kumar Jadda
	 * @since 1.0.0
	 */
	@Override
	public ResponseEntity<InputStreamResource> viewAndDownloadPdfFileUsingInputStreamResource()
	{
		try
		{
			var file = ResourceUtils.getFile(PDF_DOCUMENT_PATH);
			var headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=" + file.getName());
			return ResponseEntity
					.ok()
					.headers(headers)
					.contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(new FileInputStream(file)));
		}
		catch (Exception e)
		{
			logger.error("Exception: ", e);
		}
		return null;
	}


	/**
	 * Business logic to View the PDF file using {@link ResponseBody}. In this approach, produces must be specified at {@link GetMapping} and can not be reused for Word, Excel, Image etc. documents
	 *
	 * @author Pavan Kumar Jadda
	 * @since 1.0.0
	 */
	@Override
	public byte[] viewAndDownloadPdfFileUsingResponseBody()
	{
		try (InputStream in = new FileInputStream(ResourceUtils.getFile(PDF_DOCUMENT_PATH)))
		{
			return in.readAllBytes();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return new byte[0];
	}

	/**
	 * Contains business logic to Download PDF file using {@link ResponseBody} without the option to view it
	 *
	 * @param response {@link HttpServletResponse} object
	 *
	 * @author Pavan Kumar Jadda
	 * @since 1.0.0
	 */
	@Override
	public void downloadPdfFileUsingResponseBody(HttpServletResponse response)
	{
		downloadFileUsingResponseBody(response, PDF_DOCUMENT_PATH, MediaType.APPLICATION_PDF_VALUE);
	}

	private void downloadFileUsingResponseBody(HttpServletResponse response, String path, String contentType)
	{
		try (InputStream in = new FileInputStream(ResourceUtils.getFile(path)))
		{
			response.setContentType(contentType);
			response.setHeader("Content-Disposition", "attachment; filename=" + ResourceUtils.getFile(path).getName());
			response.getOutputStream().write(in.readAllBytes());
			response.getOutputStream().flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Contains business logic to View the PDF file using {@link InputStreamResource} and Download options shown in browser. We can have single end point for all document types like PDF, Excel, Word, Image etc.
	 *
	 * @author Pavan Kumar Jadda
	 * @since 1.0.0
	 */
	@Override
	public ResponseEntity<InputStreamResource> downloadWordFileUsingInputStreamResource()
	{
		return downloadWordOrExcelFileUsingInputStreamResource(WORD_DOCUMENT_PATH);
	}

	private ResponseEntity<InputStreamResource> downloadWordOrExcelFileUsingInputStreamResource(String documentPath)
	{
		try
		{
			var file = ResourceUtils.getFile(documentPath);
			var headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=" + file.getName());
			return ResponseEntity
					.ok()
					.headers(headers)
					.contentType(MediaType.APPLICATION_OCTET_STREAM)
					.body(new InputStreamResource(new FileInputStream(file)));
		}
		catch (Exception e)
		{
			logger.error("Exception: ", e);
		}
		return null;
	}

	@Override
	public ResponseEntity<InputStreamResource> downloadExcelFileUsingInputStreamResource()
	{
		return downloadWordOrExcelFileUsingInputStreamResource(EXCEL_DOCUMENT_PATH);
	}

	@Override
	public ResponseEntity<InputStreamResource> viewAndDownloadPngImageUsingInputStreamResource()
	{
		return downloadImageUsingInputStreamResource(PNG_IMAGE_PATH, MediaType.IMAGE_PNG);
	}

	@Override
	public ResponseEntity<InputStreamResource> viewAndDownloadJpegImageUsingInputStreamResource()
	{
		return downloadImageUsingInputStreamResource(JPEG_IMAGE_PATH, MediaType.IMAGE_JPEG);
	}

	@Override
	public void downloadPngImageUsingInputStreamResource(HttpServletResponse response)
	{
		downloadFileUsingResponseBody(response, PNG_IMAGE_PATH, MediaType.IMAGE_PNG_VALUE);
	}

	@Override
	public void downloadJpegImageUsingInputStreamResource(HttpServletResponse response)
	{
		downloadFileUsingResponseBody(response, JPEG_IMAGE_PATH, MediaType.IMAGE_JPEG_VALUE);
	}

	private ResponseEntity<InputStreamResource> downloadImageUsingInputStreamResource(String documentPath, MediaType mediaType)
	{
		try
		{
			var file = ResourceUtils.getFile(documentPath);
			var headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=" + file.getName());
			return ResponseEntity
					.ok()
					.headers(headers)
					.contentType(mediaType)
					.body(new InputStreamResource(new FileInputStream(file)));
		}
		catch (Exception e)
		{
			logger.error("Exception: ", e);
		}
		return null;
	}
}
