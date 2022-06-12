package SourceCode.TestHelper;

import com.intuit.karate.driver.chrome.Chrome;

import java.util.ArrayList;
import java.util.List;


public class SeleniumHelper {
    /***
     *
     * @param Driver
     * @param Xpath
     * @param Text
     * @return
     */
    public static boolean fnSelectElementByText(Chrome Driver, String Xpath, String Text) {
        try {
            Driver.locateAll(Xpath).forEach(elem -> {
                if (elem.getText().equalsIgnoreCase(Text)) elem.click();
            });
            Driver.locateAll("//button").forEach(elem -> {
                if (elem.getText().equalsIgnoreCase("Add to cart")) {
                    elem.click();
                }
            });
        } catch (Exception e) {
            throw new RuntimeException("Unable to select elemnent:" + Text, e);
        }
        return true;
    }

    /***
     *
     * @param Driver
     * @param Xpath
     * @param Text
     * @return
     */
    public static boolean fnVerifyElementText(Chrome Driver, String Xpath, String Text) {
        List<String> uiData = new ArrayList<>();
        try {
            Driver.locateAll(Xpath).forEach(elem -> {
                if (elem.getText().equalsIgnoreCase(Text)) {
                    uiData.add(elem.getText());
                }
                ;
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Element Text Not Found:" + Text, e);
        }
        if (uiData.contains(Text)) {
            return true;
        } else {
            return false;
        }
    }
}
