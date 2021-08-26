package com.sprintgether.otserver.service.core.impl;

import com.sprintgether.otserver.annotation.CurrentUser;
import com.sprintgether.otserver.exception.EnumErrorCode;
import com.sprintgether.otserver.exception.InvalidEntityException;
import com.sprintgether.otserver.exception.OtDBItemNotFoundException;
import com.sprintgether.otserver.model.dto.ThinkDto;
import com.sprintgether.otserver.model.dto.UserDto;
import com.sprintgether.otserver.model.entity.File;
import com.sprintgether.otserver.model.entity.Think;
import com.sprintgether.otserver.model.enums.ResponseStatus;
import com.sprintgether.otserver.model.payload.RestResponse;
import com.sprintgether.otserver.repository.ThinkRepository;
import com.sprintgether.otserver.service.FileService;
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
import org.springframework.web.bind.annotation.GetMapping;
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

    @Override
    public ThinkDto save(Think think) {
        return ThinkDto.fromEntity(thinkRepository.save(think));
    }

    @Override
    public ThinkDto createThink(String creatorId, MultipartFile document, ThinkDto thinkDto) throws IOException, OtDBItemNotFoundException {
        Think think = new Think();

        LOGGER.debug("obtenir le type du fichier");
        String mimeType = document.getContentType();

        LOGGER.debug("obtenir l'extention du fichier");
        String extension = FilenameUtils.getExtension(document.getOriginalFilename()).toLowerCase();

        String documentUuid = fileService.store(document, TokenUtil.generateRandomUuid() + "" + extension); // Upload du fichier

        File currentFile = new File();
        // Création des méta données sur le fichier
        currentFile.setName(document.getOriginalFilename().toLowerCase());
        currentFile.setUrl(documentUuid);
        currentFile.setMimeType(mimeType);
        currentFile.setExtension(extension);

        think.setDocument(currentFile);
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

    @Override
    public ThinkDto save(ThinkDto dto) {
        List<String> errors = ThinkValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Think is not VALID", dto);
            throw new InvalidEntityException("l'entité Think n'est pas valide", EnumErrorCode.ERROR_DB_ITEM_NOTFOUND, errors);
        }
        return ThinkDto.fromEntity(
                thinkRepository.save(
                        ThinkDto.toEntity(dto)
                )
        );
    }

    @Override
    public ThinkDto findById(String id) {
        if(!StringUtils.hasLength(id)){
            log.error("User ID is null");
        }

        Optional<Think> think = thinkRepository.findById(id);
        return Optional.of(ThinkDto.fromEntity(think.get())).orElseThrow(()
                -> new InvalidEntityException("Aucun Think avec l'ID "  + id + "n'a été trouvé", EnumErrorCode.ERROR_DB_ITEM_NOTFOUND) );
    }

    @Override
    public ThinkDto findByJournal(String journal) {
        if(!StringUtils.hasLength(journal)){
            log.error("THINK journal is null");
        }

        Optional<Think> think = thinkRepository.findThinkByJournal(journal);
        return Optional.of(ThinkDto.fromEntity(think.get())).orElseThrow(()
                -> new InvalidEntityException("Aucun Think avec le nom "  + think + "n'a été trouvé", EnumErrorCode.ERROR_DB_ITEM_NOTFOUND) );
    }

    @Override
    public List<ThinkDto> findAll() {
        return thinkRepository.findAll().stream()
                .map(ThinkDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        if(id == null){
            log.error("THINK ID is null");
            return;
        }
        thinkRepository.deleteById(id);
    }

}
