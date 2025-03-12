package org.neuralJava;

import org.neuralJava.Utils.*;
import java.util.ArrayList;
import java.util.Random;

public class NeuralNetwork {

  private Layer[] layers;
  private double learningRate;

  private double lastLoss = Double.MAX_VALUE;

  public NeuralNetwork(int[] layerSizes, ActivationFunction[] activations, double learningRate) {
    layers = new Layer[layerSizes.length - 1]; // -1 because inputs dont count
    this.learningRate = learningRate;

    for (int i = 0; i < layers.length; i++) {
      layers[i] = new Layer(layerSizes[i + 1], layerSizes[i], activations[i]);
    }
  }

  public double predict(Point input) {
    double[] output = { input.x, input.y }; // If no neurons exist, return the input

    for (Layer layer : layers) {
      output = layer.feedForward(output);
    }

    return output[0];
  }

  public void train(ArrayList<Pair<Point, Boolean>> trainingData, int maxEpochs) {
    for (int epoch = 0; epoch < maxEpochs; epoch++) {
      double totalLoss = 0;

      for (Pair<Point, Boolean> data : trainingData) {
        double expectedOutput = data.second ? 1. : 0.;
        double actualOutput = predict(data.first);

        double error = 2 * (actualOutput - expectedOutput);
        totalLoss += Math.pow(expectedOutput - actualOutput, 2);

        double[] errors = { error };

        for (int i = layers.length - 1; i >= 0; i--) {
          errors = layers[i].backpropagate(errors, learningRate);
        }
      }

      double averageLoss = totalLoss / trainingData.size();

      if (averageLoss > lastLoss) {
        return;
      }

      lastLoss = averageLoss;

      if (epoch % 10 == 0) {

        // Prints out log message
        System.out
            .println(String.format("Epoch: %-5d  Average Error: %.5f", epoch, averageLoss));
      }
    }
  }

  // Returns the accuracy of the network.
  // The network successfully classifies a point of true if the ouput is > 0.5
  public double test(ArrayList<Pair<Point, Boolean>> testingData) {
    int amountCorrect = 0;

    for (Pair<Point, Boolean> data : testingData) {
      double output = predict(data.first);

      if ((data.second && output > 0.5) || (!data.second && output <= 0.5)) {
        amountCorrect++;
      }
    }

    // Get the amount correct out of the total test size
    return (double) amountCorrect / testingData.size();
  }
}
