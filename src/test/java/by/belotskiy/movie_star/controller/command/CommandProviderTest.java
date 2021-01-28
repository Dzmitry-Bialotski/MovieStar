package by.belotskiy.movie_star.controller.command;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CommandProviderTest {
    @DataProvider
    public Object[][] url() {
        return new Object[][]{
                {"blablabla/bla/login.do?peers=c105_c89"},
        };
    }
    @Test(dataProvider = "url")
    public void parseUrlTest(String url){
        String result = CommandProvider.parseCommandName(url);
        assertEquals(result, "login");
    }
}
