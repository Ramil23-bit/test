package org.example.mapper;


import org.example.dto.UsersRequestDto;
import org.example.dto.UsersResponseDto;
import org.example.entity.Users;

public class UsersMapper {

    public static UsersResponseDto toUsersRegisterResponseDto(Users users) {
        return UsersResponseDto.builder()
                .fullName(users.getName())
                .phoneNumber(users.getPhoneNumber())
                .build();
    }

    public static Users usersResponseDtoToUsers(UsersResponseDto usersRegisterResponseDto) {
        return Users.builder()
                .name(usersRegisterResponseDto.getFullName())
                .phoneNumber(usersRegisterResponseDto.getPhoneNumber())
                .build();
    }

    public static Users usersRequestDtoToUsers(UsersRequestDto usersRegisterRequestDto) {
        return Users.builder()
                .avatar(usersRegisterRequestDto.getAvatar())
                .name(usersRegisterRequestDto.getFullName())
                .phoneNumber(usersRegisterRequestDto.getPhoneNumber())
                //.role(usersRegisterRequestDto.getRoleId())
                .build();
    }

    public static UsersRequestDto usersToUsersRequestDto(Users users) {
        return UsersRequestDto.builder()
                .avatar(users.getAvatar())
                .fullName(users.getName())
                .phoneNumber(users.getPhoneNumber())
                .roleId(users.getRole().getId())
                .build();
    }
}
