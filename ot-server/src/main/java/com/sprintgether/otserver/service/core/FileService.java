package com.sprintgether.otserver.service.core;

import com.sprintgether.otserver.model.entity.File;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface FileService {

    File save(File file);
    String store(MultipartFile file, String uuid) throws IOException;
    Resource read(String uuid) throws MalformedURLException;
}
