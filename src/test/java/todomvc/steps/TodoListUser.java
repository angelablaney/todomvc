package todomvc.steps;

import net.thucydides.core.annotations.Step;
import todomvc.ui.HomePage;
import todomvc.ui.TodoList;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TodoListUser {

    HomePage todomvcApplicationHomePage;
    TodoList todoList;

    @Step
    public void startsWithAnEmptyTodoList() { todomvcApplicationHomePage.open(); }

    @Step
    public void adds_a_task(String taskName) {
        todoList.addTodo(taskName);
    }

    @Step
    public void should_see_task(String taskName) {
        assertThat(todoList.getTodos()).contains(taskName);
    }

    @Step
    public void should_see_tasks(List<String> tasks) {
        assertThat(todoList.getTodos()).hasSameElementsAs(tasks);
    }

}