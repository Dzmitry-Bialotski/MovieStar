package by.belotskiy.movie_star.util;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UrlParserTest {
    @DataProvider
    public Object[][] url() {
        return new Object[][]{
                {"blablabla/bla/login.do?peers=c105_c89"},
        };
    }

    @Test(dataProvider = "url")
    public void parseTest(String url){
        /*UrlParser parser = new UrlParser();
        String actual = parser.parseCommandName(url);
        assertEquals(actual, "login");*/
    }
}
