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
@Table(name = "vicente_article")
public class Article {

    @Id
    @Column(name="id", columnDefinition = "varchar(32)")
    @GeneratedValue(generator  = "myIdStrategy")
    @GenericGenerator(name = "myIdStrategy", strategy = "uuid")
    private  String id;

    @Column(name = "title")
    private  String title;

    @Column(name = "content")
    private  String content; //工号

    @CreatedDate
    @Column(name = "createTime")
    private Date createTime; //创建时间

    @LastModifiedDate
    @Column(name = "updateTime")
    private Date updateTime;

}
