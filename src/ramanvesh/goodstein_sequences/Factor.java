package ramanvesh.goodstein_sequences;

import java.math.BigInteger;

/**
 * Created by ram.kasam on 03/02/17.
 *
 * @author ram.kasam
 */
class Factor {
  private BigInteger coefficient;
  private BigInteger base;
  private HereditaryNotation power;
  private BigInteger value;

  Factor(BigInteger coefficient, BigInteger base, BigInteger power, BigInteger value) {
    this(coefficient, base, HereditaryNotation.of(power, base), value);
  }

  private Factor(
      BigInteger coefficient, BigInteger base, HereditaryNotation power, BigInteger value) {
    this.coefficient = coefficient;
    this.base = base;
    this.power = power;
    this.value = value;
  }

  public Factor goodsteinConversion() {
    BigInteger newBase = base.add(BigInteger.ONE);
    BigInteger newPower = this.power.goodsteinConversion();
    BigInteger newValue = coefficient.multiply(HereditaryNotation.pow(newBase, newPower));
    return new Factor(coefficient, newBase, newPower, newValue);
  }

  public BigInteger getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(coefficient) + "." + base + "pow[" + power.toString() + "]";
  }
}
