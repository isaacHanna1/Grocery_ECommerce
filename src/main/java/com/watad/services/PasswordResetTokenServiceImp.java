package com.watad.services;

import com.watad.Dao.PasswordResetTokenDao;
import com.watad.model.PasswordResetToken;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetTokenServiceImp implements  PasswordResetTokenService{

    private final PasswordResetTokenDao passwordResetTokenDao;


    public PasswordResetTokenServiceImp(PasswordResetTokenDao passwordResetTokenDao) {
        this.passwordResetTokenDao = passwordResetTokenDao;
    }

    @Override
    public void save(PasswordResetToken token) {
        passwordResetTokenDao.save(token);
    }

    @Override
    public PasswordResetToken findByToken(String token) {
       return passwordResetTokenDao.findByToken(token);
    }

    @Override
    public void delete(PasswordResetToken resetToken) {
         passwordResetTokenDao.delete(resetToken);
    }
}
