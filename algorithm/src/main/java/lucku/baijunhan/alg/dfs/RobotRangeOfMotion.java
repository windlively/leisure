package lucku.baijunhan.alg.dfs;

/**
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *
 * 示例 1：
 *
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * 示例 2：
 *
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * 提示：
 *
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RobotRangeOfMotion {

    public static void main(String[] args) {
        System.out.println(new RobotRangeOfMotion().movingCount(3, 1, 0));
    }

    public int movingCount(int m, int n, int k) {
        // 创建已访问的标记数组
        boolean[] visited = new boolean[m * n];
        // 从(0,0)开始访问
        return movingCount(k, m, n, 0, 0, visited);
    }

    private int movingCount(int k, int rows, int cols, int row, int col, boolean[] visited){
        int count = 0;
        // 检查该位置是否满足要求
        if(check(k, rows, cols, row, col, visited)){
            // 将该位置标记为访问过(用一维数组代替二维数组)
            visited[row * cols + col] = true;
            // 移动格数加一, 然后分别向上下左右移动一格, 重复此过程
            count = 1 + movingCount(k, rows, cols, row + 1, col, visited)
                    + movingCount(k, rows, cols, row - 1, col, visited)
                    + movingCount(k, rows, cols, row, col + 1, visited)
                    + movingCount(k, rows, cols, row, col - 1, visited);
        }
        return count;
    }

    // 检查该位置是否符合要求
    private boolean check(int k, int rows, int cols, int row, int col, boolean[] visited){
        // 所处位置的行列索引大于零 且小于方格的行数和列数 数位和小于给定值k 且没被访问过
        return row >= 0 && col >= 0 && row < rows && col < cols &&
               (getDigitalSum(col) + getDigitalSum(row)) <= k && !visited[row * cols + col];
    }

    // 求数位和
    private int getDigitalSum(int num){
        int result = 0;
        while (num > 0){
            result += num % 10;
            num = num / 10;
        }
        return result;
    }
}
