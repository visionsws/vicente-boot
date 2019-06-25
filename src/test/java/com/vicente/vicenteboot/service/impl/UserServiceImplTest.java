package com.vicente.vicenteboot.service.impl;

import com.vicente.vicenteboot.entity.User;
import com.vicente.vicenteboot.service.UserService;
import com.vicente.vicenteboot.util.JacksonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author shiweisen
 * @since 2018-08-09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    public void getUserList() {
        List<User> list = userService.getUserList();
        list.stream().forEach(System.out::println);
    }

    @Test
    public void getUserByName() {
        User user =userService.getUserByName("xiaohu");
        System.out.println(user.toString());
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setName("12312");
        user.setJobNumber("2323");
        User info = userService.addUser(user);
        System.out.println(info.toString());

    }

    @Test
    public void updateUserById() {
        User user = userService.getUserByName("smlz");
        user.setJobNumber("RW");
        User info = userService.updateUserById(user);
        System.out.println(info.toString());
    }

    @Test
    public void getCurrentUserList() {
        List<User> list = userService.getCurrentUserList();
        list.stream().forEach(System.out::println);
    }

    @Test
    public void getPageUserList() {
        Page<User> page = userService.getPageUserList();
        System.out.println( JacksonUtil.writeValueAsString(page));
    }

    @Test
    public void heightAccess() {
        userService.heightAccess();
        System.out.println("end");

    }
}