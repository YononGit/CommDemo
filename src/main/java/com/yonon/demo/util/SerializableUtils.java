package com.yonon.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by jr-jiangyinghan on 2017-9-15.
 */
public class SerializableUtils {
    private static final Logger logger = LoggerFactory.getLogger(SerializableUtils.class);
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            //���л�
            logger.info("���л�����ʼ:{}",object);
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            logger.info("���л��������:{}",object);
            return bytes;
        } catch (Exception e) {
            logger.info("���л������쳣:{}",object);
        }
        return null;
    }

    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            //�����л�
            logger.info("�����л�����ʼ,{}",bytes);
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            logger.info("�����л��������,{}",bytes);
            return ois.readObject();
        } catch (Exception e) {
            logger.info("�����л������쳣,{}",bytes);
        }
        return null;
    }
}
