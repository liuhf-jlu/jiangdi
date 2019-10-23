package cn.edu.ccut.se;
import cn.edu.ccut.se.MaxSubSum;

public class Test_MaxSubSum {
	
	public static void main(String[] args) {
		int[] arr={-1,-2,6,4,3,5,8,-1,-2,5};
		for(int i=0;i<arr.length;i++)
			System.out.print(arr[i]+",");
		System.out.println("MaxSubSum = "+MaxSubSum.get_maxSubSum(arr, arr.length));
		
	}
}
