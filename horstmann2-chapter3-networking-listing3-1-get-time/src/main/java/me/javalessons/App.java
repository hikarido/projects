package me.javalessons;

import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        try(Socket s = new Socket("time-A.timefreq.bldrdoc.gov", 13)){
			Scanner in = new Scanner(s.getInputStream());
			while(in.hasNext()){
				System.out.println(in.nextLine());
			}
		}
    }
}
