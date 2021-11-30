package todomvc;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.JsonArray;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import todomvc.actions.addTasks.AddTaskActions;
import todomvc.actions.addTasks.AddTasksActions;
import todomvc.actions.navigate.NavigateActions;
import todomvc.actions.todolist.TodoListQuestions;

import java.lang.reflect.Type;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class AddNewTasksStepDefinitions {
    // register data type handler for converting lists
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @DefaultParameterTransformer
    @DefaultDataTableEntryTransformer
    @DefaultDataTableCellTransformer
    public Object defaultTransformer(Object fromValue, Type toValueType) {
        JavaType javaType = objectMapper.constructType(toValueType);

        if(fromValue instanceof String && ((String) fromValue).contains(","))
        {
            // determine it's a list of strings
            try{
                String[] splitArray = ((String) fromValue).split(",");
                JsonArray jsonArray = new JsonArray();
                for(String splitString : splitArray)
                {
                    jsonArray.add(splitString.trim());
                }
                return objectMapper.readValue(jsonArray.toString(), TypeFactory.defaultInstance().constructCollectionType(List.class, String.class));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return objectMapper.convertValue(fromValue, javaType);
    }
//
//    @ParameterType("(?:.+;)+.+")
//    public List<String> stringList(String raw) {
//        String[] values = raw.split(";");
//        return Arrays.asList(values);
//    }


    @Steps
    NavigateActions navigate;

    @Steps
    AddTasksActions addTasks;

    @Steps
    AddTaskActions addTask;

    @Steps
    TodoListQuestions theTodoList;


    @Given("^that (?:.*) has an empty todo list$")
    public void that_James_has_an_empty_todo_list() throws Exception {
        navigate.toTheApplicationHomePage();

    }

    @Given("^that (?:.*) has a list containing (.*)$")
    public void has_a_list_containing(List<String> tasks) throws Exception {
        navigate.toTheApplicationHomePage();
        addTasks.withNames(tasks);

    }

    @When("^s?he adds '(.*)' to (?:his|her) list$")
    public void he_adds_to_his_list(String taskName) throws Exception {
        addTask.withName(taskName);
    }

    @Then("^'(.*)' should be recorded in (?:his|her) list$")
    public void should_be_recorded_in_his_list(String taskName) throws Exception {
        assertThat(theTodoList.contents()).contains(taskName);
        // removed otl - todoUser.should_see_task(taskName);
    }

    @Then("^(?:his|her) todo list should contain (.*)$")
    public void list_should_contain(List<String> tasks) throws Exception {

        assertThat(theTodoList.contents()).hasSameElementsAs(tasks);

    }
}
