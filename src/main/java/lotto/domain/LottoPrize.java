package lotto.domain;

import java.util.Arrays;
import java.util.List;

public enum LottoPrize {
  FIRST(6, 2000000000),
  SECOND(5, 1500000),
  THIRD(4, 50000),
  FOURTH(3, 5000);

  private static final List<LottoPrize> LOTTO_PRIZE_LIST = Arrays.asList(LottoPrize.values());
  private final int matchedCnt;
  private final int prizeMoney;

  LottoPrize(int matchedCnt, int prizeMoney) {
    this.matchedCnt = matchedCnt;
    this.prizeMoney = prizeMoney;
  }

  public static LottoPrize of(int matchedCnt) {
    return LOTTO_PRIZE_LIST.stream()
        .filter(lottoPrize -> lottoPrize.matchedCnt == matchedCnt)
        .findAny()
        .orElseThrow(() -> new IllegalArgumentException("일치하는 상금이 없습니다."));
  }

  public int getPrizeMoney() {
    return prizeMoney;
  }
}
