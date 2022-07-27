package SwagLabs.TestHelper;

import com.intuit.karate.driver.chrome.Chrome;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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

    /**
     * @param driver
     * @param TableXpath
     * @return
     * @throws IOException
     * @author
     */
    public static HashMap<String, ArrayList<String>> fnGetDataFromWebTable(Chrome driver,String Url, String TableXpath,String THeaders,String Trows,String Tcell) throws IOException {
        String html = driver.locate(TableXpath).getHtml();
        ArrayList<String> colsArray = new ArrayList<>();
        HashMap<Element, ArrayList<String>> dict = new HashMap<>();
        HashMap<String, ArrayList<String>> tableData = new HashMap<>();
        Document document = Jsoup.connect(Url).get();
        Elements table = document.select(TableXpath);
        Elements subHeaders = table.select(THeaders);
        Elements rows = table.select(Trows);
        for (Element row : rows) {
            Elements list = row.select(Tcell);
            ArrayList<String> newList = new ArrayList<>();
            for (Element str : list) {
                newList.add(str.text());
            }
            dict.put(row, newList);
        }
        for (Map.Entry<Element, ArrayList<String>> data : dict.entrySet()) {
            for (int i = 0; i < data.getValue().size(); i++) {
                if (!tableData.containsKey(subHeaders.get(i).text())) {
                    ArrayList<String> tempList = new ArrayList<>();
                    tempList.add(data.getValue().get(i));
                    tableData.put(subHeaders.get(i).text(), tempList);
                } else {
                    tableData.get(subHeaders.get(i).text()).add(data.getValue().get(i));
                }
            }
        }
        return tableData;
    }
}

