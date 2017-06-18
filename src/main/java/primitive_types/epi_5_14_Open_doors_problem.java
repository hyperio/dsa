package primitive_types;

/**
 * Five hundred closed doors along a corridor are numbered 1 to 500.
 *
 * Person 1 opens each door.
 * Person 2 closes every other door (2, 4, 6, ...).
 * ...
 * Person i toggles the state of every ith door.
 *
 * Which doors are open after the 500th person has walked through?
 *
 * VARIANTS
 *
 * 1) There are two identical card decks. In a deck cards are numbered form 1 to 25.
 *    25 people seated at a round table, each holding 2 cards.
 *    They iteratively (and synchronised) pass the smaller card to the left.
 *
 *    Show that eventually someone will have 2 cards with identical numbers.
 */
class epi_5_14_Open_doors_problem {

  /**
   * 1) If the state of a door is changed
   * - an odd number of times, it is open.
   * - an even number of times, it is closed.
   *
   * 2) Door i is toggled (# of divisors of i) times.
   *    If d is a divisor of i, dth person will eventually toggle door i.
   *    If i = 12, d = 3, the third person will touch door 12 at the 4th step.
   *
   * 3) So, if the number of divisors is
   * - odd, the door is open.
   * - even, the door is closed.
   *
   * 4) Conjecture: the number of divisors of i is odd if i is a perfect square.
   *    That is, i = n^2 for some n.
   *
   *    Proof:
   *      a) We can pair up divisors of i.
   *         If k is a divisor, i/k is also a divisor. Pair up (k, i/k).
   *      b) Exception: sqrt(i) has no pair, or is not a divisor.
   *      c) Case 1:
   *           - sqrt(i) is a divisor, ie. sqrt(i) is an integer (as opposed to fractional).
   *           - It has no pair, as i/sqrt(i) = sqrt(i) |
   *           - All other divisors have a pair         | => The number of divisors is odd.
   *           - Also, since sqrt(i) is an integer, i is a perfect square, i = sqrt(i) * sqrt(i).
   *         Case 2:
   *           - sqrt(i) is a fractional number, so i is not a perfect square.
   *           - The number of divisors in this case is even, as all of them are paired up.
   *    
   * Answer: door i is open if i is a perfect square.
   */
  static boolean doorIsOpen(int i) {
    double sqrt = Math.sqrt(i);
    int floorSqrt = (int) Math.floor(sqrt);
    return floorSqrt * floorSqrt == i;
  }

  /*
   * VARIANTS
   */

  /*
   * Variant 1
   *
   * Proof: scenario describing the maximum number of moves until
   *        a person ends up with 2 cards that have the same number.
   *
   * 1) a 25 card will never pass on
   * 2) a 24 card can only be passed on by people holding a card 25
   *    (otherwise 24 is the larger and is not passed).
   *   a 24 card can moves
   *     - once, if passed by a 25 person (ie. person holding a 25 card) to a non-25 neighbour
   *     - twice if the neighbour is also 25 and passes the 24 on
   *   After that it won't move, as it will always be the larger of 2, since 25 does not move.
   * 3) a 23 card can be passed at most 4 times, when players are in this order:
   *    25 and 23 person, 25 person, 24 person, 24 person, 23 receiver
   *    The first 25 person passes it on. It ends up at the 23 receiver, next to the last 24 person.
   * 4) ... And so on until The 24 highest cards, 14-25, are stabilised.
   * 5) 13 then gets passed on and it ends up at the 25th person, who holds it forever
   *    (the higher ones are stabilised, ie. won't get passed on anymore).
   *    Eventually the next 13 arrives at the 25th person.
   */
  

}
