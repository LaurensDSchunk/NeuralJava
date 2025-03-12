package org.neuralJava.Utils;

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
