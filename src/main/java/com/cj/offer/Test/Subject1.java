package com.cj.offer.Test;

/**
 * @author : chenjie
 * @date : 2019-06-19 18:43
 * @describe :
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序
 * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数
 */
public class Subject1 {

    public static void main(String[] args) {
        int array[][] = {{1, 2, 3, 4}, {2, 3, 4, 5}, {6, 7, 8, 9}};
        System.out.println(findTwo(1, array));
    }

    /** 直接循环每一个O(n) **/
    public static boolean find(int target, int[][] array) {
        int row = array.length;
        for (int i = 0; i < row; i++) {
            int col = array[i].length;
            for (int j = 0; j < col; j++) {
                if (array[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    /** 找左下角 因为向上是递减 向右是递增的 O(logn) **/
    public static boolean findTwo(int target, int[][] array) {
        int row = array.length - 1;
        int col = array[0].length;
        int i = 0;
        while (row != -1 && i != col) {
            if (array[row][i] == target) {
                return true;
            } else if (array[row][i] < target) {
                i++;
            } else if (array[row][i] > target) {
                row--;
            }
        }
        return false;
    }
}
