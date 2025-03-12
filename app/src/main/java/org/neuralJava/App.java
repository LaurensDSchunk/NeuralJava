package org.neuralJava;

import org.neuralJava.Utils.*;
import java.util.Random;
import java.lang.reflect.Array;
import java.util.ArrayList;

/*
 *
 * This program will classify points between the min and max values according to the classifier function.
 * It will train the network, then test it.
 *
 * -----
 *
 * Network Structure. Each neuron is connected to all in previous layer.
 *
 * [in] [ReLU] [Sigmoid]
 * [in] [ReLU]
 *      [ReLU]
 *
 */

public class App {
  static Random random = new Random(2);

  final static int tests = 100;

  final static double minX = 0.0;
  final static double maxX = 1.0;
  final static double minY = 0.0;
  final static double maxY = 1.0;

  public static void main(String[] args) {
    double totalImprovement = 0;

    for (int randomSeed = 0; randomSeed < tests; randomSeed++) {

      random = new Random(randomSeed);

      System.out.println("** Test " + (randomSeed + 1) + ": **");

      int[] layerSizes = { 2, 3, 1 }; // 2 inputs, 2 hidden layers (4 and 3 neurons), 1 output
      ActivationFunction[] activations = { new None(), new Sigmoid() };

      NeuralNetwork network = new NeuralNetwork(layerSizes, activations, 0.001);

      // Create the data sets
      ArrayList<Pair<Point, Boolean>> trainingData = createLabeledData(500);
      ArrayList<Pair<Point, Boolean>> testingData = createLabeledData(2000);

      // Test untrained accuracy
      double beforeAccuracy = network.test(testingData);

      // Train the network
      network.train(trainingData, 100);

      // Test the network
      double afterAccuracy = network.test(testingData);

      System.out.println("\nStarting accuracy: " + beforeAccuracy * 100.0 + "%");
      System.out.println("Ending accuracy: " + afterAccuracy * 100.0 + "%\n\n");

      totalImprovement += (afterAccuracy - beforeAccuracy);
    }

    // BEAUTIFUL FORMATTING
    System.out.println("------------------\n");
    System.out.println(String.format("Average improvement: %.5f", (totalImprovement / tests) * 100.0) + "%");
  }

  public static boolean classify(Point point) {
    // Points in the following regions are true:
    // 0.5 < x < 0.7
    // 0.5 < y < 0.8
    if (point.x > 0.5 && point.y > 0.5 && point.y < 0.8 && point.x < 0.7)
      return true;
    return false;
  }

  public static Point randomPoint() {
    return new Point(random.nextDouble() * (maxX - minX) + minX, random.nextDouble() * (maxY - minY) + minY);
  }

  public static void labeledDataData(ArrayList<Pair<Point, Boolean>> data) {
    int truePoints = 0;
    int falsePoints = 0;

    for (Pair<Point, Boolean> dataPair : data) {
      if (dataPair.second) {
        truePoints++;
      } else {
        falsePoints++;
      }
    }

    System.out.println("True points: " + truePoints + "   False points: " + falsePoints);
  }

  // Creates a set of pairs of points and their associated classified value.
  // HALF OF THE DATA WILL BE TRUE, THE OTHER HALF WILL BE FALSE
  public static ArrayList<Pair<Point, Boolean>> createLabeledData(int amount) {
    ArrayList<Pair<Point, Boolean>> output = new ArrayList<Pair<Point, Boolean>>();

    int half = amount / 2;

    // Yes, this is inneficient but I actually dont care enough to change it
    while (output.size() < half) {
      Point point = randomPoint();
      if (classify(point)) {
        output.add(new Pair<>(point, true));
      }
    }

    while (output.size() < amount) {
      Point point = randomPoint();
      if (!classify(point)) {
        output.add(new Pair<>(point, false));
      }
    }

    return output;
  }
}
