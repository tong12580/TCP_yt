package tcp;

import java.io.*;
import java.net.*;


public class TCPClient {

	public static void main(String[] args) throws  Exception  {
		String sentence;
		String modifiedSentence;
		
		//�������������������룩
	
		BufferedReader inFromUser =new BufferedReader(new InputStreamReader(System.in));
	
	
		//����clientsocket���� server
		 Socket	clientSocket= new Socket(InetAddress.getLocalHost(),6789);
			
		//�����������ͨ��clientsocket�����
		DataOutputStream outToServer=new DataOutputStream(clientSocket.getOutputStream());
			
		//���������� ׼����clientsocket������Ϣ
		BufferedReader inFromServer =new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			//�Ӽ��̶�ȡ��Ϣ
			System.out .println("�ӿͻ�������Сд�ַ�:");
			sentence =inFromUser.readLine();
			
			//������Ϣ��server
			outToServer.writeBytes(sentence+'\n');
			//��server������Ϣ
			modifiedSentence =inFromServer.readLine();
			//��ʾ
			System.out.println("�ӷ����������д�ַ�: "+modifiedSentence);
	
		clientSocket.close();
	}

}
