package task3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class task3 {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Недостаточно аргументов! Введите пути к файлам tests.json, values.json и report.json.");
            return;
        }

        String testsFilePath = args[0];
        String valuesFilePath = args[1];
        String outputFilePath = args[2];

        try {
            // Чтение JSON из файла tests.json
            JsonObject testsJson = readJsonFile(testsFilePath);

            // Чтение JSON из файла values.json
            JsonObject valuesJson = readJsonFile(valuesFilePath);

            // Обработка данных
            JsonObject reportJson = processData(testsJson, valuesJson);

            // Запись результата в файл report.json
            writeJsonFile(reportJson, outputFilePath);

            System.out.println("Обработка данных завершена. Результат записан в файл " + outputFilePath);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении/записи файла: " + e.getMessage());
        }
    }

    private static JsonObject readJsonFile(String filePath) throws IOException {
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, JsonObject.class);
        }
    }

    private static void writeJsonFile(JsonObject json, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(json, writer);
        }
    }

    private static JsonObject processData(JsonObject testsJson, JsonObject valuesJson) {
        JsonObject reportJson = new JsonObject();
        JsonArray reportArray = new JsonArray();

        JsonArray testsArray = testsJson.getAsJsonArray("tests");
        for (JsonElement testElement : testsArray) {
            JsonObject testObject = testElement.getAsJsonObject();
            int testId = testObject.get("id").getAsInt();

            JsonObject valueObject = findValueObjectById(valuesJson, testId);
            if (valueObject != null) {
                testObject.add("value", valueObject.get("value"));
            }

            reportArray.add(testObject);
        }

        reportJson.add("tests", reportArray);
        return reportJson;
    }

    private static JsonObject findValueObjectById(JsonObject valuesJson, int id) {
        JsonArray valuesArray = valuesJson.getAsJsonArray("values");
        for (JsonElement valueElement : valuesArray) {
            JsonObject valueObject = valueElement.getAsJsonObject();
            int valueId = valueObject.get("id").getAsInt();
            if (valueId == id) {
                return valueObject;
            }
        }
        return null;
    }
}