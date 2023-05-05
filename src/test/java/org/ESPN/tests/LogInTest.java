package org.ESPN.tests;

import org.ESPN.pages.WatchPage;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.is;

public class LogInTest extends BaseTest{


    private final String USERNAME = "Juan!";

    @Test
    public void LogInTest() {
        home.LogInOption();
        checkThat("User Modal is present", home.isLogInModalDisplayed(), is(true));
        home.switchToModal();
        checkThat("ESPN logo is present", home.isEspnLogoDisplayed(), is(true));
        checkThat("Log In button is present", home.isLogInButtonDisplayed(), is(true));
        checkThat("Sign Up button is present", home.isSignUpButtonDisplayed(), is(true));
        home.insideLogInModal();
        home.switchToMain();
        WatchPage watchPage = home.watchPage();
        checkThat("The first carousel is present", watchPage.isFirstCarouselDisplayed(), is(true));
        checkThat("The second card on the first carousel is present", watchPage.isSecondCardDisplayed(), is(true));
        watchPage.clickOnSecondCard();
        checkThat("The 'X' button to close is present", watchPage.isxButtonDisplayed(), is(true));
        watchPage.clickOnxButton();
        watchPage.returnToHome();
        home.switchToMain();
        home.mouseHoverUserIcon();
        home.accessingUserPanel();
        checkThat("The username is correct", home.userNameConfirmation(), is(USERNAME));
        home.logOutOption();
        home.switchToMain();
        home.mouseHoverUserIcon();
        home.accessingUserPanel();
        checkThat("The element 'Nav text'\n" +
                "has text: 'Welcome!' without user name specified", home.displayUsername(), is("Welcome!"));
    }

}
