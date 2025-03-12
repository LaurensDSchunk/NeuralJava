package org.neuralJava;

class None implements ActivationFunction {
  public double activate(double x) {
    return x;
  }

  public double derivative(double x) {
    return 1;
  }
}
