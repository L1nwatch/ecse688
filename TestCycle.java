import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.junit.Assume;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

@RunWith(Theories.class)
@ExtendWith(MockitoExtension.class)
public class TestCycle {
    double tolerance = 0.0001;
    @Mock
    private Cycle testMockCycle;
    private Cycle testCycle = new Cycle();


    @BeforeEach
    void setUp() {
        Assertions.assertEquals(false, testMockCycle.isAllowRun());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testMock() {
        when(testMockCycle.isAllowRun()).thenReturn(true);
        Assertions.assertEquals(true, testMockCycle.isAllowRun());
//        Assertions.assertEquals(3.3, testMockCycle.calculateArea()); // it would fail because it's a full mock class
    }
    @Test
    public void testSpyMock() {
        Cycle testMockCycle = spy(testCycle);
        when(testMockCycle.isAllowRun()).thenReturn(true);
        Assertions.assertTrue(testMockCycle.isAllowRun());
        Assertions.assertEquals(3.3, testMockCycle.calculateArea());
    }

    @Test
    public void testCalculateArea() {
        int testRadius = 1;
        testCycle.setRadius(testRadius);

        double expected = Math.PI * testRadius * testRadius;
        double result = testCycle.calculateArea();
        Assertions.assertEquals(expected, result, tolerance);
    }

    @RepeatedTest(3)
    public void testCalculateRandomArea() {
        double testRadius = Math.random() * 100;
        testCycle.setRadius(testRadius);

        double expected = Math.PI * testRadius * testRadius;
        double result = testCycle.calculateArea();
        Assertions.assertEquals(expected, result, tolerance);
    }

    @Test
    public void testCalculateAreaAssertAll() {
        double testRadius = Math.random() * 100;
        testCycle.setRadius(testRadius);

        double expected = Math.PI * testRadius * testRadius;
        double result = testCycle.calculateArea();
        Assertions.assertAll(
                () -> Assertions.assertEquals(expected, result, tolerance),
                () -> Assertions.assertTrue(result > 0)
        );
    }

    @Test
    public void testCalculateAreaAssertThrows() {
        double testRadius = Math.random() * 100;
        testCycle.setRadius(testRadius);

        double expected = Math.PI * testRadius * testRadius;
        double result = testCycle.calculateArea();
        Assertions.assertEquals(expected, result, tolerance);
        // example:
//        Assertions.assertThrows(Exception.class, () -> Assertions.assertTrue(result < 0));
    }

    @ParameterizedTest
    @ValueSource(doubles = {1, 2, 2.3, 3, 4, 5})
    public void parameterizeTestCalculateArea(double testRadius) {
        testCycle.setRadius(testRadius);

        double expected = Math.PI * testRadius * testRadius;
        double result = testCycle.calculateArea();
        Assertions.assertEquals(expected, result, tolerance);
    }

    @ParameterizedTest(name = "run#{index}with args [{arguments}]")
    @CsvSource({"11", "22"})
    public void parameterizeTest2CalculateArea(double testRadius) {
        testCycle.setRadius(testRadius);

        double expected = Math.PI * testRadius * testRadius;
        double result = testCycle.calculateArea();
        Assertions.assertEquals(expected, result, tolerance);
    }

    @DataPoints
    public static double[] samples = {-1, 0, 1, 2, 3, 4, 5.5};

    @Theory
    public void theoryTestCalculateArea(double smallRadius, double bigRadius) {
        Assume.assumeTrue(smallRadius > 0);
        Assume.assumeTrue(bigRadius > 0);
        Assume.assumeTrue(bigRadius > smallRadius);

        Cycle smallCycle = new Cycle(smallRadius);
        Cycle bigCycle = new Cycle(bigRadius);
        double smallCycleArea = smallCycle.calculateArea();
        double bigCycleArea = bigCycle.calculateArea();
        Assertions.assertTrue(bigCycleArea > smallCycleArea);
    }
}