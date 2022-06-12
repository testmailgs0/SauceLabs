package SourceCode;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import cucumber.api.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author Deepak
 */
@CucumberOptions(features = {"classpath:SourceCode" },
        tags = {"@TestExecutor"})

public class cucumberReport {
    @Test
    public void testParallel() {
        //String karateOutputPath = "target/surefire-reports";
        Results results = Runner.path("classpath:SourceCode").tags("@TestExecutor").parallel(0);
        generateReport(results.getReportDir());
        assertTrue(results.getFailCount() == 0, results.getErrorMessages());
    }

    public static void generateReport(String karateOutputPath) {
        List<File> jsonFiles = (List<File>) FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
        List jsonPaths = new ArrayList(jsonFiles.size());
        jsonFiles.forEach(file ->jsonPaths.add(file.getAbsoluteFile()));
        Configuration config = new Configuration(new File("target"), "KarateBaseFramework");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }
}