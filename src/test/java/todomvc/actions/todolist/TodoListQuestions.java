package todomvc.actions.todolist;



import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.UIInteractionSteps;

import java.util.List;
import java.util.stream.Collectors;

public class TodoListQuestions extends UIInteractionSteps {
    public List<String> contents() { return findAll(".todo-list li").stream()
            .map(WebElementFacade::getText)
            .collect(Collectors.toList());


    }

}
