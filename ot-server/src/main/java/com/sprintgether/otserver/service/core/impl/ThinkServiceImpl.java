package com.sprintgether.otserver.service.core.impl;

import com.sprintgether.otserver.exception.EnumErrorCode;
import com.sprintgether.otserver.exception.InvalidEntityException;
import com.sprintgether.otserver.model.dto.ThinkDto;
import com.sprintgether.otserver.model.entity.Think;
import com.sprintgether.otserver.repository.ThinkRepository;
import com.sprintgether.otserver.service.core.ThinkService;
import com.sprintgether.otserver.validator.ThinkValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ThinkServiceImpl implements ThinkService {
    ThinkRepository thinkRepository;

    public ThinkServiceImpl(ThinkRepository thinkRepository) {
        this.thinkRepository = thinkRepository;
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
