package captherThree;

import java.util.Scanner;

public class ReadNum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		int dig;
		int cnt = 0;
		int copy = Math.abs(num);
		String[] numStr ={"ling","yi","er","san","si","wu","liu","qi","ba","jiu","fu"};
		do
		{
			copy = copy / 10;
			cnt ++;
		}while(copy !=0);
		do
		{
			if(num<0)
			{
				dig = 10;
				num = -num;
			}
			else
			{
				dig = num / (int)Math.pow(10, cnt-1);
				num = num - dig*(int)Math.pow(10, cnt-1);
				cnt --;
			}
			System.out.print(numStr[dig]);
			if(cnt != 0 || dig == 10)
				System.out.print(" ");
		}while(cnt !=0);
		
		in.close();
	}

}
