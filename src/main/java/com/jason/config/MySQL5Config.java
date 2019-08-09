package com.jason.config;

import org.hibernate.dialect.MySQL5Dialect;

/**
 * Created by BNC on 2019/7/2.
 */
public class MySQL5Config extends MySQL5Dialect {

    /**
     * 修改默认的数据库引擎及字符集
     * @return
     */
    @Override
    public String getTableTypeString() {
//        return super.getTableTypeString();
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
