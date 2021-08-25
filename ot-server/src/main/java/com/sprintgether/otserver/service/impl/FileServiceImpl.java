package com.sprintgether.otserver.service.impl;

import com.sprintgether.otserver.service.FileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger LOGGER = LogManager.getLogger(FileServiceImpl.class);

    @Value("${app.storage.directory}")
    private String storageDirectory;

    @Autowired
    private FileService fileService;

    @Override
    public String store(MultipartFile file, String uuid) throws IOException {

        LOGGER.debug("Créer le répertoire de stockage des fichiers s'il n'existe pas encore ...");
        java.io.File storageUrl = new java.io.File(storageDirectory);
        storageUrl.mkdirs();

        LOGGER.debug("Obtenir le chemin vers le répertoire crée ...");
        Path storagePath = storageUrl.toPath();

        LOGGER.debug("Copier le fichier vers le répertoire ainsi crée en utilisant le uuid reçu en paramètre");
        Files.copy(file.getInputStream(), storagePath.resolve(uuid));

        return uuid;
    }

    @Override
    public Resource read(String uuid) throws MalformedURLException {
        Path readPath = new java.io.File(storageDirectory).toPath();
        Path file = readPath.resolve(uuid);
        Resource resource = new UrlResource(file.toUri());
        if(resource.exists() || resource.isReadable()){
            return resource;
        }else{
            throw new RuntimeException("Impossible de télécharger le fichier");
        }
    }
}
