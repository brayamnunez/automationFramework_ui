package utils;

import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BaseUtils {

    public void loadUrl(String url) throws Exception {
        if (url == null) {
            DriverFactory.driver.get(FileReaderManager.getInstance().getConfigReader().getUrl());
        } else {
            DriverFactory.driver.get(url);
        }
    }

    public String setDateFormat(String dateFromUser, String dateFormat, String correctDateFormat) {
        SimpleDateFormat dateFormatFromUser = new SimpleDateFormat(dateFormat);
        SimpleDateFormat dateCorrectFormat = new SimpleDateFormat(correctDateFormat);
        String formattedDate = null;
        try {
            formattedDate = dateCorrectFormat.format(dateFormatFromUser.parse(dateFromUser));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }

    public List<String> separateListBy(String list, String regex){
        List<String> passengers = Arrays.asList(list.split(regex));
        return passengers;
    }

    public List<WebElement> getRandomFromList(List<WebElement> lst, int n) {
        ArrayList copy = new ArrayList(lst);
        Collections.shuffle(copy);
        return copy.subList(0, n);
    }
}
