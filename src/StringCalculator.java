import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
	public int add(String numbers) {
		List<Integer> negatives = new ArrayList<>();
		int sum = 0;
		String[] parseValues = parseValues(numbers);
		List<Integer> values = new ArrayList<>();

		for (String number : parseValues) {
			values.add(Integer.parseInt(number));
		}
		
		for (Integer value: values) {
			if (value < 0) {
				negatives.add(value);
			}
		}
		
		for (Integer value: values) {
			if (value <= 1000) {
				sum += value;
			}
		}
		
		if (!negatives.isEmpty()) {
			throw new RuntimeException("Error: negatives not allowed " + negatives);
		}
		return sum;
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
