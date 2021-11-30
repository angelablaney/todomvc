package todomvc.actions.addTasks;


import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import java.lang.reflect.Type;
import java.util.List;

public class AddTasksActions {


    @Steps
    AddTaskActions addTask;

    @Step("Add tasks: {0}")
    public void withNames(List<String> tasks) {
        tasks.forEach(
                task -> addTask.withName(task)
        );
    }
}
