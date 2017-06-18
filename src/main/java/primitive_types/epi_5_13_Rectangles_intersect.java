package primitive_types;

import java.util.stream.Stream;

/**
 * Check if two rectangles intersect.
 * If so, return the rectangle that is the intersection.
 *
 * A rectangle is specified by the left low point, width and height.
 *
 * EDGE CASES
 *
 * 1) Is the perimeter (ie. the edges) part of the rectangle?
 *
 * VARIANTS
 *
 * 1) Given four points in the plane,
 *    check if they are the vertices of a rectangle?
 *
 * 2) Check that 2 rectangles, not necessarily
 *    aligned with the X an Y axes, intersect.
 *
 * 3) Check if a given point is inside, on the edge, or outside a given polygon.
 */
class epi_5_13_Rectangles_intersect {

  /**
   * i.x = max(a.x, b.x)
   * i.y = max(a.y, b.y)
   * i.width = min(a.x + a.width, b.x + b.width) - i.x
   * i.height = min(a.x + a.height, b.x + b.height) - i.y
   *
   * (a.x, a.y + a.height)---------------------+
   *     |                                     |
   *     |                                     |
   *     |         (b.x, b.y + b.height)-----------------------+
   *     |             |                       |               |
   *     |             |                       |               |
   *     |             |                       |               |
   * (a.x, a.y)----(i.x, i.y)----(a.x + a.width, a.y)          |
   *                   |                                       |
   *                   |                                       |
   *                   |                                       |
   *               (b.x, b.y)--------------------(b.x + b.width, b.y)
   */
  static StraightRectangle getIntersection(StraightRectangle a, StraightRectangle b) {
    if (isIntersection(a, b)) {
      int bottomLeftX = Math.max(a.x, b.x);
      int bottomLeftY = Math.max(a.y, b.y);

      int bottomRightX = Math.min(a.x + a.width, b.x + b.width);

      int topLeftY = Math.min(a.y + a.height, b.y + b.height);

      int width = bottomRightX - bottomLeftX;
      int height = topLeftY - bottomLeftY;

      return new StraightRectangle(bottomLeftX, bottomLeftX, width, height);
    }
    // some convention on what to return if they do not intersect
    return new StraightRectangle(0, 0, -1, -1);
  }

  /**
   * Easier to say when they do not intersect.
   *
   *    +------+     +                              +
   *    |   b  |     |                              |                  +------+
   * (xb, yb)--+     |                              |                  |   b  |
   *                 |                              |               (xb, yb)--+
   *    +------+     |      +------+     +------+   |      +------+
   *    |   a  |     |      |   a  |     |   b  |   |      |   a  |
   * (xa, ya)--+     |   (xa, ya)--+  (xb, yb)--+   |   (xa, ya)--+
   *                 |                              |
   * ya + ha < yb || | xa + wa < xb || xb + wb < xa |
   * yb + hb < ya    |                              |
   *                 +                              +
   */
  static boolean isIntersection(StraightRectangle a, StraightRectangle b) {
    boolean notIntersection =
      a.y + a.height < b.y || b.y + b.height < a.y ||
      a.x + a.width < b.x || b.x + b.width < a.x;
    return !notIntersection;
  }

  /*
   * VARIANTS
   */

  /*
   * Variant 1) Given four points in the plane,
   * check if they are the vertices of a rectangle.
   */

  /**
   * Method 1
   *
   * (x1, y2), (x2, y2), (x3, y3), (x4, y4)
   *
   * (x4, y4)                             (x3, y3)
   *
   *
   *
   *   (x1, y1)              (x2, y2)
   *
   * For all possible orders of points:
   *   x1 = x4, x2 = x3
   *   y1 = y2, y3 = y4
   *
   */
  static boolean isRectangle1(Point p1, Point p2, Point p3, Point p4) {
    return p1.x == p4.x && p2.x == p3.x && p1.y == p2.y && p3.y == p4.y;
  }

  /**
   * Method 2
   *
   * 1. Compute center of mass of x coordinates and of y coordinates
   *    cx = (p1.x + p2.x + p3.x + p4.x) / 4
   *    cy = (p1.y + p2.y + p3.y + p4.y) / 4
   *
   * 2. Check that the distance from (cx, cy) to all 4 points is equal
   *
   * Equivalent to: Find circumcircle and check that all 4 points lie on the circle.
   */
  static boolean isRectangle2(Point p1, Point p2, Point p3, Point p4) {
    double cx = (p1.x + p2.x + p3.x + p4.x) / 4;
    double cy = (p1.y + p2.y + p3.y + p4.y) / 4;

    // c2pi = distance from c to pi
    double c2p1 = Math.pow(p1.x - cx, 2) + Math.pow(p1.y - cy, 2);
    double c2p2 = Math.pow(p2.x - cx, 2) + Math.pow(p2.y - cy, 2);
    double c2p3 = Math.pow(p3.x - cx, 2) + Math.pow(p3.y - cy, 2);
    double c2p4 = Math.pow(p4.x - cx, 2) + Math.pow(p4.y - cy, 2);

    return c2p1 == c2p2 && c2p1 == c2p3 && c2p1 == c2p4;
  }

  /*
   * Variant 2) Check that 2 rectangles,
   * not necessarily aligned with the X an Y axes, intersect.
   */

  /**
   * Method 1
   *
   * Let R1(A, B, C, D) and R2(A, B, C, D).
   *
   * Case 1) One of the points of R1 is inside R2.
   *         D---------------------C
   *         |                     |
   * D----------------C            |
   * |       |        |            |
   * |       |        |            |
   * |       | R2     |            |
   * |       A---------------------B
   * |                |
   * | R1             |
   * A----------------B
   *
   * Case 2) The projections of at least 2 points from R2
   *         fall on an edge of R1.
   *
   *         D------------C
   *         |            |
   *         |            |
   *         |            |
   * D-----------------------------C
   * |       |            |        |
   * | R2    |            |        |
   * A-----------------------------B
   *         |            |
   *         |            |
   *         |            |
   *         | R1         |
   *         A------------B
   *
   * To check:
   * - R2.A and R2.D are on R1.AD or
   * - R2.A and R2.B are on R1.AB
   *
   * - R1.A and R1.D are on R2.AD or
   * - R1.A and R1.B are on R2.AB
   */
  static boolean isRectangleIntersection(AnyRectangle r1, AnyRectangle r2) {
    boolean r1HasPointInsideR2 = Stream.of(r1.A, r1.B, r1.C, r1.D)
      .anyMatch(point -> isPointInsideRectangle(point, r2));

    if (r1HasPointInsideR2) {
      return true;
    }

    // R2.A and R2.D are on R1.AD or
    // R2.A and R2.B are on R1.AB or
    boolean r1OnR2 = (isPointBetween2Points(r1.A, r2.A, r2.D) && isPointBetween2Points(r1.D, r2.A, r2.D)) ||
      (isPointBetween2Points(r1.A, r2.A, r2.B)) && isPointBetween2Points(r1.B, r2.A, r2.B);

   // R1.A and R1.D are on R2.AD or
   // R1.A and R1.B are on R2.AB
    boolean r2OnR1 = (isPointBetween2Points(r2.A, r1.A, r1.D) && isPointBetween2Points(r2.D, r1.A, r1.D)) ||
      (isPointBetween2Points(r2.A, r1.A, r1.B)) && isPointBetween2Points(r2.B, r1.A, r1.B);

    return r1OnR2 || r2OnR1;
  }

  /*
   * Variant 2) Check if a given point is inside,
   * on the edge, or outside a given polygon.
   */

  /**
   * Method 1
   *
   * Check that
   * - the projection H of M on vector AB is between points A and B.
   * - the projection V of M on vector AD is between points A and D.
   *
   * VECTORS
   *
   * Given points A and B, the vector vAB=(B.x - A.x, B.y - A.y).
   * A vector is a point in relationship with the origin.
   *
   *--- It has
   *    - a direction, with respect to the origin.
   *    - a magnitude, which is the distance from the origin to the point.
   *
   *--- A geometric view of the direction and magnitude is a line
   *    with an arrow from the origin to the point.
   *    The line is parallel to the segment AB and of the same length.
   *
   *--- The Inner Product of two vectors x and y is defined as
   *
   *    x * y = sum_{i = 1 to d}(xi * yi) = |x| * |y| * cos(x,y)
   *
   *   where
   *   - d is the number of dimensions
   *   - |x| is the length of x, |x| = sqrt(x * x)
   *
   *-- The scalar projection of a vector x onto a vector y,
   *   ie. the length of the projection of x onto y is
   *
   *    p(x, y) = |x| * cos(x,y).
   *
   *    So the inner product x * y is
   *    (the length of y) multiplied by (the length of the projection of x onto y).
   *
   * This has an interesting application in our case.
   *
   * If 0 <= vAM * vAB <= |vAB| then the projection of point M onto segment AB
   * is between A and B, as opposed to outside the segment.
   *
   * If this is true for vAM and vAD as well
   * (since BC is parallel to AD and CD is parallel to AB)
   * then point M lies inside rectangle ABCD.
   *
   *  D(2, 8)-------------------------------------------------C(10, 8)
   *     |                                                        |
   *     |                                                        |
   *  V(2, 6)- - - - - - - - - - - M(4, 6)                        |
   *     |                           /|                           |
   *     |                        /                               |
   *     |                     /      |                           |
   *     |                  /                                     |
   *     |               /            |                           |
   *     |            /                                           |
   *     |         /                  |                           |
   *     |      /                                                 |
   *     |   /                        |                           |
   *     |/                                                       |
   *  A(2, 3)----------------------H(4, 3)--------------------B(10, 3)
   *
   * The rectangle could be rotated) in any way.
   */
  static boolean isPointInsideRectangle(Point m, AnyRectangle r) {
    Vector vAB = new Vector(r.B.x - r.A.x, r.B.y - r.A.y);
    Vector vAD = new Vector(r.D.x - r.A.x, r.D.y - r.A.y);
    Vector vAM = new Vector(m.x - r.A.x, m.y - r.A.y);

    int lenAB = innerProduct(vAB, vAB);
    int lenProjMOntoAB = innerProduct(vAB, vAM);

    int lenAD = innerProduct(vAB, vAD);
    int lenProjMOntoAD= innerProduct(vAD, vAM);

    return 0 <= lenProjMOntoAB && lenProjMOntoAB <= lenAB &&
      0 <= lenProjMOntoAD && lenProjMOntoAD <= lenAD;
  }

  /**
   * Checks if a point M is between two other points A and B,
   * ie. if the projection of M onto the line AB is on the segment AB
   * as opposed to outside the segment.
   */
  static boolean isPointBetween2Points(Point m, Point a, Point b) {
    Vector vAB = new Vector(b.x - a.x, b.y - a.y);
    Vector vAM = new Vector(m.x - a.x, m.y - a.y);

    int lenVAB = innerProduct(vAB, vAB);
    int lenProjMOntoVAB = innerProduct(vAM, vAB);

    return 0 <= lenProjMOntoVAB && lenProjMOntoVAB <= lenVAB;
  }

  static int innerProduct(Vector a, Vector b) {
    return a.x * b.x + a.y * b.y;
  }

}


class Point {
  int x, y;

  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

class Vector {
  int x, y;

  Vector(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

/**
 * A rectangle with the edges parallel to to axes.
 *
 *  A rectangle could also be specified by
 * the low-left point A and the up-right point C.
 *
 * This gives us the x-variation, dx = C.x - A.x
 * and the y variation dy = C.y - A.y.
 *
 *    +------------------------C(x, y)
 *    |                           |
 *    |                           |
 *    |                           |
 *    |                           |
 *    |                           |
 *    |                           |
 * A(x, y)---------------------B(?, ?)
 *
 */
class StraightRectangle {
  int x, y, width, height;

  StraightRectangle(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }
}


class AnyRectangle {
  Point A, B, C, D;

  AnyRectangle(Point A, Point B, Point C, Point D) {
    this.A = A;
    this.B = B;
    this.C = C;
    this.D = D;
  }
}