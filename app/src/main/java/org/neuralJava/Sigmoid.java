package org.neuralJava;

class Sigmoid implements ActivationFunction {
  public double activate(double x) {
    return 1 / (1 + Math.exp(-x));
  }

  public double derivative(double x) {
    double sigmoid = activate(x);
    return sigmoid * (1 - sigmoid);
  }
}
