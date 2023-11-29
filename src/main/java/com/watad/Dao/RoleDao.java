package com.watad.Dao;

import com.watad.model.Role;

public interface RoleDao {

	Role findByName(String roleName);
	
}
