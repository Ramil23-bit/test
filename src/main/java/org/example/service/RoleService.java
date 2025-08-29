package org.example.service;

import org.example.entity.Role;

public interface RoleService {

    Role createRole(Role role);
    Role getRoleById(long id);
}
