package currency;

import java.util.Objects;

public abstract class Money {
  protected int amount;
  protected String currency;

  public Money(int amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public static Dollar dollar(int amount) {
    return new Dollar(amount, "USD");
  }

  public static Franc franc(int amount) {
    return new Franc(amount, "CHF");
  }

  abstract Money times(int multiplier);
  protected String currency() {
    return currency;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Money money = (Money) o;
    return amount == money.amount;
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount);
  }
}
