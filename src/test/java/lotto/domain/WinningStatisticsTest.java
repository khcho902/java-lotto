package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningStatisticsTest {

  @Test
  @DisplayName("당첨 통계값 생성 및 결과값 확인 테스트")
  void createTest() {
    //given
    Lotto winningLotto = Lotto.issueByManual(Arrays.asList(1, 2, 3, 4, 5, 6));

    Lotto lotto1 = Lotto.issueByManual(Arrays.asList(1, 2, 3, 4, 5, 6));        // 6개 일치 => FIRST
    Lotto lotto2 = Lotto.issueByManual(Arrays.asList(1, 3, 6, 10, 11, 12));     // 3개 일치 => FOURTH
    Lotto lotto3 = Lotto.issueByManual(Arrays.asList(1, 2, 3, 42, 44, 45));     // 3개 일치 => FOURTH
    Lotto lotto4 = Lotto.issueByManual(Arrays.asList(1, 20, 30, 40, 41, 42));   // 1개 일치 => NOTHING

    List<Lotto> lottos = Arrays.asList(lotto1, lotto2, lotto3, lotto4);

    //when
    WinningStatistics statistics = new WinningStatistics(lottos, winningLotto);

    //then
    assertThat(statistics.cntByLottoPrize(LottoPrize.FIRST)).isEqualTo(1);
    assertThat(statistics.cntByLottoPrize(LottoPrize.SECOND)).isEqualTo(0);
    assertThat(statistics.cntByLottoPrize(LottoPrize.THIRD)).isEqualTo(0);
    assertThat(statistics.cntByLottoPrize(LottoPrize.FOURTH)).isEqualTo(2);
    assertThat(statistics.cntByLottoPrize(LottoPrize.NOTHING)).isEqualTo(1);
  }

  @Test
  @DisplayName("총 당첨금 구하기 테스트")
  void calcTotalPrizeMoneyTest() {
    //given
    Lotto winningLotto = Lotto.issueByManual(Arrays.asList(1, 2, 3, 4, 5, 6));

    Lotto lotto1 = Lotto.issueByManual(Arrays.asList(1, 2, 3, 4, 5, 6));        // 6개 일치 => FIRST
    Lotto lotto2 = Lotto.issueByManual(Arrays.asList(1, 3, 6, 10, 11, 12));     // 3개 일치 => FOURTH
    Lotto lotto3 = Lotto.issueByManual(Arrays.asList(1, 2, 3, 42, 44, 45));     // 3개 일치 => FOURTH
    Lotto lotto4 = Lotto.issueByManual(Arrays.asList(1, 20, 30, 40, 41, 42));   // 1개 일치 => NOTHING

    List<Lotto> lottos = Arrays.asList(lotto1, lotto2, lotto3, lotto4);

    //when
    WinningStatistics statistics = new WinningStatistics(lottos, winningLotto);

    //then
    assertThat(statistics.totalPrizeMoney())
        .isEqualTo((LottoPrize.FIRST.prizeMoney()) + (LottoPrize.FOURTH.prizeMoney() * 2));
  }
}
