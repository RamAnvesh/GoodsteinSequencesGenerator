package ramanvesh.goodstein_sequences;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ram.kasam on 03/02/17.
 *
 * @author ram.kasam
 */
public class HereditaryNotation {

  private List<Factor> factors = new LinkedList<>();

  private BigInteger of;
  private BigInteger base;

  public static HereditaryNotation of(BigInteger num, BigInteger base) {
    List<Factor> factors = new LinkedList<>();
    while (num.compareTo(BigInteger.ZERO) > 0) {
      Factor factor = getFactor(num, base);
      factors.add(factor);
      num = num.subtract(factor.getValue());
    }
    return new HereditaryNotation(factors, num, base);
  }

  private static Factor getFactor(BigInteger num, BigInteger base) {
    BigInteger power = BigInteger.ZERO;
    BigInteger result = pow(base, power);
    BigInteger local;
    while ((local = pow(base, power.add(BigInteger.ONE))).compareTo(num) <= 0) {
      result = local;
      power = power.add(BigInteger.ONE);
    }
    num = num.subtract(result);
    BigInteger coefficient = BigInteger.ONE;
    while (result.compareTo(num) <= 0) {
      num = num.subtract(result);
      coefficient = coefficient.add(BigInteger.ONE);
    }

    return new Factor(coefficient, base, power, result.multiply(coefficient));
  }


  private HereditaryNotation(List<Factor> factors, BigInteger num, BigInteger base) {
    this.factors = factors;
    this.of = num;
    this.base = base;
  }

  public BigInteger goodsteinConversion() {
    if (factors.size() == 0) {
      return BigInteger.ZERO;
    }
    BigInteger result = BigInteger.ZERO;
    for (Factor factor : factors) {
      result = result.add(factor.goodsteinConversion().getValue());
    }
    return result;
  }

  static BigInteger pow(BigInteger base, BigInteger exponent) {
    BigInteger result = BigInteger.ONE;
    while (exponent.signum() > 0) {
      if (exponent.testBit(0)) {
        result = result.multiply(base);
      }
      base = base.multiply(base);
      exponent = exponent.shiftRight(1);
    }
    return result;
  }

  public BigInteger getValue() {
    return of;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    factors.forEach(factor -> {
      builder.append(factor.toString()).append(" + ");
    });
    return builder.append("0").toString();
  }
}
