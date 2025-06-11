package com.watad.services;

import com.watad.model.PasswordResetToken;

public interface PasswordResetTokenService {
    void save(PasswordResetToken token);
    PasswordResetToken findByToken(String token);
    void delete(PasswordResetToken resetToken);

}
