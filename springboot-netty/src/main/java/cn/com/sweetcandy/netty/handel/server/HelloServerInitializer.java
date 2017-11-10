/**
 * 
 */
package cn.com.sweetcandy.netty.handel.server;

import org.springframework.stereotype.Component;

import cn.com.sweetcandy.netty.model.PersonProbuf;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author tangyu
 * @date 2017年9月6日 下午5:39:39
 */
@Component
public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		final String connectUrl = ch.localAddress().toString();
		final String port = connectUrl.substring(connectUrl.lastIndexOf(':') + 1);
		System.out.println("被访问的端口为：" + port);
		ChannelPipeline pipeline = ch.pipeline();
		// 以("\n")为结尾分割的 解码器
		//pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
		// 字符串解码 和 编码
		//pipeline.addLast("decoder", new StringDecoder());
		//pipeline.addLast("encoder", new StringEncoder());
		ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());// 解码(处理半包)
		ch.pipeline().addLast(new ProtobufDecoder(PersonProbuf.Person.getDefaultInstance()));
		ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());// 加长度
		ch.pipeline().addLast(new ProtobufEncoder());// 编码
		// 自己的逻辑Handler
		pipeline.addLast("handler", new ServerHandel());
	}

}
