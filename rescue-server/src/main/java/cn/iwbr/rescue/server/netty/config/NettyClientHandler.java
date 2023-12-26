package cn.iwbr.rescue.server.netty.config;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;

/**
 * @description: 自定义NettyHandler
 * @author: <a href="mailto:wangbaorui@gtmap.cn">wangbaorui</a>
 * @version: 3.0.0
 * @date: 2023/12/26
 */
@Configuration
@Slf4j
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        //发送消息到服务端
        ctx.writeAndFlush(Unpooled.copiedBuffer("歪比巴卜~茉莉~Are you good~马来西亚~", Charset.forName("UTF-8")));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        //接收服务端发送过来的消息
        ByteBuf byteBuf = (ByteBuf) msg;
        log.info("收到服务端：【{}】发送的消息：【{}】", ctx.channel().remoteAddress(), byteBuf.toString(Charset.forName("UTF-8")));
    }
}
