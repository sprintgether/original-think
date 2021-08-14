package com.sprintgether.otserver.controller.api;

import com.sprintgether.otserver.model.dto.FileDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sprintgether.otserver.util.constants.APP_ROOT;

@Api(APP_ROOT + "/files")
public interface FileApi {
    @PostMapping(APP_ROOT + "/files/create")
    FileDto save(@RequestBody FileDto fileDto);

    @GetMapping(APP_ROOT + "/files/find/{id}")
    FileDto findById(@PathVariable("id") String id);

    @GetMapping(APP_ROOT + "/files/find/{name}")
    FileDto findByName(@PathVariable("name") String name);

    @GetMapping(APP_ROOT + "/files/all")
    List<FileDto> findAll();

    @DeleteMapping(APP_ROOT + "/files/delete/{id}")
    void delete(@PathVariable("id") String id);
}
