package com.yonon.demo.mongodb;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jr-jiangyinghan on 2017-8-22.
 */
public class MongoDB {
    public static void main(String args[]) {
        find();
    }

    // 新增文档
    private static void insert() {
        try {
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient("192.168.80.128", 27017);

            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
            System.out.println("Connect to database successfully");

            MongoCollection<Document> collection = mongoDatabase.getCollection("test");
            System.out.println("集合 test 选择成功");
            //插入文档
            /**
             * 1. 创建文档 org.bson.Document 参数为key-value的格式
             * 2. 创建文档集合List<Document>
             * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document)
             * */
            Document document = new Document("title", "MongoDB").
                    append("description", "database").
                    append("likes", 100).
                    append("by", "Fly");
            List<Document> documents = new ArrayList<Document>();
            documents.add(document);
            collection.insertMany(documents);
            System.out.println("文档插入成功");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    // 检索所有文档
    public static void find() {
        try {
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient("192.168.80.128", 27017);

            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
            System.out.println("Connect to database successfully");

            MongoCollection<Document> collection = mongoDatabase.getCollection("test");
            System.out.println("集合 test 选择成功");

            //检索所有文档
            /**
             * 1. 获取迭代器FindIterable<Document>
             * 2. 获取游标MongoCursor<Document>
             * 3. 通过游标遍历检索出的文档集合
             * */
            FindIterable<Document> findIterable = collection.find();
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while (mongoCursor.hasNext()) {
                System.out.println(mongoCursor.next());
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void getGridFS() {
        try {
            MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
            builder.connectionsPerHost(50);   //与目标数据库能够建立的最大connection数量为50
//        builder.autoConnectRetry(true);   //自动重连数据库启动
            builder.threadsAllowedToBlockForConnectionMultiplier(50); //如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待
            /*
             * 一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间为2分钟
             * 这里比较危险，如果超过maxWaitTime都没有获取到这个连接的话，该线程就会抛出Exception
             * 故这里设置的maxWaitTime应该足够大，以免由于排队线程过多造成的数据库访问失败
             */
            builder.maxWaitTime(1000 * 60 * 2);
            builder.connectTimeout(1000 * 60 * 1);    //与数据库建立连接的timeout设置为1分钟
            MongoClientOptions mongoClientOptions = builder.build();
            ServerAddress serverAddress = new ServerAddress("192.168.80.128");
//            Mongo mongo = new Mongo(serverAddress, mongoClientOptions);
//        DB db = new DB(


//                GridFS gridFS = new GridFS();

//            MongoFiles mongoFiles = new MongoFiles();
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }
}
