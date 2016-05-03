package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sort {
	
	int[] readList(int total) throws IOException{
		int[] list = new int[total];
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		System.out.println("Input the numbers:");
		String[] str = in.readLine().split(" ");
		for(int idx = 0; idx < total; idx ++){
			list[idx] = Integer.parseInt(str[idx]);
		}
		return list;
	}
	
	void printList(int[] lst){
		int length = lst.length;
		for(int idx = 0; idx < length; idx ++){
			System.out.print(lst[idx]);
			if(idx < length - 1){
				System.out.print(" ");
			}
		}
		
	}
	
	void bubble_sort(int[] list){
		int length = list.length;
//		System.out.println();
		for(int rear = length - 1; rear > 0; rear --){
			for(int idx = 0; idx < rear; idx ++){
				if(list[idx] > list[idx + 1]){
					int temp = list[idx];
					list[idx] = list[idx + 1];
					list[idx + 1] = temp;
				}

			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		System.out.println("Total numbers:");
		int nums = Integer.parseInt(in.readLine());
		Sort sort = new Sort();
		int[] list = sort.readList(nums); 
		sort.bubble_sort(list);
		sort.printList(list);
	}
}
