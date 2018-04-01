package steps;

import cucumber.api.Delimiter;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class mySteps {
    WebDriver driver;
    String rememberedElement;

    @Before
    public void setUp() throws Throwable{
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Acer\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
    }
    @After
    public void closeDriver() throws Throwable{
        driver.close();
    }

    @Когда("^пользователь открывает браузер$")
    public void resizeBrowser() throws Throwable {
        driver.manage().window().maximize();
    }
    @Тогда("^перейти на ([^\"]*)$")
    public void goToYandex(String arg) throws Throwable{
        String s = "https://"+arg;
        driver.get(s);
    }
    @Тогда("^перейти в яндекс маркет$")
    public void goToMarket() throws Throwable {
        WebElement el = driver.findElement(By.cssSelector("[data-id='market']"));
        el.click();

    }
    @Тогда("^пользователь выбирает раздел ([^\"]*)$")
    public void chooseComp(String name) throws Throwable {
            WebElement el =driver.findElement(By.className("topmenu__list")).findElement(By.cssSelector("[data-department='"+name+"']"));
            el.click();
    }
    @Тогда("^пользователь выбирает подраздел ([^\"]*)$")
    public void chooseLap(String name) throws Throwable{
        WebElement el =driver.findElement(By.linkText(name));
        el.click();

    }
    @Тогда("^установить цену до (\\d+)$")
    public void setPrice(int arg) throws Throwable{
        WebElement el =driver.findElement(By.xpath("//*[@id=\"glpriceto\"]"));
        el.sendKeys(Integer.toString(arg));
    }
    @Тогда("^выбрать фирмы (.+)$")
    public void chooseCompany(@Delimiter(", ")List<String> list) throws Throwable{
        for (String name: list) {
            WebElement el = driver.findElement(By.name("Производитель "+name));
            Actions actions = new Actions(driver);
            actions.moveToElement(el).click().perform();
        }
    }
    @Тогда("^запомнить первый элемент$")
    public void rememberEl() throws Throwable{
        Thread.sleep(5000);
//        WebElement el = driver.findElement(By.xpath(".//*[@class='n-filter-applied-results__content']/div/div"));
//        WebElement el2 = el.findElement(By.className("n-snippet-card2__title")).findElement(By.className("link"));
        WebElement el2 = driver.findElement(By.xpath("(//*[@class=\"n-snippet-card2__title\"]/a)[1]"));
        rememberedElement = el2.getText();
        System.out.println("//////////");
        System.out.println(rememberedElement);
        System.out.println("/////////");
    }
    @Тогда("^ввести полученное название$")
    public void insertEl() throws Throwable{
        WebElement el = driver.findElement(By.xpath("//*[@id=\"header-search\"]"));
        el.sendKeys(rememberedElement);
        el = driver.findElement(By.xpath("//*[@class=\"search2__button\"]/button"));
        el.click();
    }
    @Тогда("^проверить данные$")
    public void checkData() throws Throwable{
        WebElement el = driver.findElement(By.xpath("//*[@class=\"n-title__text\"]/*"));
        String name = el.getText();
        assert(name.contains(rememberedElement));
    }






}
