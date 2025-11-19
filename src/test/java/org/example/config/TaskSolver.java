package org.example.config;

import java.util.*;

/**
 * Класс TaskSolver предоставляет методы для решения различных алгоритмических задач,
 * включая генерацию перестановок, поиск наибольшей возрастающей подпоследовательности
 * и поиск наибольшей общей подпоследовательности.
 */
public class TaskSolver {

    public static List<String> permutations(int[] arr) {
        List<String> result = new ArrayList<>();
        permute(arr, 0, result);
        return result;
    }

    private static void permute(int[] arr, int i, List<String> out) {
        if (i == arr.length) {
            StringBuilder sb = new StringBuilder();
            for (int v : arr) sb.append(v).append(",");
            sb.setLength(sb.length() - 1);
            out.add(sb.toString());
            return;
        }
        for (int j = i; j < arr.length; j++) {
            swap(arr, i, j);
            permute(arr, i + 1, out);
            swap(arr, i, j);
        }
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static int lis(int[] arr) {
        List<Integer> dp = new ArrayList<>();
        for (int n : arr) {
            int i = Collections.binarySearch(dp, n);
            if (i < 0) i = -i - 1;
            if (i == dp.size()) dp.add(n);
            else dp.set(i, n);
        }
        return dp.size();
    }

    public static int lcs(String a, String b) {
        int[][] dp = new int[a.length()+1][b.length()+1];

        for (int i = 1; i <= a.length(); i++)
            for (int j = 1; j <= b.length(); j++)
                dp[i][j] = (a.charAt(i-1) == b.charAt(j-1))
                        ? dp[i-1][j-1] + 1
                        : Math.max(dp[i-1][j], dp[i][j-1]);

        return dp[a.length()][b.length()];
    }
}