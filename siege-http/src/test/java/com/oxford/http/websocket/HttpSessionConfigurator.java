package com.oxford.http.websocket;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * HttpSession配置类
 *
 * @author Chova
 * @date 2021/04/08
 */
public class HttpSessionConfigurator extends ServerEndpointConfig.Configurator {

    /**
     * 修改WebSocket客户端与服务端握手连接的方法：
     * - 获取客户端与服务端握手连接时的HttpSession
     * - 将获取的HttpSession保存到WebSocket端点配置类ServerEndpointConfig中
     *
     * @param sec      发布到服务器WebSocket端点的配置信息
     * @param request  WebSocket握手连接时的客户端HTTP请求
     * @param response WebSocket握手连接时的响应文
     */
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        // 获取客户端与服务端握手连接时的HttpSession
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        // 将获取到的HttpSession以键值对形式保存到WebSocket端点配置类ServerEndpointConfig中客户端属性userProperties中
        sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
    }
}
