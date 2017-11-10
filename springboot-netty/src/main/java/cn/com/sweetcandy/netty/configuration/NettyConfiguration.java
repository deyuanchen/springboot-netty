package cn.com.sweetcandy.netty.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.com.sweetcandy.netty.handel.server.HelloServerInitializer;
import cn.com.sweetcandy.netty.handel.server.ServerHandel;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/****
 * 
 * @author tangyu
 * @date 2017年9月6日 下午4:48:06
 */
@Configuration
public class NettyConfiguration {
	@Value("${netty.bossCount}")
	private int bossCount;
	@Value("${netty.workerCount}")
	private int workerCount;
	@Autowired
	private HelloServerInitializer helloServerInitializer;

	@Bean
	public ServerBootstrap bootstrap() {
		ServerBootstrap bootstrap = new ServerBootstrap();

		bootstrap.group(bossGroup(), workerGroup()).channel(NioServerSocketChannel.class)
				.handler(new LoggingHandler(LogLevel.INFO)).childHandler(helloServerInitializer);
		return bootstrap;
	}

	@Bean(name = "bossGroup", destroyMethod = "shutdownGracefully")
	public NioEventLoopGroup bossGroup() {
		return new NioEventLoopGroup(bossCount);
	}

	@Bean(name = "workerGroup", destroyMethod = "shutdownGracefully")
	public NioEventLoopGroup workerGroup() {
		return new NioEventLoopGroup(workerCount);
	}
}
