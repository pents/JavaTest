import java.io.*;
import java.net.*;
import java.util.*;


public class AdvicerServer
{
	ArrayList < String > adviceList;
	
	public void search()
	{
		adviceList = new ArrayList<String>();
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(new File("Text.txt")));
			String Line;
			
			while ((Line = reader.readLine()) != null)
			{
				adviceList.add(Line);
			}
			reader.close();
		}catch (Exception ex) {ex.printStackTrace();}
		

	}
	
	public void go()
	{
		try
		{
			ServerSocket serverSocket = new ServerSocket(4242);
			
			while (true)
			{
				Socket sock = serverSocket.accept();
				
				PrintWriter writer = new PrintWriter(sock.getOutputStream());
				
				writer.println(getAdvice());
				writer.close();
				//System.out.println();
			}
		}catch(Exception ex) {ex.printStackTrace();}
	}
	
	public String getAdvice(){
		return adviceList.get((int) (Math.random() * adviceList.size()));
	}
	
	public static void main(String[] args)
	{
		AdvicerServer server = new AdvicerServer();
		server.search();
		//System.out.println(server.getAdvice());
		server.go();
	}
}