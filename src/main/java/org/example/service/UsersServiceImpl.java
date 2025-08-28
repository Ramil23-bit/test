package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.UsersRequestDto;
import org.example.entity.Role;
import org.example.entity.Users;
import org.example.repository.RoleRepository;
import org.example.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;

    @Override
    public Users createUsers(UsersRequestDto userDto) {
        Role role = roleRepository.findById(userDto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        Users user = Users.builder()
                .name(userDto.getFullName())
                .phoneNumber(userDto.getPhoneNumber())
                .avatar(userDto.getAvatar())
                .role(role)
                .build();

        return usersRepository.save(user);
    }

    @Override
    public Users updateUsers(UUID uuidUsers, Users newUsers) {
        Users users = findByIdUsers(uuidUsers);

        executeIfNotNull(newUsers.getName(),
                () -> users.setName(newUsers.getName()));
        executeIfNotNull(newUsers.getPhoneNumber(),
                () -> users.setPhoneNumber(newUsers.getPhoneNumber()));
        executeIfNotNull(newUsers.getAvatar(),
                () -> users.setAvatar(newUsers.getAvatar()));
        executeIfNotNull(newUsers.getRole(),
                () -> users.setRole(newUsers.getRole()));

        return usersRepository.save(users);
    }

    @Override
    public Users findByIdUsers(UUID uuidUsers) {
        return usersRepository.findById(uuidUsers).orElseThrow();
    }

    @Override
    public void deleteUsersById(UUID uuidUsers) {
        usersRepository.deleteById(uuidUsers);
    }

    private void executeIfNotNull(Object field, Runnable runnable) {
        if (Objects.nonNull(field)) {
            runnable.run();
        }
    }
}
