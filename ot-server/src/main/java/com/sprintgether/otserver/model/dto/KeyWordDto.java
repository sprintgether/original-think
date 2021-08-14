package com.sprintgether.otserver.model.dto;

import com.sprintgether.otserver.model.entity.KeyWord;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KeyWordDto {
    private String value;

    public static KeyWordDto fromEntity(KeyWord keyWord){
        if(keyWord == null){
            return null;
        }

        return KeyWordDto.builder()
                .value(keyWord.getValue())
                .build();
    }

    public static KeyWord toEntity(KeyWordDto keyWordDto){
        if(keyWordDto == null){
            return null;
        }

        KeyWord keyWord = new KeyWord();
        keyWord.setValue(keyWordDto.getValue());
        return keyWord;
    }
}
