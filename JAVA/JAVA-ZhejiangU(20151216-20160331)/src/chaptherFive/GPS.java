package chaptherFive;

import java.util.Scanner;

public class GPS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		String signal;
		String newtime="";
		do
		{
			signal = in.nextLine();
			if(signal.startsWith("$GPRMC"))
			{
				String[] list = signal.split(",");
				String time = list[1];
				
				if(list[2].equals("A"))
				{
					String newsig = signal.substring(1, signal.indexOf('*'));
					int result = newsig.charAt(0);
					for(int i = 1; i< newsig.length(); i++)
					{
						result ^= newsig.charAt(i); 
					}
					
					result %= 65536; 
					int check = Integer.parseInt(signal.substring(signal.indexOf("*")+1),16);
					if(result == check)
					{
						newtime="";
						for(int i = 0; i < 3; i++)
						{
							newtime += time.substring(0, 2);
							if(i ==0 )
							{
								int j = Integer.parseInt(newtime);
								j +=8;
								if(j >= 24)
									j -= 24;
								if(j>9)
								newtime = Integer.toString(j);
								else if(j >0)
								newtime = "0"+ Integer.toString(j);
								else newtime = "00";
							}
							time = time.substring(2);
							if(i != 2)
								newtime += ":";
						}
					}
				
				}
			}
		}while(!signal.equals("END"));
		
		System.out.println(newtime);
		in.close();
	}

}
