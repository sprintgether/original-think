package com.sprintgether.otserver.service.core.impl;

import com.sprintgether.otserver.exception.EnumErrorCode;
import com.sprintgether.otserver.exception.InvalidEntityException;
import com.sprintgether.otserver.exception.OtDBItemNotFoundException;
import com.sprintgether.otserver.model.dto.UserDto;
import com.sprintgether.otserver.model.entity.User;
import com.sprintgether.otserver.repository.UserRepository;
import com.sprintgether.otserver.service.core.UserService;
import com.sprintgether.otserver.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDto create(UserDto dto) {
        List<String> errors = UserValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("User is not VALID", dto);
            throw new InvalidEntityException("l'entité User n'est pas valide", EnumErrorCode.ERROR_DB_ITEM_NOTFOUND, errors);
        }
        return UserDto.fromEntity(
                userRepository.save(
                        UserDto.toEntity(dto)
                )
        );
    }

    @Override
    public User findById(String id) throws OtDBItemNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(()-> new OtDBItemNotFoundException(EnumErrorCode.ERROR_DB_ITEM_NOTFOUND, User.class.getSimpleName(), "id", id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        if(id == null){
            log.error("User ID is null");
            return;
        }
        userRepository.deleteById(id);
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
