package oomall.thrift.demo.tnonblock;

 
import oomall.thrift.demo.HelloWorldService;
import oomall.thrift.demo.HelloWorldService.Client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
 
/**
 * blog http://www.micmiu.com
 *
 * @author Michael
 *
 */
public class HelloNonblockClientDemo {
 
	public static final String SERVER_IP = "localhost";
	public static final int SERVER_PORT = 8090;
	public static final int TIMEOUT = 30000;
 
	/**
	 *
	 * @param userName
	 */
	public void startClient(String userName) {
		TTransport transport = null;
		try {
			transport = new TFramedTransport(new TSocket(SERVER_IP,
					SERVER_PORT, TIMEOUT));
			// 协议要和服务端一致
			TProtocol protocol = new TCompactProtocol(transport);
			HelloWorldService.Client client = new HelloWorldService.Client(
					protocol);
			transport.open();
			String result = client.sayHello(userName);
			System.out.println("Thrify client result =: " + result);
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		} finally {
			if (null != transport) {
				transport.close();
			}
		}
	}
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HelloNonblockClientDemo client = new HelloNonblockClientDemo();
		client.startClient("Michael");
 
	}
 
}