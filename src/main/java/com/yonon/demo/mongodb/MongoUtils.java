package com.yonon.demo.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jr-jiangyinghan on 2017-8-30.
 */
public class MongoUtils {
    public static MongoUtils instance;
    private static MongoClient mongoClient;

    public MongoUtils() {
        System.out.println("load instance...");
    }

    public static MongoUtils getInstance() {
        if (null == instance) {
            mongoClient = getClient();
            return new MongoUtils();
        } else
            return instance;
    }

    private static MongoClient getClient() {
        // 连接到 mongodb 服务
        mongoClient = new MongoClient("192.168.80.128", 27017);
        return mongoClient;
    }


    public void writeDocument(String collectionName, String documentName, String content) {
        try {
            MongoCollection<Document> collection = getCollection(collectionName);
            //插入文档
            /**
             * 1. 创建文档 org.bson.Document 参数为key-value的格式
             * 2. 创建文档集合List<Document>
             * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document)
             * */
            Document document = new Document("title", documentName).
                    append("description", content).
                    append("likes", 10000000).
                    append("by", "sys_auto_write");
            List<Document> documents = new ArrayList<Document>();
            documents.add(document);
            collection.insertMany(documents);
            System.out.println("文档" + documentName + "插入成功");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private static MongoCollection<Document> getCollection(String collectionName) {
        MongoCollection<Document> collection = mongoClient.getDatabase("mycol").getCollection(collectionName);
        System.out.println("集合 " + collectionName + " 选择成功");
        return collection;
    }

    public String printDocument(String collectionName) {
        StringBuffer content = new StringBuffer();
        MongoCollection<Document> collection = getCollection(collectionName);
        //检索所有文档
        /**
         * 1. 获取迭代器FindIterable<Document>
         * 2. 获取游标MongoCursor<Document>
         * 3. 通过游标遍历检索出的文档集合
         * */
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while (mongoCursor.hasNext()) {
            String line = mongoCursor.next().toString();
            content.append(line);
            System.out.println(line);
        }
        return content.toString();
    }
    public void deleteDocument(String collectionName){
        MongoCollection<Document> collection = getCollection(collectionName);
        collection.deleteMany (Filters.eq("likes", 100));
    }
}