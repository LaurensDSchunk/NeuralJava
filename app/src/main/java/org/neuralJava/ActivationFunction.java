package org.neuralJava;

interface ActivationFunction {
  double activate(double x);

  double derivative(double x);
}
