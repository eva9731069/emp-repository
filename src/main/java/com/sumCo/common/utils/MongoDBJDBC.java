package com.sumCo.common.utils;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDBJDBC {
    public static void main(String args[]) {
        try {
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient("localhost", 27017);

            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("local");
            System.out.println("Connect to database successfully");

//            //新增collection
//            mongoDatabase.createCollection("test");
//            System.out.println("集合创建成功");

            //選擇collection
            MongoCollection<Document> collection = mongoDatabase.getCollection("test");
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

            //查詢
//            FindIterable<Document> findIterable = collection.find();
//            MongoCursor<Document> mongoCursor = findIterable.iterator();
//            while(mongoCursor.hasNext()){
//                System.out.println(mongoCursor.next());
//            }

            //更新document
            collection.updateMany(Filters.eq("likes", 100), new Document("$set",new Document("likes",200)));

            //删除符合条件的第一个document
            collection.deleteOne(Filters.eq("likes", 200));

            //删除所有符合条件的document
            collection.deleteMany (Filters.eq("likes", 200));


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
