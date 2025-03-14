package org.neuralJava.Utils;

/*
 * Utility class to hold an (X,Y) point
 */

public class Point {
  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}
