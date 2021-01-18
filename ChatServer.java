import java.io.*;
import java.net.*;

public class ChatServer {
	public static void main(String[] args) {
		ChatServer chatserver = new ChatServer();
	}
	
	public ChatServer() {
		server s = new server();
		s.start();
	}
	
	class server extends Thread{
		public synchronized void run() {
			try {
				ServerSocket sk = new ServerSocket(7523);
				System.out.println("��Ѥ��e�p�U :\n");
				Socket insk = sk.accept();
				BufferedReader br = new BufferedReader(new InputStreamReader(insk.getInputStream()));
				PrintWriter output = new PrintWriter(insk.getOutputStream(), true);
				BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
				
				Thread msgReceiver = new Thread(new Runnable() {
					public void run() {
						String str;
						try {
							while((str = br.readLine()) != null) {
								if(str.length() > 0) System.out.println("�Ȥ�ݡ@>>�@" + str);
							}
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
				});
				msgReceiver.start();
				
				while(true) {
					String ss = buff.readLine();
					output.write(ss + "\n");
					output.flush();
				}
			}catch(Exception e) {
				
			}
		}
	}
}
