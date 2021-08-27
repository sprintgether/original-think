package com.sprintgether.otserver.service.core.impl;

import com.sprintgether.otserver.exception.EnumErrorCode;
import com.sprintgether.otserver.exception.InvalidEntityException;
import com.sprintgether.otserver.exception.OtDBItemNotFoundException;
import com.sprintgether.otserver.model.dto.TalkDto;
import com.sprintgether.otserver.model.entity.File;
import com.sprintgether.otserver.model.entity.Talk;
import com.sprintgether.otserver.model.entity.Think;
import com.sprintgether.otserver.repository.TalkRepository;
import com.sprintgether.otserver.service.FileService;
import com.sprintgether.otserver.service.core.TalkService;
import com.sprintgether.otserver.service.core.UserService;
import com.sprintgether.otserver.validator.TalkValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TalkServiceImpl implements TalkService {

    @Autowired
    TalkRepository talkRepository;

    @Autowired
    FileService fileService;

    @Autowired
    UserService userService;

    @Override
    public TalkDto save(Talk talk) {
        return TalkDto.fromEntity(talkRepository.save(talk));
    }

    private File saveFile(MultipartFile document){
        // Type du document
        String mimType = document.getContentType();

        // extension du document
        String extension = FilenameUtils.getExtension(document.getOriginalFilename().toLowerCase());

        // Créer le uuid du fichier incluant l'extension
        String documentUuid = document.getOriginalFilename().toLowerCase() + "." + extension;

        File savedFile = new File();
        savedFile.setName(document.getOriginalFilename().toLowerCase());
        savedFile.setMimeType(mimType);
        savedFile.setUrl(documentUuid);
        savedFile.setExtension(extension);

        return fileService.save(savedFile);
    }

    @Override
    public TalkDto createTalk(String creatorId, MultipartFile document, MultipartFile cover, TalkDto talkDto) throws OtDBItemNotFoundException {

        // 1 - enregistrer les documents
        File savedDocument = saveFile(document);
        File savedCover = saveFile(cover);

        Talk talk = new Talk();

        // 2 - ajouter les documents à talk
       talk.setDocument(savedDocument);
       talk.setCover(savedCover);

        // 3 - ajouter les autres info de talk
        talk.setStudyLevel(talkDto.getStudyLevel());
        talk.setTheme(talkDto.getTheme());
        talk.setDomain(talkDto.getDomain());
        talk.setDescription(talkDto.getDescription());
        talk.setAbstracts(talkDto.getAbstracts());

        talk.setCreator(userService.findById(creatorId));

        // 4 - enregistrer le talk
        return save(talk);
    }
}
