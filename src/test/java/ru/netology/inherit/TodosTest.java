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
                "Наследовение и полиморфизм",
                "Среда- пятница 24/01");

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }
}