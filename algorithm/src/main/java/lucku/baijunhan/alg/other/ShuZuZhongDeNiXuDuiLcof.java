package lucku.baijunhan.alg.other;

/**
 * 剑指 Offer 51. 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [7,5,6,4]
 * 输出: 5
 *
 *
 * 限制：
 *
 * 0 <= 数组长度 <= 50000
 */
public class ShuZuZhongDeNiXuDuiLcof {

    public static void main(String[] args) {
        System.out.println(new Solution().reversePairs(new int[]{233, 2000000001, 234, 2000000006, 235, 2000000003, 236, 2000000007, 237, 2000000002, 2000000005, 233, 233, 233, 233, 233, 2000000004}));
    }

    static class Solution {
        public int reversePairs(int[] nums) {
            return merge(nums,0,nums.length - 1, new int[nums.length]);
        }

        int merge(int[] nums, int l, int r, int[] tmp){
            if(l >=  r) return 0;
            int m = (l+r) >> 1;
            int count = 0;
            // 归并左侧
            count += merge(nums, l, m, tmp);
            // 归并右侧
            count += merge(nums, m + 1, r, tmp);
            // 归并有序的左右侧数组
            count += merge(nums, l, r, m, tmp);
            return count;
        }

//        int merge(int[] nums, int left, int right, int mid, int[] tmp){
//            // 从最大的数开始归并  与下面的方法相反
//            int l = mid, r = right, index = right, count = 0;
//            while(l >= left && r >= mid + 1){
//                if(nums[l] > nums[r]){
//                    tmp[index--] = nums[l--];
//                    count += r - mid;
//                }else{
//                    tmp[index--] = nums[r--];
//                }
//            }
//            while(l >= left){
//                tmp[index--] = nums[l--];
//            }
//            while(r >= mid + 1){
//                tmp[index--] = nums[r--];
//            }
//            // 归并结果拷贝至原数组
//            for(int i = left;i <= right; i++){
//                nums[i] = tmp[i];
//            }
//            return count;
//        }

        int merge(int[] nums, int left, int right, int mid, int[] tmp){
            int l = left, r = mid + 1, index = left, count = 0;
            while(l <= mid && r <= right){
                if(nums[l] > nums[r]){
                    // 当左侧数组的值大于右侧时，说明左侧数组后面的数
                    // 都比右侧该位置的数字大，
                    // 因此存在mid - l + 1个逆序对
                    tmp[index++] = nums[r++];
                    count += mid - l + 1;
                }else{
                    tmp[index++] = nums[l++];
                }
            }
            while(l <= mid){
                tmp[index++] = nums[l++];
            }
            while(r <= right){
                tmp[index++] = nums[r++];
            }
            for(int i = left;i <= right; i++){
                nums[i] = tmp[i];
            }
            return count;
        }
    }

}
