package tcp;

import java.io.*;
import java.net.*;


public class TCPClient {

	public static void main(String[] args) throws  Exception  {
		String sentence;
		String modifiedSentence;
		
		//创建输入流（键盘输入）
	
		BufferedReader inFromUser =new BufferedReader(new InputStreamReader(System.in));
	
	
		//创建clientsocket连接 server
		 Socket	clientSocket= new Socket(InetAddress.getLocalHost(),6789);
			
		//创建输出流（通过clientsocket输出）
		DataOutputStream outToServer=new DataOutputStream(clientSocket.getOutputStream());
			
		//创建输入流 准备从clientsocket接收信息
		BufferedReader inFromServer =new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			//从键盘读取信息
			System.out .println("从客户端输入小写字符:");
			sentence =inFromUser.readLine();
			
			//发送信息到server
			outToServer.writeBytes(sentence+'\n');
			//从server接收信息
			modifiedSentence =inFromServer.readLine();
			//显示
			System.out.println("从服务器输出大写字符: "+modifiedSentence);
	
		clientSocket.close();
	}

}
