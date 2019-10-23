package cn.edu.ccut.se;
import org.junit.Test;
import org.junit.Assert;

public class MaxSubSum {
	
	static int get_maxSubSum(int arr[],int n) {
		int max=arr[0];
		int ans=0;
		for(int i=0;i<n;i++) {
			if(ans<0)
				ans=arr[i];
			else 
				ans+=arr[i];
			if(max<ans)
				max=ans;
		}
		return max;
	}
	
	@Test
	public void test() {
		int[] arr= {-1,-2,6,4,3,5,8,-1,-2,5};
		System.out.println(MaxSubSum.get_maxSubSum(arr, arr.length));
	}
	
	
	
	
}
