package com.sprintgether.otserver.model.dto;

import com.sprintgether.otserver.model.entity.Author;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthorDto {
    private String firstName;
    private String lastName;
    private String title;
    private String email;
    private String institution;


    public static AuthorDto fromEntity(Author author){
        if(author == null){
            return null;
        }

        return AuthorDto.builder()
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .title(author.getTitle())
                .email(author.getEmail())
                .institution(author.getInstitution())
                .build();
    }

    public static Author toEntity(AuthorDto authorDto){
        if(authorDto == null){
            return null;
        }

        Author author = new Author();
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        author.setTitle(authorDto.getTitle());
        author.setEmail(authorDto.getEmail());
        author.setInstitution(authorDto.getInstitution());

        return author;
    }
}
