package com.yonon.demo.mongodb;

import com.mongodb.MongoClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by jr-jiangyinghan on 2017-8-29.
 */
public class TransferFileToMongoDB {
    public static void main(String[] args) {
        TransferFileToMongoDB transferFileToMongoDB = new TransferFileToMongoDB();
//        transferFileToMongoDB.deleteDocument("book");
//        transferFileToMongoDB.getFileStream();
        transferFileToMongoDB.printDocument("book");
    }


    public void getFileStream() {
        try {
            String fileName = "D:/mongoDB.txt";
            File localFile = new File(fileName);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(localFile));
            String line;
            StringBuffer content = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
                System.out.println(line);
            }
            System.out.println(localFile.getName() + " is loaded...");
//            writeFile("D:/newFile.txt",content.toString());
            //文档写入
            MongoUtils.getInstance().writeDocument("book","steal shadow",content.toString());
            bufferedReader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void writeFile(String fileName, String content) {
        try {
            System.out.println("开始写入:" + fileName);
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
            System.out.println("写入完成.");
        } catch (Exception ex) {

        }
    }

    public String printDocument(String collectionName){
        return MongoUtils.getInstance().printDocument(collectionName);
    }

    public void deleteDocument(String collectionName){
        MongoUtils.getInstance().deleteDocument(collectionName);
    }
}
