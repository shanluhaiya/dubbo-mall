package com.haiya.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.haiya.config.MongoManager;
import com.haiya.dao.GenDao;
import com.haiya.dao.MongoDBGenDao;
import com.haiya.factory.MongoDBCollectionFactory;
import com.haiya.utils.GenUtils;
import com.haiya.utils.PageUtils;
import com.haiya.utils.Query;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 */
@Service
public class SysGenService {
    @Autowired
    private GenDao genDao;

    public PageUtils queryList(Query query) {
        Page<?> page = PageHelper.startPage(query.getPage(), query.getLimit());
        List<Map<String, Object>> list = genDao.queryList(query);
        int total = (int) page.getTotal();
        if (genDao instanceof MongoDBGenDao) {
            total = MongoDBCollectionFactory.getCollectionTotal(query);
        }
        return new PageUtils(list, total, query.getLimit(), query.getPage());
    }

    public Map<String, String> queryTable(String tableName) {
        return genDao.queryTable(tableName);
    }

    public List<Map<String, String>> queryColumns(String tableName) {
        return genDao.queryColumns(tableName);
    }


    public byte[] generatorCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames) {
            //查询表信息
            Map<String, String> table = queryTable(tableName);
            //查询列信息
            List<Map<String, String>> columns = queryColumns(tableName);
            //生成代码
            GenUtils.generatorCode(table, columns, zip);
        }
        if (MongoManager.isMongo()) {
            GenUtils.generatorMongoCode(tableNames, zip);
        }


        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}
