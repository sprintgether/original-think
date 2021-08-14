package com.sprintgether.otserver.controller.api;

import com.sprintgether.otserver.model.dto.AuthorDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sprintgether.otserver.util.constants.APP_ROOT;

@Api(APP_ROOT + "/authors")
public interface AuthorApi {
    @PostMapping(APP_ROOT + "/authors/create")
    AuthorDto save(@RequestBody AuthorDto authorDto);

    @GetMapping(APP_ROOT + "/authors/find/{id}")
    AuthorDto findById(@PathVariable("id") String id);

    @GetMapping(APP_ROOT + "/authors/find/{firstName}")
    AuthorDto findByFirstName(@PathVariable("firstName") String firstName);

    @GetMapping(APP_ROOT + "/authors/find/{email}")
    AuthorDto findByEmail(@PathVariable("email") String email);

    @GetMapping(APP_ROOT + "/authors/all")
    List<AuthorDto> findAll();

    @DeleteMapping(APP_ROOT + "authors/delete/{id}")
    void delete(@PathVariable("id") String id);
}
