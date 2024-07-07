package task1;

import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов в массиве: ");
        int n = scanner.nextInt();
        System.out.print("Введите интервал: ");
        int m = scanner.nextInt();

        int[] ar = new int[n];
        for (int i = 0; i < n; i++) {
            ar[i] = i + 1;
        }

        int firslLast = 0;
        System.out.print("Путь: ");
        do {
            System.out.print(ar[firslLast]);
            firslLast = (firslLast + m - 1) % n;
        } while (firslLast != 0);
    }
}
