import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class StringCalculator {

  public int add(String numbers) {
    List<Integer> values = convertToInteger(parseValues(numbers));
    failWhenContainsNegatives(values);
    return sumOf(values);
  }

  private String[] parseValues(String numbers) {
    if (numbers.isEmpty()) {
      return new String[] {};
    }
    if (containsCustomDelimiters(numbers)) {
      return parseCustomDelimitedValues(numbers);
    }
    return parseStandardDelimiters(numbers);
  }

  private List<Integer> convertToInteger(String[] numbers) {
    List<Integer> result = new ArrayList<>();
    for (String number : numbers) {
      result.add(toInteger(number));
    }
    return result;
  }

  private List<Integer> convertToIntegerNonSlap(String[] numbers) {
    List<Integer> result = new ArrayList<>();
    for (String number : numbers) {
      Integer value = Integer.parseInt(number);
      result.add(value <= 1000 ? value : 0);
    }
    return result;
  }

  private void failWhenContainsNegatives(List<Integer> values) {
    List<Integer> negatives = new ArrayList<>();
    for (Integer value : values) {
      if (value < 0)
        negatives.add(value);
    }
    if (!negatives.isEmpty()) {
      throw new RuntimeException("Error: negatives not allowed " + negatives);
    }
  }

  private int sumOf(List<Integer> values) {
    int result = 0;
    for (Integer value : values) {
      result += value;
    }
    return result;
  }

  private Integer toInteger(String number) {
    Integer value = Integer.parseInt(number);
    return value <= 1000 ? value : 0;
  }

  private boolean containsCustomDelimiters(String numbers) {
    return numbers.startsWith("//");
  }

  private String[] parseCustomDelimitedValues(String numbers) { /*
                                                                 * this is a good method for slap
                                                                 */
    int newlineIndex = numbers.indexOf('\n');
    String rawCustomDelimiters = numbers.substring(2, newlineIndex);
    String numberList = numbers.substring(newlineIndex + 1);
    for (String customDelimiter : parseCustomDelimiters(rawCustomDelimiters)) {
      numberList = numberList.replaceAll(
          quoteRegularExpression(customDelimiter), ",");
    }
    return numberList.split(",");
  }

  private String[] parseCustomDelimiters(String rawCustomDelimiters) {
    return rawCustomDelimiters.replaceAll("\\[", "").split("\\]");
  }

  private String quoteRegularExpression(String customSeparator) {
    return "\\Q" + customSeparator + "\\E";
  }

  private String[] parseStandardDelimiters(String numbers) {
    return numbers.split("[,\n]");
  }
}
