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
//    Dollar five = new Dollar(5);
    Money five = Money.dollar(5);
    assertThat(five.times(2)).isEqualTo(Money.dollar(10));
    assertThat(five.times(3)).isEqualTo(Money.dollar(15));
  }

  @Test
  void testEquality() {
    Money dollarFive = Money.dollar(5);
    assertThat(dollarFive)
            .isEqualTo(Money.dollar(5))
            .isNotEqualTo(Money.dollar(6));
    Money francFive = Money.franc(5);
    assertThat(francFive)
            .isNotEqualTo(Money.dollar(5));
  }

  @Test
  void testCurrency() {
    assertThat(Money.dollar(1).currency()).isEqualTo("USD");
    assertThat(Money.franc(1).currency()).isEqualTo("CHF");
  }

  @Test
  void testSimpleAddition() {
    Money five = Money.dollar(5);
    Expression sum = five.plus(five);
    Bank bank = new Bank();
    Money reduced = bank.reduce(sum, "USD");
    assertThat(reduced).isEqualTo(Money.dollar(10));
  }

  @Test
  void testPlusReturnsSum() {
    Money five = Money.dollar(5);
    Expression result = five.plus(five);
    Sum sum = (Sum) result;
    assertThat(five)
            .isEqualTo(sum.augend)
            .isEqualTo(sum.addend);
  }

  @Test
  void testReduceSum() {
    Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
    Bank bank = new Bank();
    Money result = bank.reduce(sum, "USD");
    assertThat(result).isEqualTo(Money.dollar(7));
  }

  @Test
  void testReduceMoney() {
    Bank bank = new Bank();
    Money result = bank.reduce(Money.dollar(1), "USD");
    assertThat(result).isEqualTo(Money.dollar(1));
  }

  @Test
  void testReduceMoneyDifferentCurrency() {
    Bank bank = new Bank();
    bank.addRate("CHF", "USD", 2);
    Money result = bank.reduce(Money.franc(2), "USD");
    assertThat(result).isEqualTo(Money.dollar(1));
  }

  @Test
  void testIdentityRate() {
    assertThat(new Bank().rate("USD", "USD")).isEqualTo(1);
  }
}
