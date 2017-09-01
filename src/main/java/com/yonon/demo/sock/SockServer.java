package com.yonon.demo.sock;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jr-jiangyinghan on 2017-8-1.
 */
public class SockServer {
    private int port = 1122;
    private ServerSocket serverSocket;

    public SockServer() throws Exception {
        serverSocket = new ServerSocket(port, 3);
        System.out.println("service is run...");
    }

    public void service() {
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                System.out.println("New connection accepted " +
                        socket.getInetAddress() + ":" + socket.getPort());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        SockServer server = new SockServer();
        Thread.sleep(60000 * 10);
        server.service();
    }
}
