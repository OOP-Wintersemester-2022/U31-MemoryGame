public class Score {
    private int numEvaluations;

    public Score() {
        numEvaluations = 0;
    }

    public int getNumEvaluations() {
        return numEvaluations;
    }

    public void incrementNumEvaluations() {
        numEvaluations++;
    }
}