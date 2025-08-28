package org.example.service;

import org.example.dto.UsersRequestDto;
import org.example.dto.UsersResponseDto;
import org.example.entity.Users;

import java.util.UUID;

public interface UsersService {
    Users createUsers(UsersRequestDto users);

    Users updateUsers(UUID uuidUsers, Users newUsers);

    Users findByIdUsers(UUID uuidUsers);

    void deleteUsersById(UUID uuidUsers);
}
