package org.example.service;

import org.aspectj.apache.bcel.Constants;
import org.example.dto.UsersRequestDto;
import org.example.entity.Role;
import org.example.entity.Users;
import org.example.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsersServiceImplTest {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private RoleService roleService;

    @InjectMocks
    private UsersServiceImpl usersService;

    private UsersRequestDto userDto;
    private Users savedUser;
    private Role role;

    @BeforeEach
    void setUp() {
        userDto = new UsersRequestDto();
        userDto.setFullName("Иван Иванов");
        userDto.setPhoneNumber("+79991234567");
        userDto.setAvatar("http://example.com/avatar.png");
        userDto.setRoleId(1L);

        role = new Role();
        role.setId(1L);
        role.setRoleName("User");

        savedUser = Users.builder()
                .uuid(UUID.fromString("94619587-c9c9-48e6-8dde-aac0a1cf76c8"))
                .name("Иван Иванов")
                .phoneNumber("+79991234567")
                .avatar("http://example.com/avatar.png")
                .role(role)
                .build();

    }

    @Test
    void testCreateUsers() {
        when(roleService.getRoleById(1L)).thenReturn(role);

        when(usersRepository.save(any(Users.class))).thenReturn(savedUser);

        Users result = usersService.createUsers(userDto);

        assertNotNull(result);
        assertEquals("Иван Иванов", result.getName());
        assertEquals("+79991234567", result.getPhoneNumber());
        assertEquals("http://example.com/avatar.png", result.getAvatar());
        assertEquals(role, result.getRole());

        verify(roleService).getRoleById(1L);
        verify(usersRepository).save(any(Users.class));
    }

    @Test
    void testUpdateUsers() {
        UUID uuid = UUID.fromString("94619587-c9c9-48e6-8dde-aac0a1cf76c8");

        Users newUserData = Users.builder()
                .name("Igor Sergeev")
                .phoneNumber("+79991234567")
                .avatar("http://newavatar.com/avatar.png")
                .role(role)
                .build();

        when(usersRepository.findById(uuid)).thenReturn(Optional.of(savedUser));

        when(usersRepository.save(any(Users.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Users updatedUser = usersService.updateUsers(uuid, newUserData);

        assertNotNull(updatedUser);
        assertEquals("Igor Sergeev", updatedUser.getName());
        assertEquals("+79991234567", updatedUser.getPhoneNumber());
        assertEquals("http://newavatar.com/avatar.png", updatedUser.getAvatar());

        verify(usersRepository).findById(uuid);
        verify(usersRepository).save(any(Users.class));
    }

    @Test
    void testFindByIdUsers_UserFound() {
        UUID uuid = UUID.fromString("94619587-c9c9-48e6-8dde-aac0a1cf76c8");
        when(usersRepository.findById(uuid)).thenReturn(java.util.Optional.of(savedUser));

        Users result = usersService.findByIdUsers(uuid);

        assertNotNull(result);
        assertEquals("Иван Иванов", result.getName());
        verify(usersRepository).findById(uuid);
    }

    @Test
    void testFindByIdUsers_UserNotFound() {
        UUID uuid = UUID.fromString("94619587-c9c9-48e6-8dde-aac0a1cf76c8");
        when(usersRepository.findById(uuid)).thenReturn(java.util.Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            usersService.findByIdUsers(uuid);
        });

        verify(usersRepository).findById(uuid);
    }

    @Test
    void testDeleteUsersById() {
        UUID uuid = UUID.fromString("94619587-c9c9-48e6-8dde-aac0a1cf76c8");
        usersService.deleteUsersById(uuid);

        verify(usersRepository).deleteById(uuid);
    }
}
