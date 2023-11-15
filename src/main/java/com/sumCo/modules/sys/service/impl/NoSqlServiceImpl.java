package com.sumCo.modules.sys.service.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.sumCo.common.utils.MongoDBConnector;
import com.sumCo.modules.sys.dao.SysLogDao;
import com.sumCo.modules.sys.entity.SysLog;
import com.sumCo.modules.sys.service.NoSqlService;
import com.sumCo.modules.sys.service.SysLogService;
import org.apache.commons.lang.StringUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("noSqlService")
public class NoSqlServiceImpl implements NoSqlService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    static MongoDatabase mongodbConnection = MongoDBConnector.getConnection();


    @Override
    public List<SysLog> queryByUserName(Document whereDocKey, String collectionName) {

        MongoCollection<Document> collection = mongodbConnection.getCollection(collectionName);

        FindIterable<Document> findIterable = null;

        if (null == whereDocKey.get("userName")) {
            //查詢全部
            findIterable = collection.find();
        } else {
            //帶有查詢條件
            findIterable = collection.find(whereDocKey);
        }

        MongoCursor<Document> mongoCursor = findIterable.iterator();
        List<SysLog> sysLogList = new ArrayList<>();

        while (mongoCursor.hasNext()) {
            SysLog vo = new SysLog();
            Document document = mongoCursor.next();
            // 將Document轉換為VO
            vo = this.documentToSysLogVo(document);
            sysLogList.add(vo);
        }

        return sysLogList;
    }

    @Override
    public void insert(SysLog sysLog, String collectionName, Document document) {
        MongoCollection<Document> collection = mongodbConnection.getCollection(collectionName);

        List<Document> documents = new ArrayList<Document>();
        documents.add(document);
        collection.insertMany(documents);

        logger.info("noSql資料新增成功");
    }

    public static SysLog documentToSysLogVo(Document document) {
        SysLog vo = new SysLog();
        vo.setUsername(document.getString("userName"));
        vo.setOperation(document.getString("operation"));
        vo.setMethod(document.getString("method"));
        vo.setParams(document.getString("params"));
        vo.setIp(document.getString("ip"));
        vo.setTime(document.getLong("time"));
        vo.setCreateTime(document.getDate("createTime"));
        return vo;
    }
}
