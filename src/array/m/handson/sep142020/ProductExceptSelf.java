package array.m.handson.sep142020;

import java.util.Arrays;

public class ProductExceptSelf {

	public static void main(String[] args) {
		// [1,2,3,4]
		int[] nums = {1, 2, 4, 5, 6};
		ProductExceptSelf exceptSelf = new ProductExceptSelf();
		exceptSelf.productExceptSelf(nums);
	}

	public int[] productExceptSelf(int[] nums) {

		int ans[] = new int[nums.length];
		int l = 1;
		ans[0] = l;
		for (int i = 1; i < nums.length; i++) {
			ans[i] = ans[i - 1] * nums[i - 1];
		}
		System.out.println("a-" + Arrays.toString(ans));
		int r = 1;
		for (int i = nums.length - 1; i >= 0; i--) {
			ans[i] = ans[i] * r;
			r = r * nums[i];
		}
		System.out.println("b-" + Arrays.toString(ans));
		return ans;
	}
}
