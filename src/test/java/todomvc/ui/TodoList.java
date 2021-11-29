package todomvc.ui;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

public class TodoList extends PageObject {

    @FindBy(css=".new-todo")
    WebElementFacade newTodoField;

    public void addTodo(String taskName) {
        newTodoField.sendKeys(taskName, Keys.ENTER);
    }

    public List<String> getTodos() {
        return findAll(".todo-list li").stream()
                .map(WebElementFacade::getText)
                .collect(Collectors.toList());
    }
}
