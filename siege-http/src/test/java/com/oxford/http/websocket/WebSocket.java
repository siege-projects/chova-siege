package com.oxford.http.websocket;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.oxford.http.json.JsonConvert.convertJsonToObject;
import static com.oxford.http.json.JsonConvert.convertObjectToJson;

/**
 * WebSocket类
 *
 * @author Chova
 * @date 2021/04/06
 */
@ServerEndpoint(value = "/websocket", configurator = HttpSessionConfigurator.class)
public class WebSocket {

    /**
     * 客户端连接会话，用于传递数据
     */
    private static Session session;

    /**
     * Servlet中的HTTP连接会话
     * 用户登录后的连接会话
     */
    private static HttpSession httpSession;

    /**
     * 使用ConcurrentHashMap存放已经连接成功的WebSocket连接
     * 其中key是连接的客户端用户信息，value为对应的WebSocket连接实例
     */
    private static ConcurrentHashMap<HttpSession, WebSocket> onlineUser = new ConcurrentHashMap<>();
    /**
     * 当前在线连接数
     * 应该设置成一个线程安全的变量
     */
    private static int onlineCount = 0;

    /**
     * WebSocket连接成功时调用的方法
     * - 记录WebSocket的当前会话信息Session
     * - 获取当前登录用户的HttpSession信息
     *
     * @param session WebSocket当前会话信息
     * @param config  WebSocket的配置信息œ
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        // 记录WebSocket的当前会话信息Session
        this.session = session;

        // 获取当前登录用户的HttpSession信息
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.httpSession = httpSession;

        // 记录当前用户的登录信息和对应的WebSocket实例
        if (null != httpSession.getAttribute("user")) {
            onlineUser.put(httpSession, this);
        }

        // 通知所有登录用户当前在线人员
        String onlineUsers = getAllOnlineUser();
        String toMessage = toMessage("user", "", "", onlineUsers);
        sendToAll(toMessage);
        // 系统在线人员增加1
        addOnlineCount();
        getOnlineCount();
    }

    /**
     * WebSocket接收到客户端消息时调用的方法
     *
     * @param message 客户端消息
     * @param session 客户端连接会话
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        // 获取客户端传入的消息内容，并解析消息
        Map<String, String> messageMap = convertJsonToObject(message, Map.class);
        String content = messageMap.get("content");
        String fromUser = messageMap.get("fromUser");
        String toUser = messageMap.get("toUser");
        String toMessage = toMessage("message", fromUser, toUser, content);
        // 判断消息类型
        if ("all".equals(toUser)) {
            // 如果是消息类型是群发，则给所有人发送消息
            sendToAll(toMessage);
        } else {
            // 如果是指定消息，则给指定的用户发送消息
            sendToUser(toMessage, fromUser, toUser);
        }
    }

    /**
     * WebSocket连接关闭时调用的方法
     *
     * @param session     客户端连接会话
     * @param closeReason 关闭理由
     */
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        // 将当前客户端会话的WebSocket连接移除
        onlineUser.remove(httpSession);
        // 用户数减少1
        subOnlineCount();
        getOnlineCount();
    }

    /**
     * WebSocket连接异常时调用的方法
     *
     * @param session   客户端连接会话
     * @param throwable 抛出的异常
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    /**
     * 给指定的用户发送消息
     *
     * @param message  消息内容
     * @param fromUser 消息发送人
     * @param toUser   消息送达人
     */
    public static void sendToUser(String message, String fromUser, String toUser) {
        if (null != toUser && toUser.isEmpty()) {
            // 判断用户是否在线
            boolean isOnline = false;
            for (HttpSession userSession : onlineUser.keySet()) {
                if (toUser.equals(userSession.getAttribute("user"))) {
                    isOnline = true;
                }
            }
            try {
                if (isOnline) {
                    // 如果用户在线则给发送人和送达人发送消息，消息在发送人和送达人中都需要显示
                    for (HttpSession userSession : onlineUser.keySet()) {
                        if (userSession.getAttribute("user").equals(fromUser) || userSession.getAttribute("user").equals(toUser)) {
                            onlineUser.get(httpSession).session.getBasicRemote().sendText(message);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给所有用户发送广播消息
     *
     * @param message 广播消息内容
     */
    public static void sendToAll(String message) {
        try {
            for (HttpSession userSession : onlineUser.keySet()) {
                onlineUser.get(userSession).session.getBasicRemote().sendText(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取当前在线人数
     *
     * @return int 当前在线人数
     */
    public static synchronized int getOnlineCount() {
        System.out.println("当前在线人数：" + onlineCount);
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
     * 获取WebSocket连接中的所有登录用户
     *
     * @return String 以逗号形式分割的所有登录用户
     */
    private String getAllOnlineUser() {
        String onlineUsers = "";
        for (HttpSession httpSession : onlineUser.keySet()) {
            String user = (String) httpSession.getAttribute("username");
            onlineUsers += user + ",";
        }
        return onlineUsers.substring(0, onlineUsers.length() - 1);
    }

    /**
     * 将消息内容封装成指定格式的Json字符串
     *
     * @param type     消息类型
     * @param fromUser 发送人
     * @param toUser   送达人
     * @param content  消息内容
     * @return String 消息内容指定格式的Json字符串
     */
    private String toMessage(String type, String fromUser, String toUser, String content) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("type", type);
        dataMap.put("data", content);
        dataMap.put("fromUser", fromUser);
        dataMap.put("toUser", toUser);
        // 将Map对象转换成Json字符串数据
        return convertObjectToJson(dataMap);
    }
}
