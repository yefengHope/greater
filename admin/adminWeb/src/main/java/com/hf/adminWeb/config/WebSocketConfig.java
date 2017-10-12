package com.hf.adminWeb.config;// package com.fengyu.config;
//
// import org.apache.log4j.Logger;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.messaging.converter.MessageConverter;
// import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
// import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
// import org.springframework.messaging.simp.config.ChannelRegistration;
// import org.springframework.messaging.simp.config.MessageBrokerRegistry;
// import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
// import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
// import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
// import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
//
// import java.util.List;
//
// @Configuration
// @EnableWebSocketMessageBroker
// public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//
//     private static Logger logger = Logger.getLogger(WebSocketConfig.class);
//
//     @Override
//     public void registerStompEndpoints(StompEndpointRegistry registry) {
//         //添加这个Endpoint，这样在网页中就可以通过websocket连接上服务了
//         registry.addEndpoint("/socket").withSockJS();
//     }
//
//     @Override
//     public void configureWebSocketTransport(WebSocketTransportRegistration webSocketTransportRegistration) {
//
//     }
//
//     @Override
//     public void configureMessageBroker(MessageBrokerRegistry config) {
//         logger.info(" >>>>>>>>>>  : WebSocket服务代理启动成功! ");
//         /*
//             这里设置的simple broker是指可以订阅的地址，也就是服务器可以发送的地址
//             enableSimpleBroker不能适用集群
//          */
//         /**
//          * 客户端接收服务端消息的地址的前缀信息
//          * @param destinationPrefixes "/topic","/queue",...
//          */
//         config.enableSimpleBroker("/topic","/queue");
//         /**
//          * 指服务端接收地址的前缀，意思就是说客户端给服务端发消息的地址的前缀
//          */
//         config.setApplicationDestinationPrefixes("/app");
//     }
//
//     @Override
//     public void configureClientInboundChannel(ChannelRegistration channelRegistration) {
//     }
//
//     @Override
//     public void configureClientOutboundChannel(ChannelRegistration channelRegistration) {
//
//     }
//
//     @Override
//     public void addArgumentResolvers(List<HandlerMethodArgumentResolver> list) {
//
//     }
//
//     @Override
//     public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> list) {
//
//     }
//
//     @Override
//     public boolean configureMessageConverters(List<MessageConverter> list) {
//         return false;
//     }
// }
