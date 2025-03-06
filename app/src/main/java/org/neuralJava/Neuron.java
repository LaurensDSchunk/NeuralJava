package org.neuralJava;

public class Neuron {
  private double[] weights;
  private double bias;
  private ActivationFunction activationFunction;

  public Neuron(int inputCount, ActivationFunction activationFunction) {
    this.activationFunction = activationFunction;
    this.weights = new double[inputCount];
    this.bias = Math.random() * 2 - 1;

    for (int i = 0; i < weights.length; i++) {
      weights[i] = Math.random() * 2 - 1;
    }
  }

  public double feedForward(double[] inputs) {
    double sum = bias;

    for (int i = 0; i < inputs.length; i++) {
      sum += inputs[i] * weights[i];
    }

    return activationFunction.activate(sum);
  }
}
