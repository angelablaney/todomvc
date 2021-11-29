package todomvc.actions.navigate;

import net.thucydides.core.annotations.Step;
import todomvc.ui.HomePage;

public class NavigateActions {

    HomePage homePage;

    @Step("Navigate to the app home page")
    public void toTheApplicationHomePage() {
        homePage.open();
    }
}
