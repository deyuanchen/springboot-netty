package cn.com.sweetcandy.netty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;

@SpringBootApplication
public class NettyApplication implements CommandLineRunner {
	@Autowired
	private ServerBootstrap serverBootstrap;

	public static void main(String[] args) {
		SpringApplication.run(NettyApplication.class, args);
	}

	public void run(String... args) throws Exception {
		ChannelFuture future = serverBootstrap.bind(7878).sync();
		future=serverBootstrap.bind(7979).sync();
	}
}
