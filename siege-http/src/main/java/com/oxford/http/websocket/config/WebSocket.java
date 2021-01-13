package com.oxford.http.websocket.config;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
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

    @OnMessage
    public void onMessage(String message, Session session) {

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
}
