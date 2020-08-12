package lucku.baijunhan.alg.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 130. 被围绕的区域
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 *
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 示例:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 *
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 */
public class SurroundedRegions {

    public static void main(String[] args) {
        char[][] board = new char[][]{{'O','O','O','O','O','O','O','O','X','O','O','O','O','O','X','O','O','O','O','O'},{'O','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','O','O','O'},{'X','O','O','X','O','X','O','O','O','O','X','O','O','X','O','O','O','O','O','O'},{'O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','X','O'},{'O','X','X','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','O'},{'O','O','O','O','X','O','O','O','O','O','O','X','O','O','O','O','O','X','X','O'},{'O','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','O','O','O'},{'O','O','O','O','O','O','O','O','O','O','O','O','O','X','O','O','O','O','O','O'},{'O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','X','O'},{'O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O'},{'O','O','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','O','O'},{'O','O','O','O','X','O','O','O','O','X','O','O','O','O','O','O','O','O','O','O'},{'O','O','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','O','O'},{'X','O','O','O','O','O','O','O','O','X','X','O','O','O','O','O','O','O','O','O'},{'O','O','O','O','O','O','O','O','O','O','O','X','O','O','O','O','O','O','O','O'},{'O','O','O','O','X','O','O','O','O','O','O','O','O','X','O','O','O','O','O','X'},{'O','O','O','O','O','X','O','O','O','O','O','O','O','O','O','X','O','X','O','O'},{'O','X','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O'},{'O','O','O','O','O','O','O','O','X','X','O','O','O','X','O','O','X','O','O','X'},{'O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O','O'}};
        new Solution().solve(board);
        System.out.println(Arrays.deepToString(board));
    }

    static class Solution {
        private final int[][] direction = new int[][]{
                {1, 0, -1, 0},
                {0, 1, 0, -1}
        };

        public void solve(char[][] board) {
            if(board.length == 0) return;
            int m = board.length - 1;
            int n = board[0].length - 1;
            Queue<int[]> queue = new LinkedList<>();
            for(int i = 0; i <= m; i ++){
                if(board[i][0] == 'O')
                    //recursive(board, i, 0);
                    queue.offer(new int[]{i, 0});
                if(board[i][n] == 'O')
                    // recursive(board, i, n);
                    queue.offer(new int[]{i, n});
            }

            for(int i = 0; i <= n; i ++){
                if(board[0][i] == 'O') // recursive(board, 0, i);
                    queue.offer(new int[]{0, i});
                if(board[m][i] == 'O') // recursive(board, m, i);
                    queue.offer(new int[]{m, i});
            }

            while(!queue.isEmpty()){
                int[] pos = queue.poll();
                int x = pos[0], y = pos[1];
                board[x][y] = 'Z';
                for(int k = 0; k < 4; k ++){
                    int mx = direction[0][k] + x;
                    int my = direction[1][k] + y;
                    if(mx < 0 || mx >= board.length || my < 0 || my >= board[0].length || board[mx][my] != 'O'){
                        continue;
                    }
                    queue.offer(new int[]{mx,my});
                }
            }

            for(int i = 0; i <= n; i ++){
                for(int j = 0; j <= m; j ++){
                    if(board[i][j] == 'O') board[i][j] = 'X';
                    if(board[i][j] == 'Z') board[i][j] = 'O';
                }
            }
        }

        public void recursive(char[][] board, int i, int j){
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{i, j});
            while(!queue.isEmpty()){
                int[] pos = queue.poll();
                int x = pos[0], y = pos[1];
                board[x][y] = 'Z';
                for(int k = 0; k < 4; k ++){
                    int m = direction[0][k] + x;
                    int n = direction[1][k] + y;
                    if(m < 0 || m >= board.length || n < 0 || n >= board[0].length || board[m][n] != 'O'){
                        continue;
                    }
                    queue.offer(new int[]{m,n});
                }
            }

        }
    }
}
