package com.watad.services;

import com.watad.Dao.RoleDao;
import com.watad.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RoleServiceImp implements RoleService{

    private final RoleDao roleDao;

    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Role findByName(String roleName) {
         return    roleDao.findByName(roleName);
    }
}
