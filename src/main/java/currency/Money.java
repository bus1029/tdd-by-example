package currency;

import java.util.Objects;

public class Money {
  protected int amount;
  protected String currency;

  protected Money(int amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public static Dollar dollar(int amount) {
    return new Dollar(amount, "USD");
  }

  public static Franc franc(int amount) {
    return new Franc(amount, "CHF");
  }

  Money times(int multiplier) {
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
}
