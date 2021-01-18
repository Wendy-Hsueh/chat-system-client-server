import java.io.*;
import java.net.*;

public class ChatClient 
{
	public static void main(String[] args) {ChatClient chatclient = new ChatClient();}
	
	public ChatClient() {
		client s = new client();
		s.start();
	}
	
	class client extends Thread{
		public synchronized void run() 
		{
			try 
			{
				Socket sock = new Socket("localhost", 7523);
				System.out.println("��Ѥ��e�p�U :\n");
				BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				PrintWriter output = new PrintWriter(sock.getOutputStream(), true);
				BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
				
				Thread msgReceiver = new Thread(new Runnable() 
				{
					public void run()
					{
						String str;
						try {
							while((str = br.readLine()) != null) 
							{
								if(str.length() > 0) System.out.println("���A���@>>�@" + str);
							}
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
				});
				msgReceiver.start();
				
				while(true) 
				{
					String ss = buff.readLine();
					output.write(ss + "\n");
					output.flush();
				}
			}catch(Exception e) {
				
			}
		}
	}
}
