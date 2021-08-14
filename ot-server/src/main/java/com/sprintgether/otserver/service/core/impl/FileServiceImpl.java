package com.sprintgether.otserver.service.core.impl;

import com.sprintgether.otserver.exception.EnumErrorCode;
import com.sprintgether.otserver.exception.InvalidEntityException;
import com.sprintgether.otserver.model.dto.FileDto;
import com.sprintgether.otserver.model.entity.File;
import com.sprintgether.otserver.repository.FileRepository;
import com.sprintgether.otserver.service.core.FileService;
import com.sprintgether.otserver.validator.FileValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public FileDto save(FileDto fileDto) {
        List<String> errors = FileValidator.validate(fileDto);
        if(!errors.isEmpty()){
            log.error("File is not VALID", fileDto);
            throw new InvalidEntityException("l'entité File n'est pas valid", EnumErrorCode.ERROR_DB_ITEM_NOTFOUND, errors);
        }
        return FileDto.fromEntity(
                fileRepository.save(
                        FileDto.toEntity(fileDto)
                )
        );
    }

    @Override
    public FileDto findById(String id) {
        if(!StringUtils.hasLength(id)){
            log.error("AUTHOR ID is null");
        }

        Optional<File> file = fileRepository.findById(id);
        return Optional.of(FileDto.fromEntity(file.get())).orElseThrow(()
                -> new InvalidEntityException("Aucun auteur avec l'ID "  + id + "n'a été trouvé", EnumErrorCode.ERROR_DB_ITEM_NOTFOUND) );
    }

    @Override
    public FileDto findByName(String name) {
        if(!StringUtils.hasLength(name)){
            log.error("FILE name is null");
        }

        Optional<File> file = fileRepository.findFileByName(name);
        return Optional.of(FileDto.fromEntity(file.get())).orElseThrow(()
                -> new InvalidEntityException("Aucun File avec le nom "  + file + "n'a été trouvé", EnumErrorCode.ERROR_DB_ITEM_NOTFOUND) );
    }

    @Override
    public List<FileDto> findAll() {
        return fileRepository.findAll().stream()
                .map(FileDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        if(id == null){
            log.error("FILE ID is null");
            return;
        }
        fileRepository.deleteById(id);
    }
}
