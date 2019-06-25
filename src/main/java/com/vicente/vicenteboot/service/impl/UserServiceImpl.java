package com.vicente.vicenteboot.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.vicente.vicenteboot.common.snowflake.SnowflakeIdWorker;
import com.vicente.vicenteboot.entity.User;
import com.vicente.vicenteboot.repository.UserRepository;
import com.vicente.vicenteboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author shiweisen
 * @since 2018-08-09
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SnowflakeIdWorker generateSnowflake;

    /**
     * 获取所有用户列表
     * @return
     */
    public List<User> getUserList(){
        List<User> userList=new ArrayList<User>();
        userList=userRepository.findAll();
        return  userList;
    }

    /**
     * 通过姓名获取用户信息
     * @param name 用户姓名
     * @return
     */
    public User getUserByName(String name) {
        List<User> userList = userRepository.findByName(name);
        if (!userList.isEmpty()){
            return userList.get(0);
        }
        return null;
    }

    /**
     * 新增用户信息
     * @param User 用户信息
     * @return
     */
    public User addUser(User User) {
        System.out.println(generateSnowflake.nextId());
        return userRepository.save(User);
    }

    /**
     * 更新用户信息
     * @param User 用户信息
     * @return
     */
    public User updateUserById(User User) {
        return userRepository.save(User);
    }

    /**
     * 获取最新的用户
     * @return
     */
    public List<User> getCurrentUserList() {
        Sort sort=new Sort(Sort.Direction.DESC,"createTime");
        return userRepository.findAll(sort);

    }

    /**
     * 获取分页的用户
     * @return
     */
    public Page<User> getPageUserList() {
        Sort sort=new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable = PageRequest.of(0,5,sort);
        return userRepository.findAll(pageable);
    }

    @Override
    public void heightAccess() {
        long startMillis = System.currentTimeMillis();
        System.out.println("all start:"+startMillis);

        Vector<Integer> vc=new Vector<>();
        System.out.println("==========================Druid 测试开始==========================");
        int count = 10;
        // 测试Druid
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Integer res = queryDruid(count);
                    vc.add(res);
                }
            }).start();
        }
        while(vc.size()<10) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 结束时间
        long endMillis = System.currentTimeMillis();
        // 输出结束时间
        System.out.println("end:"+endMillis);
        long sum = endMillis - startMillis;
        System.out.println("sum  time  "+sum);
        System.out.println("avg time "+sum/10);

        vc.clear();
        System.out.println("==========================Druid 测试结束==========================");
        System.out.println();

    }

    /**
     * Druid测试
     * @param count
     * @throws SQLException
     */
    public Integer queryDruid(int count) {
        // 开始时间
        long startMillis = System.currentTimeMillis();
        System.out.println("start:"+startMillis);
        // 循环查询
        for (int i = 0; i < count; i++) {
            userRepository.count();
        }
        // 结束时间
        long endMillis = System.currentTimeMillis();
        // 输出结束时间
        System.out.println("end:"+endMillis);
       return Integer.parseInt((endMillis - startMillis) + "");
    }


}
