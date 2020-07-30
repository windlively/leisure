package lucku.baijunhan.alg.other;

import java.util.Arrays;

/**
 * 剑指 Offer 61. 扑克牌中的顺子
 * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5]
 * 输出: True
 *
 *
 * 示例 2:
 *
 * 输入: [0,0,1,2,5]
 * 输出: True
 *
 */
public class BuKePaiZhongDeShunZiLcof {

    static class Solution {
        public boolean isStraight(int[] nums) {
            Arrays.sort(nums);
            int king = 0;
            int i = 0;
            while(nums[i] == 0){
                i ++;
                king ++;
            }
            i ++;
            while(i < nums.length){
                if(nums[i] != nums[i - 1] + 1){
                    if(king > 0){
                        nums[i - 1] ++;
                        king --;
                    }else{
                        return false;
                    }
                }else{
                    i ++;
                }
            }
            return true;
        }
    }

}
