package org.neuralJava;

/*
 *
 * This is the class for a layer in the neural network.
 * Each layer has one type of activation function.
 * Each layer can have > 0 neurons
 *
 */

public class Layer {
  private Neuron[] neurons;

  // Creates and fills up the layer with neurons
  public Layer(int neuronCount, int inputSize, ActivationFunction activationFunction) {
    neurons = new Neuron[neuronCount];

    for (int i = 0; i < neurons.length; i++) {
      neurons[i] = new Neuron(inputSize, activationFunction);
    }
  }

  // Calls feed forward for each neuron in the layer
  public double[] feedForward(double[] inputs) {
    double[] outputs = new double[neurons.length];

    for (int i = 0; i < neurons.length; i++) {
      outputs[i] = neurons[i].feedForward(inputs);
    }

    return outputs;
  }

  public double[] backpropagate(double[] errors, double learningRate) {
    double[] nextLayerErrors = new double[neurons[0].getWeights().length];

    for (int i = 0; i < neurons.length; i++) {
      double[] weightGradients = neurons[i].backpropagate(errors[i], learningRate);

      // Accumulate gradients for the previous layer
      for (int j = 0; j < weightGradients.length; j++) {
        nextLayerErrors[j] += weightGradients[j] / neurons.length;
      }
    }

    return nextLayerErrors;
  }
}
