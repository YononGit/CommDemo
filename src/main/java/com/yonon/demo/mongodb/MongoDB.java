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

    // �����ĵ�
    private static void insert() {
        try {
            // ���ӵ� mongodb ����
            MongoClient mongoClient = new MongoClient("192.168.80.128", 27017);

            // ���ӵ����ݿ�
            MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
            System.out.println("Connect to database successfully");

            MongoCollection<Document> collection = mongoDatabase.getCollection("test");
            System.out.println("���� test ѡ��ɹ�");
            //�����ĵ�
            /**
             * 1. �����ĵ� org.bson.Document ����Ϊkey-value�ĸ�ʽ
             * 2. �����ĵ�����List<Document>
             * 3. ���ĵ����ϲ������ݿ⼯���� mongoCollection.insertMany(List<Document>) ���뵥���ĵ������� mongoCollection.insertOne(Document)
             * */
            Document document = new Document("title", "MongoDB").
                    append("description", "database").
                    append("likes", 100).
                    append("by", "Fly");
            List<Document> documents = new ArrayList<Document>();
            documents.add(document);
            collection.insertMany(documents);
            System.out.println("�ĵ�����ɹ�");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    // ���������ĵ�
    public static void find() {
        try {
            // ���ӵ� mongodb ����
            MongoClient mongoClient = new MongoClient("192.168.80.128", 27017);

            // ���ӵ����ݿ�
            MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
            System.out.println("Connect to database successfully");

            MongoCollection<Document> collection = mongoDatabase.getCollection("test");
            System.out.println("���� test ѡ��ɹ�");

            //���������ĵ�
            /**
             * 1. ��ȡ������FindIterable<Document>
             * 2. ��ȡ�α�MongoCursor<Document>
             * 3. ͨ���α�������������ĵ�����
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
            builder.connectionsPerHost(50);   //��Ŀ�����ݿ��ܹ����������connection����Ϊ50
//        builder.autoConnectRetry(true);   //�Զ��������ݿ�����
            builder.threadsAllowedToBlockForConnectionMultiplier(50); //�����ǰ���е�connection����ʹ���У���ÿ��connection�Ͽ�����50���߳��Ŷӵȴ�
            /*
             * һ���̷߳������ݿ��ʱ���ڳɹ���ȡ��һ���������ݿ�����֮ǰ����ȴ�ʱ��Ϊ2����
             * ����Ƚ�Σ�գ��������maxWaitTime��û�л�ȡ��������ӵĻ������߳̾ͻ��׳�Exception
             * ���������õ�maxWaitTimeӦ���㹻�����������Ŷ��̹߳�����ɵ����ݿ����ʧ��
             */
            builder.maxWaitTime(1000 * 60 * 2);
            builder.connectTimeout(1000 * 60 * 1);    //�����ݿ⽨�����ӵ�timeout����Ϊ1����
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
