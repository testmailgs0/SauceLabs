package SauceLabs;


import static org.junit.Assert.assertTrue;

import com.intuit.karate.KarateOptions;
import org.junit.Test;
import com.intuit.karate.junit4.Karate;
import org.junit.runner.RunWith;
/**
 * Unit test for simple App.
 */
    @RunWith(Karate.class)
    @KarateOptions(features = "classpath:SauceLabs/TestScenarios/*.feature")
    public class TestRunner {

    }

