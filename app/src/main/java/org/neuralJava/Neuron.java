package org.neuralJava;

import java.util.Random;

public class Neuron {

  private Random random = new Random(1);

  private double[] weights;
  private double bias;
  private ActivationFunction activationFunction;

  // These are used in the backpropogation
  private double lastInput[];
  private double lastOutput;

  public Neuron(int inputCount, ActivationFunction activationFunction) {
    this.activationFunction = activationFunction;
    this.weights = new double[inputCount];
    this.bias = (random.nextDouble() * 2 - 1) * Math.sqrt(1.0 / inputCount);

    for (int i = 0; i < weights.length; i++) {
      weights[i] = (random.nextDouble() * 2 - 1) * Math.sqrt(1.0 / inputCount);
    }
  }

  /*
   * Returns the value of the neuron with the input of the ouputs of the previous
   * neurons
   */
  public double feedForward(double[] inputs) {
    lastInput = inputs; // Store for backprop
    double sum = bias;

    for (int i = 0; i < inputs.length; i++) {
      sum += inputs[i] * weights[i];
    }

    lastOutput = activationFunction.activate(sum);
    return lastOutput;
  }

  // Slightly trains the neuron by updating it's weights and bias
  public double[] backpropagate(double error, double learningRate) {
    double delta = error * activationFunction.derivative(lastOutput);
    double[] weightGradients = new double[weights.length];

    // Update weights
    for (int i = 0; i < weights.length; i++) {
      weightGradients[i] = delta * lastInput[i];
      weights[i] -= learningRate * weightGradients[i];
    }

    // Update bias
    bias -= learningRate * delta;

    return weightGradients;
  }

  // Returns an array of weights that the neuron uses
  public double[] getWeights() {
    return this.weights;
  }
}
