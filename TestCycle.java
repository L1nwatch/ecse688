import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class TestCycle {
    double tolerance = 0.0001;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @org.junit.Test
    public void testCalculateArea() {
        int testRadius = 1;
        Cycle testC = new Cycle(testRadius);
        double expected = Math.PI * testRadius * testRadius;
        double result = testC.calculateArea();
        assertEquals(expected, result, tolerance);
    }
}