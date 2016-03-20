package chaptherFive;

import java.util.Scanner;

public class CountWordsLength {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		String word;
		do{
			word = in.next();
			if(word.endsWith("."))
				System.out.print(word.length()-1);
			else{
				System.out.print(word.length()+" ");
			}
			
		}while(!word.endsWith("."));

		in.close();
	}

}
