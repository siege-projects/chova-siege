package com.oxford.http.websocket.config;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket连接配置
 * - @ServerEndpoint可以将当前类设置为WebSocket服务类
 * - @ServerEndpoint注解是一个类层次注解
 * - 作用是将当前类定义成一个WebSocket服务器端
 * - 注解的值监听用户连接的客户端的访问的URL地址
 * - 客户端可以通过这个URL来连接到WebSocket服务器端
 *
 * @author Chova
 * @date 2021/1/8
 */
@Component
@ServerEndpoint("/websocket/{user}")
public class WebSocket {

    /**
     * 记录当前的在线连接数
     * 静态变量,应该设置为线程安全的
     */
    private static int onlineCount = 0;

    /**
     * 使用ConcurrentHashMap存放连接的WebSocket,其中key是用户ID
     * concurrent包中的线程安全的Set可以用来存放每个客户端对象的WebSocket对象
     */
    private static ConcurrentHashMap<String, WebSocket> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 客户端的连接会话,用于给客户端发送数据
     */
    private Session session;

    /**
     * 当前人员ID
     */
    private String userId;

    /**
     * 获取当前在线人数
     *
     * @return int 当前在线人数
     */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    /**
     * 增加一位当前在线人数
     */
    public static synchronized void addOnlineCount() {
        onlineCount++;
    }

    /**
     * 减少一位当前在线人数
     */
    public static synchronized void subOnlineCount() {
        onlineCount--;
    }

    /**
     * Websocket连接建立成功时调用的方法
     *
     * @param user    客户端传入的用户ID标识
     * @param session 客户端连接会话
     */
    @OnOpen
    public void onOpen(@PathParam("user") String user, Session session) {
        userId = user;
        this.session = session;
        // 将WebSocket连接的人员和WebSocket存放的webSocketMap中
        webSocketMap.put(userId, this);
        // 当前在线人数+1
        addOnlineCount();
        System.out.println("用户:" + userId + "加入连接. 当前在线人数:" + getOnlineCount());
    }

    /**
     * WebSocket接收到客户端请求信息时调用的方法
     *
     * @param message 客户端请求信息
     * @param session 可选Session参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("客户端传入的消息:" + message);
        // 消息内容
        String sendMessage = message.split("-")[0];
        // 用户ID
        String userId = message.split("-")[1];
        // 给指定的用户发送消息
        sendToUser(userId, sendMessage);
    }

    /**
     * WebSocket连接关闭时调用的方法
     */
    @OnClose
    public void onClose() {
        if (!userId.isEmpty()) {
            // 将WebSocket连接的人员和WebSocket从webSocketMap中移除
            webSocketMap.remove(userId);
            // 当前在线人数-1
            subOnlineCount();
            System.out.println("用户:" + userId + "关闭连接. 当前在线人数:" + getOnlineCount());
        }
    }

    /**
     * WebSocket异常时调用的方法
     *
     * @param session Session参数
     * @param error   WebSocket错误
     */
    @OnError
    public void OnError(Session session, Throwable error) {
        System.out.println("WebSocket异常:" + error.getMessage());
    }

    /**
     * 发送消息
     *
     * @param message 消息内容
     */
    public void sendMessage(String message) throws IOException {
        /*
         * getBasicRemote() - 同步发送消息
         * getAsyncRemote() - 异步发送消息
         */
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 给指定的用户发送消息
     *
     * @param toUserId    消息送达的用户ID
     * @param sendMessage 发送的消息
     */
    public void sendToUser(String toUserId, String sendMessage) {
        try {
            if (null != webSocketMap.get(toUserId)) {
                webSocketMap.get(toUserId).sendMessage("来自用户" + userId + "发送的消息,内容:" + sendMessage);
            } else if (null != webSocketMap.get(userId)) {
                webSocketMap.get(userId).sendMessage("用户" + toUserId + "已经离线,未接收到消息.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 给所有用户发送消息
     *
     * @param message 发送的消息
     */
    public void sendToAll(String message) {
        try {
            for (String key : webSocketMap.keySet()) {
                webSocketMap.get(key).sendMessage("来自用户" + userId + "发送的消息, 内容:" + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
