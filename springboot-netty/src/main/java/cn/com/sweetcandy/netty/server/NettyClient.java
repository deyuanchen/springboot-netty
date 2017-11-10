package cn.com.sweetcandy.netty.server;

import cn.com.sweetcandy.netty.handel.client.HelloClientInitializer;
import cn.com.sweetcandy.netty.model.PersonProbuf;
import cn.com.sweetcandy.netty.model.PersonProbuf.Person;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
	public static void main(String[] args) {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).handler(new HelloClientInitializer());

			// 连接服务端
			Channel ch = b.connect("127.0.0.1", 7878).sync().channel();
			final Person person = PersonProbuf.Person.newBuilder().setAge(25).setName("tangyu").setLocal("beijing")
					.build();
			ch.writeAndFlush(person);
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
	}
}
