package com.sprintgether.otserver.service.core;

import com.sprintgether.otserver.exception.OtDBItemNotFoundException;
import com.sprintgether.otserver.model.dto.UserDto;
import com.sprintgether.otserver.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> findByEmail(String email);

    UserDto create(UserDto userDto);

    User findById(String id) throws OtDBItemNotFoundException;

    List<UserDto> findAll();

    void delete(String id);

    boolean existByEmail(String email);
}
