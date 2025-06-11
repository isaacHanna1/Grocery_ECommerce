package com.watad.services;

import com.watad.Dto.RegistrationDto;
import com.watad.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    public void changeUserAddress(long userId,String goverment , String city , String userAdress) ;
    public String getUserRole(long id) ;
    public String getEmailByPhone(String phone);
    void saveUser(User user);
    void saveUser(RegistrationDto registrationDto , HttpServletRequest req);
    User findByEmail(String email);
    User findByPhone(String phone);
    User findById(long id);
    void activeUserAccount(long id );
    void updateUserAddress(long userId , String goverment , String city , String userAddress);
    void updatePassword (String userName , String newPassword);
    List<User> getAllUser() ;

    }
