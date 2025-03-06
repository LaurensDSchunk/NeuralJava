package org.neuralJava;

public class NeuralNetwork {

  private Layer[] layers;

  public NeuralNetwork(int[] layerSizes, ActivationFunction[] activations) {
    layers = new Layer[layerSizes.length - 1]; // -1 because inputs dont count

    for (int i = 0; i < layers.length; i++) {
      layers[i] = new Layer(layerSizes[i + 1], layerSizes[i], activations[i]);
    }
  }

  public double[] predict(double[] inputs) {
    double[] output = inputs; // If no neurons exist, return the input

    for (Layer layer : layers) {
      output = layer.feedForward(output);
    }

    return output;
  }
}
