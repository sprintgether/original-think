package com.sprintgether.otserver.service.core.impl;

import com.sprintgether.otserver.exception.EnumErrorCode;
import com.sprintgether.otserver.exception.InvalidEntityException;
import com.sprintgether.otserver.exception.OtDBItemNotFoundException;
import com.sprintgether.otserver.model.dto.ThinkDto;
import com.sprintgether.otserver.model.entity.File;
import com.sprintgether.otserver.model.entity.Think;
import com.sprintgether.otserver.repository.ThinkRepository;
import com.sprintgether.otserver.service.core.FileService;
import com.sprintgether.otserver.service.core.ThinkService;
import com.sprintgether.otserver.service.core.UserService;
import org.apache.commons.io.FilenameUtils;
import com.sprintgether.otserver.util.TokenUtil;
import com.sprintgether.otserver.validator.ThinkValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ThinkServiceImpl implements ThinkService {

    private static final Logger LOGGER = LogManager.getLogger(ThinkServiceImpl.class);

    @Autowired
    private ThinkRepository thinkRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;


    private File saveDocument(MultipartFile document) throws IOException {
        LOGGER.debug("obtenir le type du fichier");
        String mimeType = document.getContentType();

        LOGGER.debug("obtenir l'extention du fichier");
        String extension = FilenameUtils.getExtension(document.getOriginalFilename()).toLowerCase();

        String documentUuid = fileService.store(document, TokenUtil.generateRandomUuid() + "." + extension); // Upload du fichier

        File pdfFile = new File();
        // Création des méta données sur le fichier
        pdfFile.setName(document.getOriginalFilename().toLowerCase());
        pdfFile.setUrl(documentUuid);
        pdfFile.setMimeType(mimeType);
        pdfFile.setExtension(extension);
        File savedFile = fileService.save(pdfFile);

        return pdfFile;
    }

    private File saveCover(MultipartFile cover) throws IOException {
        LOGGER.debug("obtenir le type du fichier");
        String mimeType = cover.getContentType();

        LOGGER.debug("obtenir l'extention du fichier");
        String extension = FilenameUtils.getExtension(cover.getOriginalFilename()).toLowerCase();

        String coverUuid = fileService.store(cover, TokenUtil.generateRandomUuid() + "." + extension); // Upload du fichier

        File imageFile = new File();
        // Création des méta données sur le fichier
        imageFile.setName(cover.getOriginalFilename().toLowerCase());
        imageFile.setUrl(coverUuid);
        imageFile.setMimeType(mimeType);
        imageFile.setExtension(extension);
        File savedCover = fileService.save(imageFile);

        return imageFile;
    }

    @Override
    public ThinkDto save(Think think) {
        return ThinkDto.fromEntity(thinkRepository.save(think));
    }

    @Override
    public ThinkDto createThink(String creatorId, MultipartFile document, MultipartFile cover, ThinkDto thinkDto) throws IOException, OtDBItemNotFoundException {
        Think think = new Think();

        File savedFile = saveDocument(document);
        File savedCover = saveCover(cover);

        think.setDocument(savedFile);
        think.setCover(savedCover);

        // mettre les autres infos sur le THINK
        think.setJournal(thinkDto.getJournal());
        think.setTheme(thinkDto.getTheme());
        think.setDomain(thinkDto.getDomain());
        think.setDescription(thinkDto.getDescription());
        think.setAbstracts(thinkDto.getAbstracts());
        think.setIsPublished(thinkDto.getIsPublished());
        think.setPublishedAt(thinkDto.getPublishedAt());

        //
        think.setCreator(userService.findById(creatorId));

        return save(think);
    }

}
