package SourceCode;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.intuit.karate.junit4.Karate;
import cucumber.api.CucumberOptions;
import io.cucumber.junit.Cucumber;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;


/**
 *
 * @author Deepak
 */
@CucumberOptions(features = {"classpath:SourceCode" },
        tags = {"@TestExecutor"},plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json" },
        monochrome = true)
public class cucumberReport {
    @Test
    public void testParallel() {
        //String karateOutputPath = "target/surefire-reports";
        Results results = Runner.path("classpath:SourceCode").outputCucumberJson(true).tags("@TestExecutor").parallel(0);
        generateReport(results.getReportDir());
        assertEquals(results.getErrorMessages(), 0, results.getFailCount());
    }

    public static void generateReport(String karateOutputPath) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();
        String currentDateTime = dtf.format(now);
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
        ArrayList jsonPaths = new ArrayList(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        String currentDir = System.getProperty("user.dir");
        String folderPath= currentDir + "\\Reports\\ " + "TestCaseExecution_ " +currentDateTime;
        File file = new File (folderPath);
        file.mkdirs();
        Configuration config = new Configuration( file, "SauceLabs");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }
}