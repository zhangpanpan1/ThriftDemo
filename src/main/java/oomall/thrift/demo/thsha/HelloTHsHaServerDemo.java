package oomall.thrift.demo.thsha;

 
import oomall.thrift.demo.HelloWorldImpl;
import oomall.thrift.demo.HelloWorldService;
import oomall.thrift.demo.HelloWorldService.Iface;
import oomall.thrift.demo.HelloWorldService.Processor;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
 
/**
 * blog http://www.micmiu.com
 *
 * @author Michael
 *
 */
public class HelloTHsHaServerDemo {
	public static final int SERVER_PORT = 8090;
 
	public void startServer() {
		try {
			System.out.println("HelloWorld THsHaServer start ....");
 
			TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(
					new HelloWorldImpl());
 
			TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(
					SERVER_PORT);
			THsHaServer.Args thhsArgs = new THsHaServer.Args(tnbSocketTransport);
			thhsArgs.processor(tprocessor);
			thhsArgs.transportFactory(new TFramedTransport.Factory());
			thhsArgs.protocolFactory(new TBinaryProtocol.Factory());
 
			//半同步半异步的服务模型
			TServer server = new THsHaServer(thhsArgs);
			server.serve();
 
		} catch (Exception e) {
			System.out.println("Server start error!!!");
			e.printStackTrace();
		}
	}
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HelloTHsHaServerDemo server = new HelloTHsHaServerDemo();
		server.startServer();
	}
 
}