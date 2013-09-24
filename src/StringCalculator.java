import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
	public int add(String numbers) {
		String[] parseValues = parseValues(numbers);
		List<Integer> values = toIntegers(parseValues);
		
		failIfThereAreNegatives(values);

		int sum = 0;

		for (Integer value: values) {
			if (value <= 1000) {
				sum += value;
			}
		}
		
		return sum;
	}

	private List<Integer> toIntegers(String[] parseValues) {
		List<Integer> values = new ArrayList<>();

		for (String number : parseValues) {
			values.add(Integer.parseInt(number));
		}
		return values;
	}

	private void failIfThereAreNegatives(List<Integer> values) {
		List<Integer> negatives = new ArrayList<>();

		for (Integer value : values) {
			if (value < 0) {
				negatives.add(value);
			}
		}

		if (!negatives.isEmpty()) {
			throw new RuntimeException("Error: negatives not allowed "
					+ negatives);
		}
	}

	private String[] parseValues(String numbers) {
		if (numbers.isEmpty()) {
			return new String[] {};
		}
		if (numbers.startsWith("//")) {
			int newlineIndex = numbers.indexOf('\n');
			String customDelimiters = numbers.substring(2, newlineIndex);
			String numberList = numbers.substring(newlineIndex + 1);
			for (String customDelimiter : customDelimiters
					.replaceAll("\\[", "").split("\\]")) {
				numberList = numberList.replaceAll("\\Q" + customDelimiter + "\\E", ",");
			}
			return numberList.split(",");
		}
		return numbers.split("[,\n]");
	}
}
