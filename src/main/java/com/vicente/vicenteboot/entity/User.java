package com.vicente.vicenteboot.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author shiweisen
 * @since 2018-08-09
 */
@Data
@Entity
@Table(name = "vicente_user")
//自动添加创建时间和更新时间
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(generator = "snowFlakeId")
    @GenericGenerator(name = "snowFlakeId", strategy = "com.vicente.vicenteboot.common.snowflake.GenerateId")
    @Column(name = "id")
    private  Long id; //ID

    @Column(name = "name")
    private  String name; //姓名

    @Column(name = "jobNumber")
    private  String jobNumber; //工号

    @CreatedDate
    @Column(name = "createTime")
    private Date createTime; //创建时间

    @LastModifiedDate
    @Column(name = "updateTime")
    private Date updateTime;

}
