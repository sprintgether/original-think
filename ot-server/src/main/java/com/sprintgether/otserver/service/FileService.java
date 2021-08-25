package com.sprintgether.otserver.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface FileService {

    String store(MultipartFile file, String uuid) throws IOException;
    Resource read(String uuid) throws MalformedURLException;
}
