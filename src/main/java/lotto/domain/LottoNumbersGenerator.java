package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumbersGenerator {

  private static final List<LottoNumber> CANDIDATE_LOTTO_NUMBERS = initCandidateLottoNumbers();

  public static LottoNumbers generateByRandom() {
    Collections.shuffle(CANDIDATE_LOTTO_NUMBERS);
    return new LottoNumbers(CANDIDATE_LOTTO_NUMBERS.subList(0, LottoNumbers.SIZE));
  }

  private static List<LottoNumber> initCandidateLottoNumbers() {
    return IntStream.rangeClosed(LottoNumber.MIN, LottoNumber.MAX)
        .mapToObj(LottoNumber::valueOf)
        .collect(Collectors.toList());
  }
}
