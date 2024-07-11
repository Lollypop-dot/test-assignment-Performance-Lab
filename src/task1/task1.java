package task1;

public class task1 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Недостаточно аргументов! Введите количество элементов в массиве и интервал.");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        int[] ar = new int[n];
        for (int i = 0; i < n; i++) {
            ar[i] = i + 1;
        }

        int firstLast = 0;
        System.out.print("Путь: ");
        do {
            System.out.print(ar[firstLast]);
            firstLast = (firstLast + m - 1) % n;
        } while (firstLast != 0);
    }
}
