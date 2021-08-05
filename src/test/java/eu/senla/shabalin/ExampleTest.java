package eu.senla.shabalin;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.internal.thread.ThreadTimeoutException;

import java.io.IOException;
import java.util.List;

public class ExampleTest extends Assert {

//        1. @BeforeSuite – указывает, что данный метод будет запускаться перед любым методом тестового класса.
//        2. @BeforeTest – аннотированный метод будет запускаться до всех тестовых методов.
//        3. @BeforeClass – указывает, что метод будет выполнен до всех тестовых методов тестового класса.
//        4. @BeforeGroups – аннотирует методы, которые будут выполняться перед первым методом в любой из указанных групп.
//        5. @BeforeMethod – аннотированный метод будет выполняться перед каждым тестовым методом.
//        6. @AfterMethod – аннотированный метод будет запускаться после каждого тестового метода.
//        7. @AfterGroups – аннотируется методы, которые будут выполняться после всех методом в любом из указанных групп.
//        8. @AfterClass – аннотированный метод будет запущен после всех тестовых методов в текущем классе.
//        9. @AfterTest – аннотированный метод будет запущен после всех тестовых методов, принадлежащих классам внутри тега <test>.
//        10. @AfterSuite – указывает, что данный метод, будет запускаться после всех методов тестового класса.

    //Проверка, бросит ли исключение тестовый метод. В аннотации указываем ожидаемое исключение.
    @Test(expectedExceptions = NullPointerException.class)
    public void nullPointerExceptionTest() {
        List list = null;
        list.size();
    }

    //Тест будет проигнорирован.
    @Test(enabled = false)
    public void ignoreParameterTest() {
        System.out.println("Test ignored");
    }

    //Устанавливаем лимит времени выполнения теста. Если лимит превышен - бросается ThreadTimeoutException
    @Test(timeOut = 1000)
    public void waitLongTimeTest() throws InterruptedException {
        Thread.sleep(1001);
    }

    //Параметры запуски тестов можно группировать
    @Test(timeOut = 1000, expectedExceptions = ThreadTimeoutException.class)
    public void waitLongTimePositiveTest() throws InterruptedException {
        Thread.sleep(1001);
    }

    // --------------------------------------------------------------------------------------------

    //Для назначения тесту определенной группы используется параметр groups. Далее создается аннотация, в которой перечислены
    //группы для запуска и перечень классов, в которых будут искаться тесты заданной группы.
    @Test(groups = {"unit1", "integration"})
    public void testingMethod1() {
        System.out.println("Тестовый метод №1");
    }

    @Test(groups = {"unit2", "integration"})
    public void testingMethod2() {
        System.out.println("Тестовый метод №2");
    }

    @Test(groups = {"unit1"})
    public void testingMethod3() {
        System.out.println("Тестовый метод №3");
    }

    @Test(groups = {"unit1", "unit2"})
    public void testingMethod4() {
        System.out.println("Тестовый метод №4");
    }

    //----------------------------------------------------------------

    //Можно установить зависимость между методами, т.е. пока не отработает базовый метод, зависимый метод работу не начнет.
    //При попытке запустить зависимый метод сначала запустится базовый метод, а после него запустится зависимый.
    //Так же есть возможность устанавливать зависимость от группы. Зависимый метод выполнится после выполнения методов группы
    //от которой он зависит. Если запуск базового метода будет неудачным, тогда зависимый не выполняется.

    @Test
    public void basicMethod() {
        System.out.println("Это основной метод");
    }

    @Test(dependsOnMethods = {"basicMethod"})
    public void dependentMethod() {
        System.out.println("Это зависимый метод");
    }

    @Test(groups = "basic")
    public void basicMethod1() {
        System.out.println("Это основной метод1");
    }

    @Test(groups = "basic")
    public void basicMethod2() {
        System.out.println("Это основной метод2");
    }

    @Test(dependsOnGroups = {"basic"})
    public void dependentMethod1() {
        System.out.println("Это зависимый метод");
    }
}
