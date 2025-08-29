package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.UsersRequestDto;
import org.example.dto.UsersResponseDto;
import org.example.entity.Users;
import org.example.mapper.UsersMapper;
import org.example.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UsersService usersService;

    @PostMapping
    public ResponseEntity<UsersResponseDto> createUsers(@RequestBody UsersRequestDto createUser) {
        Users saveUsers = usersService.createUsers(createUser);
        UsersResponseDto usersResponseDto = UsersMapper.toUsersRegisterResponseDto(saveUsers);

        return ResponseEntity.ok(usersResponseDto);
    }

    @GetMapping("/{uuid}")
    public Users getUserById(@PathVariable(name = "uuid") UUID uuid) {
        return usersService.findByIdUsers(uuid);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<UsersResponseDto> updateUsersDetails(@PathVariable(name = "uuid") UUID uuid,
                                                               @RequestBody UsersRequestDto usersRequestDto) {
        Users users = UsersMapper.usersRequestDtoToUsers(usersRequestDto);
        Users updateUsers = usersService.updateUsers(uuid, users);
        UsersResponseDto usersResponseDto = UsersMapper.toUsersRegisterResponseDto(updateUsers);

        return ResponseEntity.ok(usersResponseDto);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteUsersById(@PathVariable(name = "uuid") UUID uuid) {
        usersService.deleteUsersById(uuid);
        return ResponseEntity.noContent().build();
    }
}
