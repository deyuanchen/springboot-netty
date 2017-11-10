/**
 * 
 */
package cn.com.sweetcandy.netty.handel.client;

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
 * @date 2017年9月6日 下午5:48:53
 */
public class HelloClientInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();

		/*
		 * 这个地方的 必须和服务端对应上。否则无法正常解码和编码
		 * 
		 * 解码和编码 我将会在下一张为大家详细的讲解。再次暂时不做详细的描述
		 * 
		 */
		// pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192,
		// Delimiters.lineDelimiter()));
		// pipeline.addLast("decoder", new StringDecoder());
		// pipeline.addLast("encoder", new StringEncoder());
		ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());// 解码(处理半包)
        ch.pipeline().addLast(new ProtobufDecoder(PersonProbuf.Person.getDefaultInstance()));
        ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());//加长度
        ch.pipeline().addLast(new ProtobufEncoder());// 编码
		// 客户端的逻辑
		pipeline.addLast("handler", new HelloClientHandler());
	}
}
