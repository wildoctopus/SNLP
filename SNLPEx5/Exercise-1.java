
/**
 * Simple structure for storing a sequence of states and its probability.
 */
public static class StateSequence {
    /**
     * The sequence of states.
     */
    public final String[] states;
    /**
     * The logarithm of the probability of this sequence.
     */
    public final double logProbability;

    public StateSequence(String[] states, double logProbability) {
        this.states = states;
        this.logProbability = logProbability;
    }
}

/**
 * A class implementing the Viterbi algorithm based on an Hidden Markov Model
 * with the given states, observations, transition matrix and emission matrix.
 */
public static class ViterbiAlgorithm {
    
    // YOUR CODE HERE

    /**
     * Constructor.
     */
    public ViterbiAlgorithm(String[] states, String[] observationVocab, double[][] transitionMatrix,
            double[][] emissionMatrix) {
        // YOUR CODE HERE
    }

    /**
     * Returns the sequence of states which has the highest probability to create
     * the given sequence of observations.
     * 
     * @param observations
     *            a sequence of observations
     * @return the sequence of states
     */
    public StateSequence getStateSequence(String[] observations) {
        StateSequence sequence = null;
        // YOUR CODE HERE
        return sequence;
    }
}

// This line should make sure that compile errors are directly identified when executing this cell
// (the line itself does not produce any meaningful result)
new ViterbiAlgorithm(new String[0],new String[0],new double[2][2],new double[0][0]);
System.out.println("compiled");

%maven org.junit.jupiter:junit-jupiter-api:5.3.1
import org.junit.jupiter.api.Assertions;
import org.opentest4j.AssertionFailedError;

public static final double DELTA = 0.000001;

public static void checkViterbi(String[] states, double[][] transitionMatrix, String[] observationVocab,
        double[][] emissionMatrix, String[] observations, StateSequence expectedSequence) {
    try {
        ViterbiAlgorithm viterbi = new ViterbiAlgorithm(states, observationVocab, transitionMatrix, emissionMatrix);
        long time1 = System.currentTimeMillis();
        StateSequence sequence = viterbi.getStateSequence(observations);
        time1 = System.currentTimeMillis() - time1;
        System.out.println("Viterbi took " + time1 + "ms");
        Assertions.assertArrayEquals(expectedSequence.states, sequence.states);
        double diff = Math.abs(expectedSequence.logProbability - sequence.logProbability);
        Assertions.assertTrue(diff < DELTA, "The calculated probability (" + sequence.logProbability
                + ") does not match the expected probability (" + expectedSequence.logProbability + ").");
        System.out.println("Test passed");
    } catch (AssertionFailedError e) {
        throw e;
    } catch (Throwable e) {
        System.err.println("Your solution caused an unexpected error:");
        throw e;
    }
}

String observations[];
StateSequence expectedSequence;
String[] states;
String[] sequence;
double[][] transitionMatrix;
String[] observationVocab;
double[][] emissionMatrix;

System.out.println("---------- Ice cream example ----------");
states = new String[] { "HOT", "COLD" };
transitionMatrix = new double[][] { { 0, 0.8, 0.2, 0 }, { 0, 0.6, 0.3, 0.1 }, { 0, 0.4, 0.5, 0.1 },
        { 0, 0, 0, 0 } };
observationVocab = new String[] { "1", "2", "3" };
emissionMatrix = new double[][] { { 0.2, 0.4, 0.4 }, { 0.5, 0.4, 0.1 } };
observations = new String[] { "3", "1", "3" };
expectedSequence = new StateSequence(new String[] { "HOT", "HOT", "HOT" }, Math.log(0.0009216));
checkViterbi(states, transitionMatrix, observationVocab, emissionMatrix, observations, expectedSequence);

observations = new String[] { "3", "2", "1", "1" };
expectedSequence = new StateSequence(new String[] { "HOT", "HOT", "COLD", "COLD" }, Math.log(0.000288));
checkViterbi(states, transitionMatrix, observationVocab, emissionMatrix, observations, expectedSequence);

observations = new String[] { "1", "3", "3", "2", "3", "2", "1", "3", "1", "1", "1" };
expectedSequence = new StateSequence(
        new String[] { "HOT", "HOT", "HOT", "HOT", "HOT", "HOT", "HOT", "HOT", "COLD", "COLD", "COLD" },
        Math.log(3.439853568E-9));
checkViterbi(states, transitionMatrix, observationVocab, emissionMatrix, observations, expectedSequence);


System.out.println("---------- Ice cream example ----------");
observations = new String[1000];
Arrays.fill(observations, "3");
sequence = new String[1000];
Arrays.fill(sequence, "HOT");
expectedSequence = new StateSequence(sequence,
        Math.log(0.8) + (999 * Math.log(0.6)) + (1000 * Math.log(0.4) + Math.log(0.1)));
checkViterbi(states, transitionMatrix, observationVocab, emissionMatrix, observations, expectedSequence);

// Ignore this cell

// Ignore this cell
