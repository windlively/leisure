package lucku.baijunhan.alg.backtrack;

/**
 * 37. 解数独
 * 编写一个程序，通过已填充的空格来解决数独问题。
 *
 * 一个数独的解法需遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 *
 *
 *
 * 一个数独。
 *
 *
 *
 * 答案被标成红色。
 *
 * Note:
 *
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 */
public class SudokuSolver {

    public static void main(String[] args) {
        char[][] chars = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        new Solution().solveSudoku(chars);
    }

    static class Solution {
        public void solveSudoku(char[][] board) {
            backtrack(board, 0, 0);
        }

        public boolean backtrack(char[][] board, int row, int col) {
            System.out.println(row + " " + col);
            if (row == board.length)
                return true;
            if (board[row][col] != '.')
                return (col == board[row].length - 1 && backtrack(board, row + 1, 0)
                        || col < board[row].length - 1 && backtrack(board, row, col + 1));

            for (int j = '1'; j <= '9'; j++) {
                if(!check(board, row, col, (char) j)){
                    continue;
                }
                board[row][col] = (char) j;

                if (col == board[row].length - 1 && backtrack(board, row + 1, 0)
                    || col < board[row].length - 1 && backtrack(board, row, col + 1))
                    return true;

                board[row][col] = '.';
            }
            return false;
        }

        boolean check(char[][] board, int i, int j, char ch) {
            for (int n = 0; n < 9; n++) {
                if (board[i][n] == ch || board[n][j] == ch) return false;
            }
            i = (i / 3) * 3;
            j = (j / 3) * 3;
            // System.out.println(i + " " + j);
            for (int m = i; m < i + 3; m++) {
                for (int n = j; n < j + 3; n++) {
                    if (board[m][n] == ch)
                        return false;
                }
            }
            return true;
        }

    }
}
