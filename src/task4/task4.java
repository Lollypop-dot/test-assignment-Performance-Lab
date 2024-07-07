package task4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class task4 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        if (args.length < 1) {
            System.out.println("Необходимо передать аргумент: путь к файлу");
            return;
        }

        String filePath = args[0];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            StringBuilder sb = new StringBuilder();

            while (line != null) {
                sb.append(line+ " ");
                System.out.println(line);
                line = reader.readLine();
            }

            String s = sb.toString();
            int[] numArr = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(Arrays.toString(numArr));

            reader.close();

            minMoves(numArr);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void minMoves (int [] ar){
        int sum = 0;
        int average = 0;

        for (int i = 0; i < ar.length; i++) {
            sum += ar[i];
        }

        average = sum / ar.length;

        int moves = 0;

        for (int i = 0; i < ar.length; i++) {
            moves += Math.abs(ar[i] - average);
        }
        System.out.println(moves);
    }

}


