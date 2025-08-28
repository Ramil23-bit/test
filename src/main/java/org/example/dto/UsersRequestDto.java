package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entity.Role;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsersRequestDto {

    private String fullName;

    private String phoneNumber;

    private String avatar;

    private long roleId;
}
