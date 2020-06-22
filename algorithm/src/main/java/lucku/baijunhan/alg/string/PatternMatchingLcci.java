package lucku.baijunhan.alg.string;

import java.util.Arrays;

/**
 * 面试题 16.18. 模式匹配
 * 你有两个字符串，即pattern和value。 pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"），该字符串也匹配像"a"、"ab"和"b"这样的模式。但需注意"a"和"b"不能同时表示相同的字符串。编写一个方法判断value字符串是否匹配pattern字符串。
 *
 * 示例 1：
 *
 * 输入： pattern = "abba", value = "dogcatcatdog"
 * 输出： true
 * 示例 2：
 *
 * 输入： pattern = "abba", value = "dogcatcatfish"
 * 输出： false
 * 示例 3：
 *
 * 输入： pattern = "aaaa", value = "dogcatcatdog"
 * 输出： false
 * 示例 4：
 *
 * 输入： pattern = "abba", value = "dogdogdogdog"
 * 输出： true
 * 解释： "a"="dogdog",b=""，反之也符合规则
 * 提示：
 *
 * 0 <= len(pattern) <= 1000
 * 0 <= len(value) <= 1000
 * 你可以假设pattern只包含字母"a"和"b"，value仅包含小写字母。
 *
 */
public class PatternMatchingLcci {

    public static void main(String[] args) {
        System.out.println(new Solution().patternMatching("abba", "dogdogdogdog"));
    }

    static class Solution {

        public boolean patternMatching(String pattern, String value) {
            char[] p = pattern.toCharArray();
            char[] v = value.toCharArray();
            int countA = 0, countB = 0;
            for(char c : p){
                if(c == 'a') countA++;
                else countB ++;
            }
            int lV = v.length;
            if(countA == 0 && countB == 0) return lV == 0;
            if(value.equals("")) return countA == 0 || countB == 0;
            if(countA==1 || countB == 1) return true;
            if(countA == 0 || countB == 0) return match(v, countB == 0 ? countA : countB);
            char firstPCh = p[0];

            for(int i = -1; i < v.length; i ++){
                if(firstPCh == 'a'){
                    if(match(i, countA, countB, v, p, 'a'))
                        return true;
                } else{
                    if(match(i, countB, countA, v, p, 'b'))
                        return true;
                }
            }

            return false;
        }

        boolean match(char[] v, int count){
            int lV = v.length;
            int charLen = lV / count;
            if(!(charLen * count == lV))
                return false;
            char[] s = Arrays.copyOfRange(v, 0, charLen);
            int i = 0;
            for(char c : v){
                if(c != s[i++])
                    return false;
                if(i == charLen)
                    i = 0;
            }
            return true;
        }

        boolean match(int i, int countFirstCh, int countAnotherCh, char[] v, char[] p, char firstCh){
            int lFirstCh = i + 1;
            int lV = v.length;
            int lAnotherCh = (lV - countFirstCh * lFirstCh) / countAnotherCh;
            if(!(countFirstCh * lFirstCh + countAnotherCh * lAnotherCh == lV))
                return false;
            char[] aStr = Arrays.copyOfRange(v, 0, lFirstCh);
            char[] bStr = new char[Math.max(lAnotherCh, 0)];
            int vIndex = 0;
            boolean initB = false;
            for(char c : p){
                if(c == firstCh){
                    if(vIndex + lFirstCh > lV){
                        return false;
                    }else{
                        for(int aI = 0; aI < lFirstCh; vIndex++, aI ++){
                            if(aStr[aI] != v[vIndex]){
                                return false;
                            }
                        }
                    }
                }else{
                    if(vIndex + lAnotherCh > lV)
                        return false;
                    else if(!initB){
                        for(int bI = 0; bI < lAnotherCh; vIndex ++, bI ++){
                            bStr[bI] = v[vIndex];
                        }
                        initB = true;
                    }else{
                        for(int bI = 0; bI < lAnotherCh; vIndex ++, bI ++){
                            if(bStr[bI] != v[vIndex]){
                                return false;
                            }
                        }
                    }
                }
            }            return true;
        }
    }


}
