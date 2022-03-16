package currency;

import java.util.Objects;

public class Money implements Expression {
  protected int amount;
  protected String currency;

  protected Money(int amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public static Money dollar(int amount) {
    return new Money(amount, "USD");
  }

  public static Money franc(int amount) {
    return new Money(amount, "CHF");
  }

  Expression times(int multiplier) {
    return new Money(amount * multiplier, currency);
  }

  protected String currency() {
    return currency;
  }

  @Override
  public boolean equals(Object o) {
    Money money = (Money) o;
    return amount == money.amount
            && currency().equals(money.currency());
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, currency);
  }

  @Override
  public String toString() {
    return "Money{" +
            "amount=" + amount +
            ", currency='" + currency + '\'' +
            '}';
  }

  public Expression plus(Expression addend) {
    return new Sum(this, addend);
  }

  public Money reduce(Bank bank, String to) {
    int rate = bank.rate(currency, to);
    return new Money(amount/rate, to);
  }
}
