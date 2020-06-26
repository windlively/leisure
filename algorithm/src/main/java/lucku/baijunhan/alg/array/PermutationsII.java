package lucku.baijunhan.alg.array;

import java.util.ArrayList;
import java.util.List;

public class PermutationsII {

    public static void main(String[] args) {
        System.out.println(new Solution().permuteUnique(new int[]{1, 1, 2}));
    }

    static class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> list = new ArrayList<>();
            // List<Integer> remain = new ArrayList<>();
            // for(int n: nums) remain.add(n);
            // recursive(list, new ArrayList<>(), remain);
            recursive2(list, nums, 0);
            return list;
        }

        public void recursive(List<List<Integer>> result, List<Integer> cur, List<Integer> remain){
            if(remain.isEmpty()){
                if(!result.contains(cur))
                    result.add(cur);
                return;
            }
            for(int i = 0; i < remain.size(); i++){
                List<Integer> next = new ArrayList<>(cur);
                List<Integer> nextRemain = new ArrayList<>(remain);
                next.add(remain.get(i));
                nextRemain.remove(i);
                recursive(result, next, nextRemain);
            }
        }

        void recursive2(List<List<Integer>> result, int[] nums, int index){
            if(index == nums.length){
                List<Integer> list = new ArrayList<>();
                for(int n : nums) list.add(n);
                result.add(list);
                return;
            }

            for(int i = index; i < nums.length; i++){
                swap(nums, index, i);
                recursive2(result, nums, index + 1);
                swap(nums, index, i);
            }
        }

        void swap(int[] ns, int i, int j){
            ns[i] = ns[i] ^ ns[j];
            ns[j] = ns[i] ^ ns[j];
            ns[i] = ns[i] ^ ns[j];

        }
    }
}
