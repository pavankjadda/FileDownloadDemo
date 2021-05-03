package com.pj.filedownloaddemo.web;

import com.pj.filedownloaddemo.service.FileDownloadService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Provides API end points to download PDF, Word, Excel, Image etc. files. There are 2 different ways to do it
 * <pre>
 *     1. Stream the file using {@link InputStreamResource} and View in the browser(can be downloaded with browser options)
 *     2. Stream the file using {@link ResponseBody} and View in the browser(can be downloaded with browser options)
 *     3. Just Download the file using {@link HttpServletResponse} and can not be viewed in browser
 * </pre>
 *
 * @author Pavan Kumar Jadda
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/download")
public class FileDownloadController
{
	private final FileDownloadService fileDownloadService;

	public FileDownloadController(FileDownloadService fileDownloadService)
	{
		this.fileDownloadService = fileDownloadService;
	}


	/**
	 * View the PDF file using {@link InputStreamResource} and Download options shown in browser. We can have single end point for all document types like PDF, Excel, Word, Image etc.
	 *
	 * @author Pavan Kumar Jadda
	 * @since 1.0.0
	 */
	@GetMapping("/pdf/input_stream_resource/view")
	public ResponseEntity<InputStreamResource> viewAndDownloadPdfFileUsingInputStreamResource()
	{
		return fileDownloadService.viewAndDownloadPdfFileUsingInputStreamResource();
	}

	/**
	 * View the PDF file using {@link ResponseBody}. In this approach, produces must be specified at {@link GetMapping} and can not be reused for Word, Excel, Image etc. documents
	 *
	 * @author Pavan Kumar Jadda
	 * @since 1.0.0
	 */
	@GetMapping(value = "/pdf/response_body/view", produces = MediaType.APPLICATION_PDF_VALUE)
	@ResponseBody
	public byte[] viewAndDownloadPdfFileUsingResponseBody()
	{
		return fileDownloadService.viewAndDownloadPdfFileUsingResponseBody();
	}

	/**
	 * Download PDF file using {@link ResponseBody} without the option to view it
	 *
	 * @author Pavan Kumar Jadda
	 * @since 1.0.0
	 */
	@GetMapping(value = "/pdf/response_body/download")
	public void downloadPdfFileUsingResponseBody(HttpServletResponse response)
	{
		fileDownloadService.downloadPdfFileUsingResponseBody(response);
	}

	/**
	 * Download the Word file using {@link InputStreamResource}. {@link ResponseBody} can also be used instead of this
	 *
	 * @author Pavan Kumar Jadda
	 * @since 1.0.0
	 */
	@GetMapping("/word/input_stream_resource/download")
	public ResponseEntity<InputStreamResource> downloadWordFileUsingInputStreamResource()
	{
		return fileDownloadService.downloadWordFileUsingInputStreamResource();
	}

	/**
	 * Download the Word file using {@link InputStreamResource}. {@link ResponseBody} can also be used instead of this
	 *
	 * @author Pavan Kumar Jadda
	 * @since 1.0.0
	 */
	@GetMapping("/excel/input_stream_resource/download")
	public ResponseEntity<InputStreamResource> downloadExcelFileUsingInputStreamResource()
	{
		return fileDownloadService.downloadExcelFileUsingInputStreamResource();
	}

	/**
	 * View the PNG Image file using {@link InputStreamResource} and Download options shown in browser. We can have single end point for all document types like PDF, Excel, Word, Image etc.
	 *
	 * @author Pavan Kumar Jadda
	 * @since 1.0.0
	 */
	@GetMapping("/png/input_stream_resource/view")
	public ResponseEntity<InputStreamResource> viewAndDownloadPngImageUsingInputStreamResource()
	{
		return fileDownloadService.viewAndDownloadPngImageUsingInputStreamResource();
	}


	/**
	 * View the JPEG file using {@link InputStreamResource} and Download options shown in browser. We can have single end point for all document types like PDF, Excel, Word, Image etc.
	 *
	 * @author Pavan Kumar Jadda
	 * @since 1.0.0
	 */
	@GetMapping("/jpeg/input_stream_resource/view")
	public ResponseEntity<InputStreamResource> viewAndDownloadJpegImageUsingInputStreamResource()
	{
		return fileDownloadService.viewAndDownloadJpegImageUsingInputStreamResource();
	}

	/**
	 * Download PNG file using {@link ResponseBody} without the option to view it
	 *
	 * @author Pavan Kumar Jadda
	 * @since 1.0.0
	 */
	@GetMapping(value = "/png/response_body/download")
	public void downloadPngImageUsingInputStreamResource(HttpServletResponse response)
	{
		fileDownloadService.downloadPngImageUsingInputStreamResource(response);
	}

	/**
	 * Download JPEG file using {@link ResponseBody} without the option to view it
	 *
	 * @author Pavan Kumar Jadda
	 * @since 1.0.0
	 */
	@GetMapping(value = "/jpeg/response_body/download")
	public void downloadJpegImageUsingInputStreamResource(HttpServletResponse response)
	{
		fileDownloadService.downloadJpegImageUsingInputStreamResource(response);
	}
}
