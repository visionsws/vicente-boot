package com.vicente.vicenteboot.service;

import com.vicente.vicenteboot.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author shiweisen
 * @since 2018-08-09
 */
public interface UserService {

    List<User> getUserList();
    User getUserByName(String name);
    User addUser(User userInfo);
    User updateUserById(User userInfo);
    List<User>getCurrentUserList();
    Page<User> getPageUserList();

    void heightAccess();

}
