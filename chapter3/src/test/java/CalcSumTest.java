import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import template.Calculator;

import java.io.IOException;


public class CalcSumTest {

    Calculator calculator;
    String numFilepath;

    @BeforeEach
    public void setup() {
        this.calculator = new Calculator();
        this.numFilepath = getClass().getResource("number.txt").getPath();
    }

    @Test
    public void sumOfNumber() throws Throwable {
        Assertions.assertThat(calculator.calcSum(this.numFilepath)).isEqualTo(10);
    }

    @Test
    public void multiplyOfNumber() throws IOException {
        Assertions.assertThat(calculator.calcMultiply(this.numFilepath)).isEqualTo(24);
    }
}
