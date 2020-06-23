package stepdefinition;

import helpers.ToDoHelper;
import io.cucumber.java.Before;

public class Base {

    // Init RequestSpecBuilder
    @Before
    public void TestSetup(){
        ToDoHelper toDoHelper = new ToDoHelper();
    }
}
