package com.sumCo.common.utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnector {
    public static MongoDatabase getConnection() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("local");
        return mongoDatabase;
    }
}
