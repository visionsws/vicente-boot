package com.vicente.vicenteboot.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 *  自动填充创建时间和更新时间
 *
 * @author weisen
 * @since 2019-10-30
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        LOGGER.info("start insert fill ....");
        //this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);//版本号3.0.6以及之前的版本
        boolean hasCreateTime = metaObject.hasSetter("createTime");
        if (hasCreateTime){
            this.setInsertFieldValByName("createTime", LocalDateTime.now(), metaObject);
            this.setInsertFieldValByName("isDelete", 0, metaObject);
        }
        this.setInsertFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LOGGER.info("start update fill ....");
        Object fieldValue = getFieldValByName("updateTime", metaObject);
        if (fieldValue == null){
            this.setUpdateFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
}
