package calculator;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAddCalculator {

  private static final String DEFAULT_SPLIT_DELIMITER_REGEX = ",|:";
  private static final String CUSTOM_SPLIT_REGEX = "//(.)\n(.*)";
  private static final int CUSTOM_SPLIT_TARGET_INDEX = 2;
  private static final int CUSTOM_SPLIT_DELIMITER_INDEX = 1;

  public static int splitAndSum(String input) {
    if (StringUtils.isBlank(input)) {
      return 0;
    }
    return getSumResult(split(input));
  }

  private static String[] split(String input) {
    Matcher m = Pattern.compile(CUSTOM_SPLIT_REGEX).matcher(input);
    if (m.find()) {
      return m.group(CUSTOM_SPLIT_TARGET_INDEX).split(m.group(CUSTOM_SPLIT_DELIMITER_INDEX));
    }
    return input.split(DEFAULT_SPLIT_DELIMITER_REGEX);
  }

  private static int getSumResult(String[] tokens) {
    return Arrays.stream(tokens).mapToInt(NaturalNumber::parseNaturalNumber).sum();
  }
}
