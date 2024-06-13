package com.Lunexconline.Ecommerce.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageStorageService {
    List<String> saveImages(List<MultipartFile> images) throws IOException;
}

