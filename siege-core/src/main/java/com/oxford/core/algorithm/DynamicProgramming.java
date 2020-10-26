package com.oxford.core.algorithm;

/**
 * 动态规划相关算法实现
 *
 * @author Chova
 * @date 2020/10/26
 */
public class DynamicProgramming {

    /**
     * 获取两个字符串的最小编辑距离
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return int 最小编辑距离
     */
    public static int minDistance(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        // base case
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }

        // 自底向上求解
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min(
                            // 删除
                            dp[i - 1][j] + 1,
                            // 替换
                            dp[i - 1][j - 1] + 1,
                            // 插入
                            dp[i][j - 1] + 1
                    );
                }
            }
        }
        // 返回s1和s2的最小编辑距离
        return dp[m][n];
    }

    /**
     * 获取三个数字中的最小值
     *
     * @param a 数字a
     * @param b 数字b
     * @param c 数字c
     * @return int 三个数字中的最小值
     */
    private static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
