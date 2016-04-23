package homework1;

import java.io.*;

public class Nuke2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String text;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		text = in.readLine();
		
		System.out.println(text.charAt(0) + text.substring(2));
		
	}

}
