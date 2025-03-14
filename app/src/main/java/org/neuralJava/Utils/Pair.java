package org.neuralJava.Utils;

/*
 * Utility class to hold two objects in the same place, making things cleaner
 */

public class Pair<T, U> {
  public T first;
  public U second;

  public Pair(T a, U b) {
    this.first = a;
    this.second = b;
  }

  public String toString() {
    return "<" + first + ", " + second + ">";
  }
}
