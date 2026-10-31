package ru.netology.homework.collection;

import java.util.Arrays;
import java.util.stream.Stream;

public class MyArrayList<E> {

    private Object[] elements;
    private int size;

    public MyArrayList() {
        this.elements = new Object[10];
        this.size = 0;
    }

    //добавление элемента в конец списка
    public void add(E element) {
        //если массив заполнен, расширяем его
        if (size == elements.length) {
            resize();
        }
        this.elements[size] = element;
        this.size++;
    }

    //автоматическое расширение внутреннего массива
    private void resize() {
        //создаем новый массив в 2 раза больше старого
        int newLength = elements.length * 2;
        Object[] newArray = new Object[newLength];

        // копируем все старые элементы в новый массив
        System.arraycopy(elements, 0, newArray, 0, elements.length);

        // заменяем старый массив новым
        elements = newArray;
    }

    //получение элемента по индексу
    public E get(int index) {
        checkIndex(index);
        //приводим Object обратно к типу E
        return (E) elements[index];
    }

    //удаление элемента по индексу
    public E remove(int index) {
        checkIndex(index);

        E removedElement = (E) elements[index];

        //сдвигаем все элементы после удаленного на одну позицию влево
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[size - 1] = null; //Убираем дубликат в конце
        size--;

        return removedElement;
    }

    //возвращает текущее количество элементов
    public int size() {
        return size;
    }

    //проверка, пуст ли список
    public boolean isEmpty() {
        return size == 0;
    }

    //вспомогательный метод для проверки выхода за границы
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + size);
        }
    }

    //отдаём элементы в виде стандартного стрима.
    public Stream<E> stream() {
        // берем только заполненную часть массива (от 0 до size) и делаем из нее стрим
        return Arrays.stream((E[]) elements, 0, size);
    }
}