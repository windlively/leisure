package lucku.baijunhan.alg.array;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 *
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例:
 *
 * 输入: 4
 * 输出: [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class N_Queens {

    public static void main(String[] args) {
        System.out.println(new Solution().solveNQueens(3));
    }

    static class Solution {
        public List<List<String>> solveNQueens(int n) {
            // if(n < 4) return Collections.emptyList();
            List<List<String>> list = new ArrayList<>();
            search(list, new boolean[n][n], 0);
            return list;
        }

        public void search(List<List<String>> list, boolean[][] board, int row){
            if(row == board.length){
                List<String> ls = new ArrayList<>(board.length);
                for( boolean[] arr: board){
                    StringBuilder s = new StringBuilder();
                    for (boolean v : arr){
                        s.append(v ? "Q" : ".");
                    }
                    ls.add(s.toString());
                }
                list.add(ls);
                return;
            }
            for(int i = 0; i < board[0].length; i++){
                if(check(board, row, i)){
                    board[row][i] = true;
                    search(list, board, row + 1);
                    board[row][i] = false;
                }
            }
        }

        private static boolean check(boolean[][] board, int i, int j){
            int row = board.length;
            int col = board[0].length;
            int base;
            for(int m = 0; m < row; m ++){

                if(m == i){
                    for(int k = 0; k < col; k ++)
                        if(board[m][k]) return false;
                    continue;
                }

                base = Math.abs(m - i);
                int jL = j - base;
                int jR = j + base;

                if(jL >= 0 && jL < col && board[m][jL]
                   || jR >= 0 && jR < col && board[m][jR]
                   || board[m][j]){
                    return false;
                }
            }
            return true;
        }
    }

    static String out(boolean[][] board){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < board.length; i ++){
            for (int j = 0; j < board[0].length; j++) {
                stringBuilder.append(board[i][j] ? " * " : " o ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
