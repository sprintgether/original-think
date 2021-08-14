package com.sprintgether.otserver.controller.api;

import com.sprintgether.otserver.model.dto.RoleDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sprintgether.otserver.util.constants.APP_ROOT;

@Api(APP_ROOT + "/roles")
public interface RoleApi {
    @PostMapping(APP_ROOT + "/roles/create")
    RoleDto save(@RequestBody RoleDto roleDto);

    @GetMapping(APP_ROOT + "/roles/find/{id}")
    RoleDto findById(@PathVariable String id);

    @GetMapping(APP_ROOT + "/roles/find/{roleName}")
    RoleDto findByRoleName(@PathVariable("roleName") String roleName);

    @GetMapping(APP_ROOT + "/roles/find/{id}")
    List<RoleDto> findAll();

    @DeleteMapping(APP_ROOT + "/roles/delete/{id}")
    void delete(@PathVariable("id") String id);
}
