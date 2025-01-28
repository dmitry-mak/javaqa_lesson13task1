package ru.netology.inherit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodosTest {

    @Test
    public void shouldAddAllTasksTypes() {
        SimpleTask simpleTask = new SimpleTask(1, "Проснуться");

        String[] subtasks = {"Код", "Тесты", "Рефакторинг"};
        Epic epic = new Epic(2, subtasks);

        Meeting meeting = new Meeting(3,
                "Разбор домашних заданий",
                "Наследование и полиморфизм",
                "Среда- пятница 24/01");

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    // Поиск задачи по части слова. Возвращает true.
    @Test
    void searchTestForSimpleTasks() {

        Task testSimpleTaskSearch = new SimpleTask(1, "Проснуться и заварить чай");

        boolean expected = true;
        boolean actual = testSimpleTaskSearch.matches("чай");

        Assertions.assertEquals(expected, actual);
    }

    //    Поиск задачи по части слова. Негативная проверка - должен возвращать false
    @Test
    void searchTestForSimpleTasks_negative() {

        Task testSimpleTaskSearch = new SimpleTask(1, "Проснуться и заварить чай");

        boolean expected = false;
        boolean actual = testSimpleTaskSearch.matches("спать");

        Assertions.assertEquals(expected, actual);
    }


    // Должен возвращать true независимо от регистра
    @Test
    void searchTestForSimpleTasks_caseSensitive() {

        Task testSimpleTaskSearch = new SimpleTask(1, "Проснуться и заварить чай");

        boolean expected = true;
        boolean actual = testSimpleTaskSearch.matches("пРоС");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void searchTestForTodos() {

        Task simpleTask = new SimpleTask(2, "Запушить домашнюю работу на гитхаб");

        String[] subtasks = {"Создать", "Читать", "Улучшить", "Удалить"};
        Task epicTask = new Epic(1, subtasks);

        Task meetingTask = new Meeting(3,
                "Наследование и абстракция",
                "Автоматизированное тестирование",
                "Четверг 23/01/2025");


        Todos toDoList = new Todos();
        toDoList.add(meetingTask);
        toDoList.add(epicTask);
        toDoList.add(simpleTask);

        Task[] expected = {simpleTask};
        Task[] actual = toDoList.search("пушить");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnMultipleTasks() {
        Task simpleTask = new SimpleTask(2, "Читать лекцию по автоматической интеграции");

        String[] subtasks = {"Создать", "Читать", "Улучшить", "Удалить"};
        Task epicTask = new Epic(1, subtasks);

        Task meetingTask = new Meeting(3,
                "Как начать читать быстрее",
                "Самый быстрый ботан на диком западе",
                "Четверг 23/01/2025");


        Todos toDoList = new Todos();
        toDoList.add(simpleTask);
        toDoList.add(epicTask);
        toDoList.add(meetingTask);

        Task[] expected = {simpleTask, epicTask, meetingTask};
        Task[] actual = toDoList.search("читать");

        Assertions.assertArrayEquals(expected, actual);
    }

    //    Должен искать и добавлять задачи типа Meeting при совпадении как из поля topic, так и поля project
    @Test
    public void meetingTasksTest() {

        Meeting meeting = new Meeting(1,
                "Автоматизация тестов",
                "Инженер по тестированию",
                "23/01/2025");

        Meeting meeting2 = new Meeting(3,
                "Замена масла",
                "Запись в автосервис",
                "24/01/2025");

        Task meeting3 = new Meeting(4,
                "Как начать читать быстрее",
                "Самый быстрый ботан на диком западе",
                "Четверг 23/01/2025");

        Todos todos = new Todos();
        todos.add(meeting);
        todos.add(meeting2);
        todos.add(meeting3);

        Task[] expected = {meeting, meeting2};
        Task[] actual = todos.search("авто");

        Assertions.assertArrayEquals(expected, actual);
    }

    //    обработка пустого массива
    @Test
    public void shouldHandleNullQuery() {
        Todos todos = new Todos();
        Task[] expected = new Task[0];
        Task[] actual = todos.search(null);
        Assertions.assertArrayEquals(expected, actual);
    }

//    Должен осуществлять поиск по подзадачам объекта типа Epic
    @Test
    public void testMeeting() {
        String[] subtasks = {"Прийти", "Увидеть", "Победить"};
        Epic epic = new Epic(1,
                subtasks);

        Todos todos = new Todos();

        todos.add(epic);

        Task[] expected = {epic};
        Task[] actual = todos.search("Победить");

        Assertions.assertArrayEquals(expected, actual);
    }
}