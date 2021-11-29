package todomvc.actions.addTasks;

// just storing the LOCATORS here which help us interact with the page objects

import org.openqa.selenium.By;

//made it private (removed 'public' from the class and static final lines) because it doesnt need to be shared anywhere else in the project.
class NewTodoForm {
    static final By NEW_TODO_INPUT = By.cssSelector(".new-todo");
}
