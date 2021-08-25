package com.sprintgether.otserver.service.core.impl;

import com.sprintgether.otserver.exception.EnumErrorCode;
import com.sprintgether.otserver.exception.InvalidEntityException;
import com.sprintgether.otserver.model.dto.ThinkDto;
import com.sprintgether.otserver.model.entity.File;
import com.sprintgether.otserver.model.entity.Think;
import com.sprintgether.otserver.repository.ThinkRepository;
import com.sprintgether.otserver.service.FileService;
import com.sprintgether.otserver.service.core.ThinkService;
import com.sprintgether.otserver.util.TokenUtil;
import com.sprintgether.otserver.validator.ThinkValidator;
import lombok.extern.slf4j.Slf4j;
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

    @Autowired
    private ThinkRepository thinkRepository;

    @Autowired
    private FileService fileService;

    @Override
    public ThinkDto save(Think think) {
        return ThinkDto.fromEntity(thinkRepository.save(think));
    }

    @Override
    public ThinkDto createThink(String creatorId, MultipartFile document, ThinkDto thinkDto) throws IOException {

        Think think = new Think();

        String documentExtension = ".pdf";
        String documentUuid = fileService.store(document, TokenUtil.generateRandomUuid() + "" + documentExtension);

        File pdfFile = new File();
        pdfFile.setName("");
        pdfFile.setExtension("");
        pdfFile.setUrl(documentUuid);

        think.setDocument(pdfFile);
        think.setJournal("");

        // TODO
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
