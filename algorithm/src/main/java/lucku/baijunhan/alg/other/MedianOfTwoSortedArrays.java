package lucku.baijunhan.alg.other;

/**
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 *
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 *
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        System.out.println(new Solution().findMedianSortedArrays(new int[]{1, 2, 3, 4, 5, 6, 7, 9}, new int[]{2, 3, 5, 6, 7, 8}));
    }

    static class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int len = nums1.length + nums2.length;
            if((len & 1) == 0){
                return (minKth(nums1, nums2, (len >> 1)) + minKth(nums1, nums2, (len >> 1) + 1)) / 2.0;
            }
            return minKth(nums1, nums2, (len >> 1) + 1);

        }

        int minKth(int[] n1, int[] n2, int k){

            int len1 = n1.length, len2 = n2.length;
            int i1 = 0, i2 = 0;

            while(true){
                if(i1 == len1) return n2[i2 + k - 1];
                if(i2 == len2) return n1[i1 + k - 1];

                if(k == 1) return Math.min(n1[i1], n2[i2]);

                int half = k >> 1;
                int newI1 = Math.min(len1, i1 + half) - 1;
                int newI2 = Math.min(len2, i2 + half) - 1;
                if(n1[newI1] < n2[newI2]){
                    k -= newI1 - i1 + 1;
                    i1 = newI1 + 1;
                }else{
                    k -= newI2 - i2 + 1;
                    i2 = newI2 + 1;
                }
            }

        }
    }
}
