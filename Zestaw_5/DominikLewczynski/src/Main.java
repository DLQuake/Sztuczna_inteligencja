import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Zadanie 1");
        Perceptron perceptron = new Perceptron(3);
        perceptron.setWeights(0.5, 0.5, 0.5);
        perceptron.setLearningFactor(0.5);
        Example[] examples = new Example[3];
        examples[0] = new Example(new double[]{1, 0, 0}, 1);
        examples[1] = new Example(new double[]{0, 1, 0}, 1);
        examples[2] = new Example(new double[]{0, 0, 1}, 1);
        for (int i = 0; i < 100; i++) {
            for (Example example : examples) {
                perceptron.learn(example);
            }
        }
        System.out.println(Arrays.toString(perceptron.getWeights()));

        System.out.println("\n");

        System.out.println("Zadanie 2");
        ClassificationTree.DataSet dataSet = new ClassificationTree.DataSet();
        dataSet.addData(new double[]{1, 0, 0, 0});
        dataSet.addData(new double[]{1, 0, 1, 0});
        dataSet.addData(new double[]{0, 1, 0, 0});
        dataSet.addData(new double[]{1, 1, 1, 1});
        dataSet.addData(new double[]{1, 1, 0, 1});

        List<Integer> attributes = new ArrayList<>();
        for (int i = 0; i < dataSet.getNumCols() - 1; i++) {
            attributes.add(i);
        }

        List<int[]> groups = new ArrayList<>();
        for (int i = 0; i < dataSet.getNumCols() - 1; i++) {
            int[] group = new int[dataSet.getNumRows()];
            for (int j = 0; j < dataSet.getNumRows(); j++) {
                group[j] = (int) dataSet.getData(j, i);
            }
            groups.add(group);
        }

        int[] classes = new int[dataSet.getNumRows()];
        for (int i = 0; i < dataSet.getNumRows(); i++) {
            classes[i] = (int) dataSet.getData(i, dataSet.getNumCols() - 1);
        }

        int[] groupsClasses = new int[dataSet.getNumRows()];
        for (int i = 0; i < dataSet.getNumRows(); i++) {
            groupsClasses[i] = (int) dataSet.getData(i, dataSet.getNumCols() - 1);
        }

        List<Integer> attributesList = new ArrayList<>();
        for (int i = 0; i < dataSet.getNumCols() - 1; i++) {
            attributesList.add(i);
        }

        System.out.println("Gini index: " + ClassificationTree.giniIndex(groups.get(0)));
        System.out.println("Gini index: " + ClassificationTree.giniIndex(groups.get(1)));
        System.out.println("Gini index: " + ClassificationTree.giniIndex(groups.get(2)));
    }
}

// do zadania 1
class Perceptron {
    private double[] weights;
    private double learningFactor;

    public Perceptron(int n) {
        weights = new double[n];
    }

    public void setWeights(double... weights) {
        this.weights = weights;
    }

    public void setLearningFactor(double learningFactor) {
        this.learningFactor = learningFactor;
    }

    public double[] getWeights() {
        return weights;
    }

    public void learn(Example example) {
        double[] inputs = example.getInputs();
        double output = example.getOutput();
        double[] deltaWeights = new double[weights.length];
        for (int i = 0; i < weights.length; i++) {
            deltaWeights[i] = learningFactor * (output - weights[i] * inputs[i]);
        }
        for (int i = 0; i < weights.length; i++) {
            weights[i] += deltaWeights[i];
        }
    }

}

class Example {
    private final double[] inputs;
    private final double output;

    public Example(double[] inputs, double output) {
        this.inputs = inputs;
        this.output = output;
    }

    public double[] getInputs() {
        return inputs;
    }

    public double getOutput() {
        return output;
    }

}

// do Zadania 2
class ClassificationTree {

    public static double giniIndex(int[] groups) {
        int total = 0;
        for (int group : groups) {
            total += group;
        }
        double gini = 1.0;
        for (int group : groups) {
            double prob = (double) group / total;
            gini -= prob * prob;
        }
        return gini;
    }

    public static class DataSet {
        private final List<double[]> data;
        private int numRows;
        private int numCols;

        public DataSet() {
            data = new ArrayList<>();
            numRows = 0;
            numCols = 0;
        }

        public void addData(double[] row) {
            data.add(row);
            numRows++;
            numCols = row.length;
        }

        public int getNumRows() {
            return numRows;
        }

        public int getNumCols() {
            return numCols;
        }

        public double getData(int row, int col) {
            return data.get(row)[col];
        }
    }

}