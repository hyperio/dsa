package primitive_types;

/**
 * Generate uniform random numbers between a and b
 * using a uniform random number generator that produces either 0 or 1
 *
 * VARIANT
 *
 * using a uniform random number that produces x \in [0, 1)
 */
public class epi_5_12_Uniform_random_between_a_and_b {

  /**
   * Generate random number x, a <= x <= b, bit by bit.
   * For simplicity, generate x1, 0 <= x1 <= b - a and x = x1 + a.
   *
   * Let t = b - a + 1. Need to generate 0 <= x <= t - 1.
   * 1) If t = 2^i, for some i, then we simply generate i bits.
   *    The maximum number represented on i bits is 2^i - 1 = t - 1.
   * 2) If t < 2^i, for some minimum i that meets this requirement,
   *    keep generating i bits until the result happens to be smaller that t.
   *
   * COMPLEXITY
   *
   * Time
   *   - In each attempt, we generate on of 2^i possible numbers.
   *   - Out of the possible ones, t are good and 2^i - t are not.
   *   - So, the probability of a bad [> t, so out of range] number
   *     in a single attempt is (2^i - t) / 2^i.
   *   - So, the probability of k bad numbers, in k consecutive attempts,
   *     is [ (2^i - t) / 2^i ]^k.
   *   - Since t > 2^(i - 1) (otherwise i would be i - 1)
   *     (2^i - t) / 2^i = 1 - t/2^i < 1/2 since t/2^i > 1/2.
   *   - So, [ (2^i - t) / 2^i ]^k < (1/2)^k
   *   - Continuing our probabilistic interpretation,
   *     let p(X=k) = (1/2)^k, as (1/2)^k is an upper bound.
   *   - The average time complexity is the expected value
   *     E[X] = 1*(1/2)^1 + 2*(1/2)^2 + 3*(1/2)^3 + ...
   *
   *   - The ratio convergence test says a series converges if
   *     lim(n->infinity)(a_(n+1)/a_n) < 1
   *   - In our case, lim(n -> infinity) ((n + 1)/2^(n + 1))/(n/2^n)
   *     = lim(n -> infinity) (n + 1)/2n = lim(n -> infinity) (1/2 + 1/2n) = 1/2 < 1.
   *   - So the series converges (to 2 actually but won't compute, doesn't matter).
   *     Important is that it converges to a number, so the average time is bounded
   *     by a number, ie. a constant.
   *   - So the average time is O(1).
   */
  static int randomAB1(int a, int b) {
    int t = b - a + 1;
    int res;

    do {
      res = 0;
      for (int i = 0; (1 << i) < t; i++) {
        res = (res << 1) | random0Or1();
      }
    } while (res >= t);

    return res + a;
  }

  static int random0Or1() {
    if (Math.random() < 0.5) { // x \in [0, 0.5)
      return 0;
    } else { // x \in [0.5, 1)
      return 1;
    }
  }

  /*
   * VARIANT
   */

  /**
   * randomAB = a + (b - a)*random01
   */
  static double randomABv1(int a, int b) {
    return a + (b - a) * random01();
  }

  static double random01() {
    return Math.random();
  }

}
