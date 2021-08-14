package com.sprintgether.otserver.controller.api;

import com.sprintgether.otserver.model.dto.UserDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.sprintgether.otserver.util.constants.APP_ROOT;

@Api(APP_ROOT + "/users")
public interface UserApi {
    @PostMapping(APP_ROOT + "/users/create")
    UserDto save(UserDto userDto);

    @GetMapping(APP_ROOT + "/users/find/{id}")
    UserDto findById(@PathVariable("id") String id);

    @GetMapping(APP_ROOT + "/users/find/{email}")
    UserDto findByEmail(@PathVariable("email") String email);

    @GetMapping(APP_ROOT + "/users/find/all")
    List<UserDto> findAll();

    @DeleteMapping(APP_ROOT + "/users/delete/{id}")
    void delete(@PathVariable("id") String id);
}
