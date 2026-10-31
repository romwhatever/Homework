package ru.netology.homework.service;

import ru.netology.homework.collection.MyArrayList;
import ru.netology.homework.model.Car;

import java.io.*;

public class CarService {
    private final MyArrayList<Car> cars = new MyArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
    }

    public void loadFromFile(String fileName) {

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {

            //проверяем, нашелся ли файл. Если нет - выбрасываем ошибку.
            if (inputStream == null) {
                System.out.println("Ошибка: Файл " + fileName + " не найден в папке resources!");
                return;
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        String model = parts[0].trim();
                        int power = Integer.parseInt(parts[1].trim());
                        int year = Integer.parseInt(parts[2].trim());
                        cars.add(new Car(model, power, year));
                    }
                }
                System.out.println("Данные успешно загружены! Добавлено машин: " + cars.size());
            }

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка в формате данных внутри файла: " + e.getMessage());
        }
    }

    //обработка через Stream API
    public void processData() {
        if (cars.isEmpty()) {
            System.out.println("Список автомобилей пуст. Сначала добавьте данные.");
            return;
        }

        System.out.println("--- Результат обработки (Stream API) ---");

        var result = cars.stream()
                .filter(car -> car.getPower() > 100) // оставляем только машины мощнее 100 л.с.
                .map(car -> car.getModel().toUpperCase())
                .sorted()
                .toList();

        result.forEach(System.out::println);
    }
}