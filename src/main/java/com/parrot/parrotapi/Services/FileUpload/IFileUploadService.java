package com.parrot.parrotapi.Services.FileUpload;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadService {

    String upload(MultipartFile file, String fileName);
}