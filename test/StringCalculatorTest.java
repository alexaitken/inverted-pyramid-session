import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class StringCalculatorTest {

	final StringCalculator sut = new StringCalculator();

	@Test
	public void add_sums_empty_string_to_zero() {
		assertThat(sut.add(""), is(0));
	}

	@Test
	public void add_sums_numbers() {
		assertThat(sut.add("1"), is(1));
		assertThat(sut.add("1,2"), is(3));
		assertThat(sut.add("2,3"), is(5));
		assertThat(sut.add("1,2,3,4"), is(10));
	}

	@Test
	public void add_sums_numbers_when_newline_delimiter() {
		assertThat(sut.add("1,2\n3"), is(6));
	}

	@Test
	public void add_ignores_large_numbers() {
		assertThat(sut.add("999,1000,1001"), is(1999));
	}

	@Test
	public void add_sums_numbers_when_simple_custom_delimiter() {
		assertThat(sut.add("//;\n1;2"), is(3));
		assertThat(sut.add("//x\n1x2x3"), is(6));
		assertThat(sut.add("//***\n1***2***3***4"), is(10));
	}

	@Test
	public void add_sums_numbers_when_multiple_custom_delimiters() {
		assertThat(sut.add("//[***]\n1***2"), is(3));
		assertThat(sut.add("//[;][xx]\n1;2xx3"), is(6));
		assertThat(sut.add("//[x][y]\n1x2y3x4"), is(10));
	}

	@Test
	public void add_fails_for_negative_numbers() {
		try {
			sut.add("1,-2,3,-4");
			fail();
		} catch (RuntimeException expected) {
			assertThat(expected.getMessage(), both(containsString("negatives not allowed")));
		}
	}
}
