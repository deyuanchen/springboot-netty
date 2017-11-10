/**
 * 
 */
package cn.com.sweetcandy.netty.handel.client;

import cn.com.sweetcandy.netty.model.PersonProbuf.Person;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author tangyu
 * @date 2017年9月6日 下午5:49:32
 */
public class HelloClientHandler extends SimpleChannelInboundHandler<Person> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Person msg) throws Exception {

		System.out.println("Server say : " + msg);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Client active ");
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Client close ");
		super.channelInactive(ctx);
	}
}
