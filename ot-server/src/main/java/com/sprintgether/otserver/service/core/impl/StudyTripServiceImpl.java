package com.sprintgether.otserver.service.core.impl;

import com.sprintgether.otserver.exception.EnumErrorCode;
import com.sprintgether.otserver.exception.InvalidEntityException;
import com.sprintgether.otserver.exception.OtDBItemNotFoundException;
import com.sprintgether.otserver.model.dto.StudyTripDto;
import com.sprintgether.otserver.model.entity.File;
import com.sprintgether.otserver.model.entity.StudyTrip;
import com.sprintgether.otserver.model.entity.Thesis;
import com.sprintgether.otserver.repository.StudyTripRepository;
import com.sprintgether.otserver.service.FileService;
import com.sprintgether.otserver.service.core.StudyTripService;
import com.sprintgether.otserver.service.core.UserService;
import com.sprintgether.otserver.util.TokenUtil;
import com.sprintgether.otserver.validator.StudyTripValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudyTripServiceImpl implements StudyTripService {
    private static final Logger LOGGER =  LogManager.getLogger(StudyTripServiceImpl.class);

    @Autowired
    StudyTripRepository studyTripRepository;

    @Autowired
    FileService fileService;

    @Autowired
    UserService userService;


    @Override
    public StudyTripDto save(StudyTrip studyTrip) {
        return StudyTripDto.fromEntity(
                studyTripRepository.save(studyTrip)
        );
    }

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
    public StudyTripDto createStudyTrip(String creatorId, MultipartFile document, MultipartFile cover, StudyTripDto studyTripDto) throws IOException, OtDBItemNotFoundException {
        LOGGER.debug(" 1 - Enregister les fichiers 'document' et 'cover'");
        File savedDocument = savedDocument(document);
        File savedCover = savedDocument(cover);


        StudyTrip studyTrip = new StudyTrip();
        LOGGER.debug("2 - ajouter ces fichiers à thesis");
        studyTrip.setDocument(savedDocument);
        studyTrip.setCover(savedCover);

        LOGGER.debug("3 - ajouter les autres infos de la thesis");
        studyTrip.setLocality(studyTrip.getLocality());
        studyTrip.setMentorName(studyTrip.getMentorName());
        studyTrip.setMentorEmail(studyTrip.getMentorEmail());

        studyTrip.setTheme(studyTripDto.getTheme());
        studyTrip.setDomain(studyTripDto.getDomain());
        studyTrip.setDescription(studyTripDto.getDescription());
        studyTrip.setAbstracts(studyTripDto.getAbstracts());

        studyTrip.setCreator(userService.findById(creatorId));

        // 4 - enregistrer la thesis
        return save(studyTrip);
    }
}
