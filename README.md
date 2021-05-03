# File Download Demo
Spring Boot project that shows PDF, Word, Excel, Image etc. documents download through REST API

## How to Run?
1. Clone the project and run [FileDownloadDemoApplication](src/main/java/com/pj/filedownloaddemo/FileDownloadDemoApplication.java) class
2. The API should be available at http://localhost:8081

## View and Download PDF documents
1. Go to http://localhost:8081/api/v1/download/pdf/input_stream_resource/view to view and download the PDF file using `InputStreamResource`
2. Go to http://localhost:8081/api/v1/download/pdf/response_body/view to view the PDF file using `ResponseBody`
3. Go to http://localhost:8081/api/v1/download/pdf/response_body/download to download the PDF file using `ResponseBody`

## View and Download Word and Excel documents
1. Go to http://localhost:8081/api/v1/download/word/input_stream_resource/download to download the PDF file using `InputStreamResource`
2. Go to http://localhost:8081/api/v1/download/excel/input_stream_resource/downloadd to download the PDF file using `InputStreamResource`

## View and Download Image files
1. Go to http://localhost:8081/api/v1/download/png/input_stream_resource/view to view and download the PNG image using `InputStreamResource`
1. Go to http://localhost:8081/api/v1/download/jpeg/input_stream_resource/view to view and download the JPEG image using `InputStreamResource`
3. Go to http://localhost:8081/api/v1/download/png/response_body/download to download the PNG image using `ResponseBody`
4. Go to http://localhost:8081/api/v1/download/jpeg/response_body/download to download the JPEG image using `ResponseBody`
