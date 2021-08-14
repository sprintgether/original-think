package com.sprintgether.otserver.model.dto;

import com.sprintgether.otserver.model.entity.File;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FileDto {
    private String name;
    private String url;
    private String mimeType;
    private String extension;

    public static FileDto fromEntity(File file){
        if(file == null){
            return null;
        }

        return FileDto.builder()
                .name(file.getName())
                .url(file.getUrl())
                .mimeType(file.getMimeType())
                .extension(file.getExtension())
                .build();
    }

    public static File toEntity(FileDto fileDto){
        if(fileDto == null){
            return null;
        }

        File file = new File();
        file.setName(fileDto.getName());
        file.setUrl(fileDto.getUrl());
        file.setMimeType(fileDto.getMimeType());
        file.setExtension(fileDto.getExtension());

        return  file;
    }
}
