package ramanvesh.goodstein_sequences;

import java.math.BigInteger;
import java.util.Iterator;

/**
 * Created by ram.kasam on 03/02/17.
 *
 * @author ram.kasam
 */
public class GoodsteinSequence implements Iterator<BigInteger> {

  private final int seed;
  private BigInteger previousValue = null;
  private BigInteger previousExponent = BigInteger.ONE;

  public GoodsteinSequence(int seed) {
    this.seed = seed;
  }

  @Override
  public boolean hasNext() {
    return !isZero(previousValue);
  }

  private static boolean isZero(BigInteger num) {
    return BigInteger.ZERO.equals(num);
  }

  @Override
  public BigInteger next() {
    if (previousValue == null) {
      previousValue = BigInteger.valueOf(seed);
    } else {
      previousExponent = previousExponent.add(BigInteger.ONE);
      previousValue = HereditaryNotation.of(previousValue, previousExponent).goodsteinConversion()
          .subtract(BigInteger.ONE);
    }
    return previousValue;
  }

}
