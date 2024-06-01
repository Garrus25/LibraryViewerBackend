package com.example.libraryviewerbackend.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface PictureRetriever {
    Resource retrieveStaticPictureData(String filename);

    Boolean saveImage(MultipartFile file);
}
