# Bit manipulation

## Facts and tricks

- x ^ 0s = x, x ^ 1s = ~x, x ^ x = 0
- x & 0s = 0, x & 1s = x, x & x = x
- x | 0s = x, x | 1s = 1s, x | x = x

## 2s complement
The 2's complement of an $N$-bit number (where $N$ is the number of bits used for the
number, excluding the sign bit) is the complement of the number with respect to 2^N.

Consider -3 on 4 bits. The complement of 3 (absolute value of -3) with respect to
2^3 = 8 is 5. So -3 in 2's complement is 1101 (leading 1 indicates a negative number).

They can also be achieved by flipping all bits and then adding 1.

## Shifting

- Arithmetic shift: (1)101 >> 1 = (1)110 (sign bit is duplicated. Weird.)
- Logical shift: (1)101 >>> 1 = (0)010


## Facts

- On n bits we can represent numbers 0 .. (2^n) - 1. Eg. if n = 3, we can represent from 0 (000) to 2^3 - 1 = 7 (111).
- -1 in 2s complement is a sequence of 1s (sign bit is 1). ~0 is a sequence of 1s as well (sign bit is 0).
- c & (c - 1) clears the least significant 1 in c. If c & (c - 1) == 0, c is a a power of 2.
- c & ~(c - 1) get the least significant 1 in c. That is, if c = 1100, c & ~(c-1) = 0100.
Same result is achieved by c & (-c) (2s complement)
- To represent n in binary we need [floor(log2(n)) + 1] bits. In general, the number of digits
in base b required to represent the decimal integer n is [floor(logb(n)) + 1]. b could be 10,
giving the number of decimal digits of an int.

## Common Bit masks

Convention: first bit := MSB, last bit := LSB

On n bits:
- Set last k bits: 2^k - 1 in binary is a sequence of n - k 0s followed by k 1s.
If n = 5, k = 2, 2^2 - 1 = 00100 - 1 = 00011.
- Set first k bits: ~0 << (n - k).
If n = 5, k = 2, 11111 << (5 - 2) = 11000.  

## Base conversions

2.25_10 = 10.01_2

2 -> 10
- integer part: 0 * 2^0 + 1 * 2 ^ 1 = 2
- fractional part: 0 * 1/2^1 + 1 * 1/2^2 = 1/4 = 0.25

10 -> 2
- integer part (2): repeat (divide by 2) until 0
{ add remainder, from LSB to MSB }
- fractional part (0.25): repeat (multiply by 2) until 0
{ if (number >= 1) add 1 and number -= 1 else add 0 }