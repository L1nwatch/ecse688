import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

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
//        Assertions.assertEquals(3.3, testMockCycle.calculateArea()); #TODO: not know how to fix it
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
}