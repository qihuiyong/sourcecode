
package org.qhy;

import java.util.Random;

public class QuikSort {

    public static int[] a =  new int[10]; // 预设数据数组

    public static void main2(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
        int aa = random.nextInt(9000);
        System.out.println(aa);
        }
    }
    public static void main(String args[]) {
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i]=  random.nextInt(9000);
        }
        System.out.print("排序前: ");
        for (int i = 0; i < a.length; i++)
            System.out.printf("%3s", a[i]);
        System.out.println("");
        int Index = a.length;
        long stratTime = System.currentTimeMillis();
        QuickSort(0, Index - 1, Index); // 快速排序
        long endTime = System.currentTimeMillis();
        System.out.println("耗时："+(endTime-stratTime));
        // 排序后结果
        System.out.print("排序后: ");
        for (int i = 0; i < a.length; i++)
            System.out.printf("%3s", a[i]);
        System.out.println("");
    }

    public static void QuickSort(int Left, int Right, int Index) {
        int i, j, k; // 循环计数变量
        int Pivot; // 枢纽变量
        int Temp; // 暂存变量
        i = Left; // 设定左指针
        j = Right; // 设定右指针
        Pivot = a[Left]; // 取最左边的元素
        if (i < j) {
            do {
                while (a[i] < Pivot && i < Right) // 从左往右找比Pivot大的值
                {
                    i++;
                }
                while (a[j] > Pivot && j > Left) // 从右往左找比Pivot小的值
                {
                    j--;
                }
                if (i < j) // 交换a[i]和a[j]
                {
                    Temp = a[i];
                    a[i] = a[j];
                    a[j] = Temp;
                }
            } while (i < j);
            if (i > j) {
                Temp = a[Left]; // 交换a[Left]和a[j]
                a[Left] = a[j];
                a[j] = Temp;
                // 打印目前排序结果
                System.out.print("排序中: ");
                for (k = 0; k <= Index; k++) {
                    System.out.printf("%3s", a[k]);
                }
                System.out.println("");
            }
            QuickSort(Left, j - 1, Index); // 排序左半边
            QuickSort(j + 1, Right, Index); // 排序右半边
        }
    }
}
