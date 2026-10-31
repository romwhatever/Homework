package ru.netology.homework;

import ru.netology.homework.collection.MyArrayList;
import ru.netology.homework.model.Car;
import ru.netology.homework.service.CarService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarService carService = new CarService(); // Создаем наш сервис
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n--- ГЛАВНОЕ МЕНЮ ---");
            System.out.println("1. Добавить автомобиль вручную");
            System.out.println("2. Загрузить данные из файла");
            System.out.println("3. Обработать данные (Stream API)");
            System.out.println("4. Тест кастомной коллекции");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Введите модель: ");
                    String model = scanner.nextLine();

                    System.out.print("Введите мощность (л.с.): ");
                    int power = getValidInt(scanner); // вызов метода для валидации

                    System.out.print("Введите год выпуска: ");
                    int year = getValidInt(scanner);

                    if (year < 1885 || year > 2026) {
                        System.out.println("Ошибка: неверный год выпуска!");
                    } else {
                        carService.addCar(new Car(model, power, year));
                        System.out.println("Автомобиль успешно добавлен!");
                    }
                    break;

                case "2":
                    carService.loadFromFile("data.txt");
                    break;

                case "3":
                    carService.processData();
                    break;

                case "4":
                    System.out.println("--- Тест кастомной коллекции ---");
                    MyArrayList<Car> testList = new MyArrayList<>();

                    // добавление
                    System.out.println("Добавляем 3 машины...");
                    testList.add(new Car("Test1", 100, 2000));
                    testList.add(new Car("Test2", 150, 2010));
                    testList.add(new Car("Test3", 200, 2020));

                    // вывод содержимого и размер
                    System.out.println("Текущий размер: " + testList.size());
                    for (int i = 0; i < testList.size(); i++) {
                        System.out.println("Элемент [" + i + "]: " + testList.get(i)); // 3. Получение
                    }

                    // удаление
                    System.out.println("Удаляем элемент под индексом 1...");
                    testList.remove(1);
                    System.out.println("Новый размер: " + testList.size());

                    // проверяем, что осталось
                    for (int i = 0; i < testList.size(); i++) {
                        System.out.println("Элемент [" + i + "]: " + testList.get(i));
                    }
                    break;

                case "0":
                    System.out.println("Завершение работы...");
                    isRunning = false;
                    break;

                default:
                    System.out.println("Ошибка: неверный пункт меню.");
            }
        }
        scanner.close();
    }

    // вспомогательный метод для валидации ввода чисел
    private static int getValidInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: нужно ввести целое число. Попробуйте снова: ");
            }
        }
    }
}