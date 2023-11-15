package com.sumCo.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.sumCo.modules.sys.entity.SysLog;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MongoDBJDBC {
    public static void main(String args[]) throws IOException {

            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient("localhost", 27017);

            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("local");
            System.out.println("Connect to database successfully");

//            //新增collection
//            mongoDatabase.createCollection("test");
//            System.out.println("集合创建成功");

            //選擇collection
            MongoCollection<Document> collection = mongoDatabase.getCollection("sysLog");
            System.out.println("集合 test 选择成功");

            //新增document
//            Document document = new Document("title", "MongoDBttt").
//                    append("description", "databasettt").
//                    append("likes", 100333).
//                    append("by", "Flyttt");
//            List<Document> documents = new ArrayList<Document>();
//            documents.add(document);
//            collection.insertMany(documents);
//            System.out.println("文档插入成功");


        Document whereQ = new Document();
        whereQ.put("userName","admin");
            //查詢
            FindIterable<Document> findIterable = collection.find();
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while(mongoCursor.hasNext()){
                System.out.println(mongoCursor.next());

//                Document document = mongoCursor.next();
//
//                // 將Document轉換為VO
//                SysLog vo = convertDocumentToVO(document);
//                System.out.println(vo.getMethod());
//                System.out.println(vo.getOperation());
//                System.out.println(vo.getTime());
//                System.out.println(vo.getCreateTime());
            }
            ObjectMapper objectMapper = new ObjectMapper();

//
//            SysLog sysLog = new SysLog();
//
//            String json = objectMapper.writeValueAsString(sysLog);
//
//        Gson gson = new Gson();
//        String json = "";
//            for(Object data:documentList){
//                 json = gson.toJson(data);
//            }
//
//        SysLog user2 = objectMapper.readValue(json, SysLog.class);
//        System.out.println(user2.getMethod());
//        System.out.println("22121");



            //更新document
//            collection.updateMany(Filters.eq("likes", 100), new Document("$set",new Document("likes",200)));
//
//            //删除符合条件的第一个document
//            collection.deleteOne(Filters.eq("likes", 200));
//
//            //删除所有符合条件的document
//            collection.deleteMany (Filters.eq("likes", 200));



    }

    public static SysLog convertDocumentToVO(Document document) {
        SysLog vo = new SysLog();

        vo.setOperation(document.getString("operation"));
        vo.setMethod(document.getString("method"));
        vo.setParams(document.getString("params"));
        vo.setIp(document.getString("ip"));
        vo.setTime(document.getLong("time"));
vo.setCreateTime(document.getDate("createTime"));

        return vo;
    }
}
