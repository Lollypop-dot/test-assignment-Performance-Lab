package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Недостаточно аргументов! Введите пути к файлам circlePoint.txt и point.txt.");
            return;
        }

        String circlePointPath = args[0];
        String targetPointPath = args[1];

        try {
            takePath(circlePointPath, targetPointPath);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    static void takePath(String path1, String path2) throws FileNotFoundException {
        String circlePoint = "";
        String rad = "";
        String targetPoint = "";
        int stopPoint = 100;

        Scanner scanner = new Scanner(new File(path1));
        while (scanner.hasNextLine()) {
            circlePoint = scanner.nextLine();
            rad = scanner.nextLine();
        }

        scanner = new Scanner(new File(path2));

        while (scanner.hasNextLine() && stopPoint > 0) {
            targetPoint = scanner.nextLine();
            checkIn(circlePoint, rad, targetPoint);
            stopPoint--;
        }

        scanner.close();
    }

    static void checkIn(String circlePoint, String rad, String targetPoint) {
        String[] stringArrOfCirclePoint = circlePoint.split(" ");
        int[] intArrOfCirclePoint = new int[2]; // точка круга
        int r = Integer.parseInt(rad); // радиус

        for (int i = 0; i < intArrOfCirclePoint.length; i++) {
            intArrOfCirclePoint[i] = Integer.parseInt(stringArrOfCirclePoint[i]);
        }

        String[] stringArrOfTargetPoint = targetPoint.split(" ");
        int[] intArrOfTargetPoint = new int[2]; // точка таргет
        for (int i = 0; i < intArrOfTargetPoint.length; i++) {
            intArrOfTargetPoint[i] = Integer.parseInt(stringArrOfTargetPoint[i]);
        }

        double distance = Math.pow((intArrOfTargetPoint[0] - intArrOfCirclePoint[0]), 2) + Math.pow((intArrOfTargetPoint[1] - intArrOfCirclePoint[1]), 2);

        if (distance > Math.pow(r, 2)) {
            System.out.println("2 - точка снаружи");
        } else if (distance < Math.pow(r, 2)) {
            System.out.println("1 - точка внутри");
        } else {
            System.out.println("0 - точка лежит на окружности");
        }
    }
}

