package com.vicente.vicenteboot.controller;

import com.vicente.vicenteboot.entity.User;
import com.vicente.vicenteboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @author shiweisen
 * @since 2018-08-09
 */
@RestController
@RequestMapping(value = "/test")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有用户
     *
     * @return
     */
    @GetMapping(value = "/getUserList")
    public List<User> getUserList() {
        return userService.getUserList();
    }

    @GetMapping(value = "/getUser")
    public User getUserByName(@RequestParam("name") String name) {
        return userService.getUserByName(name);
    }

    @GetMapping(value = "/getCurrentUserList")
    public List<User> getCurrentUserList() {
        return userService.getCurrentUserList();
    }

    @GetMapping(value = "/getPageUserList")
    public Page<User> getPageUserList() {
        return userService.getPageUserList();
    }

    @PutMapping(value = "/addUser")
    public User addUser(User User) {
        return userService.addUser(User);
    }

    @PostMapping(value = "/updateUser")
    public User updateUser(User User) {
        return userService.updateUserById(User);
    }

    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));/*TimeZone时区，解决差8小时的问题*/
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }


    @RequestMapping(value = "/heightAccess")
    public void heightAccess() {
         userService.heightAccess();
    }

}
