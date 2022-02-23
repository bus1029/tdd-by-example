package currency;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {
  // 1. 작은 테스트를 하나 추가한다.
  // 2. 모든 테스트를 실행해서 테스트가 실패하는 것을 확인한다.
  // 3. 조금 수정한다.
  // 4. 모든 테스트를 실행해서 테스트가 성공하는 것을 확인한다.
  // 5. 중복을 제거하기 위해 리팩토링을 한다.

  @Test
  void testMultiplication() {
    Dollar five = new Dollar(5);
    assertThat(five.times(2)).isEqualTo(new Dollar(10));
    assertThat(five.times(3)).isEqualTo(new Dollar(15));
  }

  @Test
  void testEquality() {
    Dollar dollarFive = new Dollar(5);
    assertThat(dollarFive)
            .isEqualTo(new Dollar(5))
            .isNotEqualTo(new Dollar(6));
    Franc francFive = new Franc(5);
    assertThat(francFive)
            .isEqualTo(new Franc(5))
            .isNotEqualTo(new Franc(6));
  }

  @Test
  void testFrancMultiplication() {
    Franc five = new Franc(5);
    assertThat(five.times(2)).isEqualTo(new Franc(10));
    assertThat(five.times(3)).isEqualTo(new Franc(15));
  }
}
