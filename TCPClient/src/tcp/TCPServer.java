package tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		String clientSentence;
		String capitalizedSentence;
		//�������Ӷ˿ں�
		ServerSocket welcomeSocket =new ServerSocket(6789);
		while (true) {
		//��welcomesocket �ȴ��ͻ����ӣ�������connectionsocket
			Socket connectionSocket =welcomeSocket.accept();
		//������������connectionsocket������Ϣ
			BufferedReader inFromClient= new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		//�����������ͨ��connectionSocket���
			DataOutputStream outToClient=new DataOutputStream(connectionSocket.getOutputStream());
		//����client����Ϣ
			clientSentence =inFromClient.readLine();
		//ת��Ϊ��д�ַ�
			capitalizedSentence=clientSentence.toUpperCase()+'\n';
		//������Ϣ	
			outToClient.writeBytes(capitalizedSentence);
			
		}

	}

}
