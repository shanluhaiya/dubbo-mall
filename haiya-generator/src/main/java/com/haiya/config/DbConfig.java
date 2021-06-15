package com.haiya.config;

import com.haiya.dao.*;
import com.haiya.utils.RRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 数据库配置
 */
@Configuration
public class DbConfig {
    @Value("${haiya.database: mysql}")
    private String database;
    @Autowired
    private MySQLGenDao mySQLGenDao;
    @Autowired
    private OracleGenDao oracleGenDao;
    @Autowired
    private SQLServerGenDao sqlServerGenDao;
    @Autowired
    private PostgreSQLGenDao postgreSQLGenDao;

    private static boolean mongo = false;

    @Bean
    @Primary
    @Conditional(MongoNullCondition.class)
    public GenDao getGenDao() {
        if ("mysql".equalsIgnoreCase(database)) {
            return mySQLGenDao;
        } else if ("oracle".equalsIgnoreCase(database)) {
            return oracleGenDao;
        } else if ("sqlserver".equalsIgnoreCase(database)) {
            return sqlServerGenDao;
        } else if ("postgresql".equalsIgnoreCase(database)) {
            return postgreSQLGenDao;
        } else {
            throw new RRException("不支持当前数据库：" + database);
        }
    }

    @Bean
    @Primary
    @Conditional(MongoCondition.class)
    public GenDao getMongoDBDao(MongoDBGenDao mongoDBGenDao) {
        mongo = true;
        return mongoDBGenDao;
    }

    public static boolean isMongo() {
        return mongo;
    }
}
