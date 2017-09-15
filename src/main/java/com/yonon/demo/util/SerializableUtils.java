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
            //序列化
            logger.info("序列化对象开始:{}",object);
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            logger.info("序列化对象结束:{}",object);
            return bytes;
        } catch (Exception e) {
            logger.info("序列化对象异常:{}",object);
        }
        return null;
    }

    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            //反序列化
            logger.info("反序列化对象开始,{}",bytes);
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            logger.info("反序列化对象结束,{}",bytes);
            return ois.readObject();
        } catch (Exception e) {
            logger.info("反序列化对象异常,{}",bytes);
        }
        return null;
    }
}
