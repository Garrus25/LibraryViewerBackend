package com.example.libraryviewerbackend.service;

import org.springframework.core.io.Resource;

public interface PictureRetriever {
    Resource retrieveStaticPictureData(String filename);
}
