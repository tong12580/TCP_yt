package tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) throws IOException {
        String clientSentence;
        String capitalizedSentence;
        //创建连接端口号
        ServerSocket welcomeSocket = new ServerSocket(6789);
        while (true) {
            //在welcomesocket 等待客户连接，并创建connectionsocket
            Socket connectionSocket = welcomeSocket.accept();
            //创建输入流从connectionsocket接收信息
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            //创建输出流，通过connectionSocket输出
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            //接收client的信息
            clientSentence = inFromClient.readLine();
            //转换为大写字符
            capitalizedSentence = clientSentence.toUpperCase() + '\n';
            //发送信息
            outToClient.writeBytes(capitalizedSentence);

        }

    }

}
