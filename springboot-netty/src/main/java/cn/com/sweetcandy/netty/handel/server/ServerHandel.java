/**
 * 
 */
package cn.com.sweetcandy.netty.handel.server;

import java.net.InetAddress;

import org.springframework.stereotype.Component;

import cn.com.sweetcandy.netty.model.PersonProbuf.Person;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author tangyu
 * @date 2017年9月6日 下午5:17:22
 */
@Component
public class ServerHandel extends SimpleChannelInboundHandler<Person> {

	@Override
	protected void channelRead0(ChannelHandlerContext context, Person message) throws Exception {
		System.out.println(context.channel().remoteAddress() + " message:" + message);
		//context.writeAndFlush("Received your message !it's : " + message + "\n");
		context.writeAndFlush(message);
	}

	/*
	 * 
	 * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
	 * 
	 * channelActive 和 channelInActive 在后面的内容中讲述，这里先不做详细的描述
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");
		//ctx.writeAndFlush("Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("客户端下线：" + ctx.channel().remoteAddress());
		ctx.fireChannelInactive();
	}
}
