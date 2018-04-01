import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


/**
 * Created by Acer on 28.03.2018.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/features",
        glue = "steps",
        snippets = SnippetType.CAMELCASE
)
public class RunnerTest {
}
