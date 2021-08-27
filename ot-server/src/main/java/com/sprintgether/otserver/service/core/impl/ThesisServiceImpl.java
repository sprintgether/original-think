package com.sprintgether.otserver.service.core.impl;

import com.sprintgether.otserver.exception.OtDBItemNotFoundException;
import com.sprintgether.otserver.model.dto.ThesisDto;
import com.sprintgether.otserver.model.entity.File;
import com.sprintgether.otserver.model.entity.Thesis;
import com.sprintgether.otserver.repository.ThesisRepository;
import com.sprintgether.otserver.service.FileService;
import com.sprintgether.otserver.service.core.ThesisService;
import com.sprintgether.otserver.service.core.UserService;
import com.sprintgether.otserver.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class ThesisServiceImpl implements ThesisService {
    private static final Logger LOGGER = LogManager.getLogger(ThesisServiceImpl.class);

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    @Autowired
    private ThesisRepository thesisRepository;

    // 1 - enregistrer les fichiers document et cover
    private File savedDocument(MultipartFile document) throws IOException {
        LOGGER.debug("Obténir le type de document");
        String mimeType = document.getContentType();

        LOGGER.debug("Obténir l'extension");
        String extension = FilenameUtils.getExtension(document.getOriginalFilename().toLowerCase());

        LOGGER.debug("créer le uuid du fichier incluant l'extension");
        String documentUuid =  fileService.store(document, TokenUtil.generateRandomUuid() + "." + extension);

        LOGGER.debug("Création des méta données sur le fichier");
        File pdfFile = new File();

        pdfFile.setName(document.getOriginalFilename().toLowerCase());
        pdfFile.setUrl(documentUuid);
        pdfFile.setMimeType(mimeType);
        pdfFile.setExtension(extension);

        return fileService.save(pdfFile);
    }

    @Override
    public ThesisDto save(Thesis thesis) {
        return ThesisDto.fromEntity(thesisRepository.save(thesis));
    }


    @Override
    public ThesisDto createThesis(String creatorId, MultipartFile document, MultipartFile cover, ThesisDto thesisDto) throws IOException, OtDBItemNotFoundException {
        LOGGER.debug(" 1 - Enregister les fichiers 'document' et 'cover'");
        File savedDocument = savedDocument(document);
        File savedCover = savedDocument(cover);


        Thesis thesis = new Thesis();
        LOGGER.debug("2 - ajouter ces fichiers à thesis");
        thesis.setDocument(savedDocument);
        thesis.setCover(savedCover);

        LOGGER.debug("3 - ajouter les autres infos de la thesis");
        thesis.setGrade(thesisDto.getGrade());
        thesis.setTheme(thesisDto.getTheme());
        thesis.setDomain(thesisDto.getDomain());
        thesis.setDescription(thesisDto.getDescription());
        thesis.setAbstracts(thesisDto.getAbstracts());

        thesis.setCreator(userService.findById(creatorId));

        // 4 - enregistrer la thesis
        return save(thesis);
    }
}
