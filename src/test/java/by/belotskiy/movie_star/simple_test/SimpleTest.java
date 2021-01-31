package by.belotskiy.movie_star.simple_test;

import by.belotskiy.movie_star.controller.command.CommandProvider;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SimpleTest{
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

    @Test
    public void booleanTest(){
        assertEquals(((Boolean)true).toString().toUpperCase(), "TRUE");
    }
}

