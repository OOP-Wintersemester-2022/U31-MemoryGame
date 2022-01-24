import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Board  {
    private ArrayList<Card> cards;
    private Score score;
    private int width;
    private int height;

    public Board(int numRows, int numCardsInRow, int app_width, int app_height) {
        score = new Score();
        width = app_width;
        height = app_height;

        File[] files = retrieveAssets();
        ArrayList<Integer> evaluationNumbers = randomizeBoard(files.length);

        setupCards(numRows, numCardsInRow);
        assignValuesToCards(files, evaluationNumbers);
    }

    private void assignValuesToCards(File[] files, ArrayList<Integer> evaluationNumbers) {
        for (int i = 0; i < cards.size() && cards.size() == evaluationNumbers.size(); i++) {
            cards.get(i).setEvaluationNumber(evaluationNumbers.get(i));
            cards.get(i).setImage(files[evaluationNumbers.get(i)].getAbsolutePath());
        }
    }

    private ArrayList<Integer> randomizeBoard(int numFiles) {
        ArrayList<Integer> evaluationNumbers = new ArrayList<Integer>();

        Random random = new Random();

        while (evaluationNumbers.size() < Config.CARDS_PER_ROW * Config.NUM_CARD_ROWS) {
            int num = random.nextInt(numFiles);

            if (evaluationNumbers.stream().filter(number -> num == number).count() < 2) {
                evaluationNumbers.add(num);
            }
        }

        return evaluationNumbers;
    }

    private File[] retrieveAssets() {
        File[] files = new File(Config.BOARD_ASSET_PATH).listFiles();

        int numFiles = files.length;

        if (numFiles != Config.CARDS_PER_ROW * Config.NUM_CARD_ROWS / 2) {
            System.exit(0);
        }

        return files;
    }

    private void setupCards(int numRows, int numCardsInRow) {
        cards = new ArrayList<Card>();

        for (int i = 0; i < numRows; i++) {
            int y = Config.BOARD_MARGIN_TO_CARDS + i * (Config.CARD_HEIGHT + Config.BOARD_MARGIN_BETWEEN_CARDS);

            for (int k = 0; k < numCardsInRow; k++) {
                int x = Config.BOARD_MARGIN_TO_CARDS + k * (Config.CARD_WIDTH + Config.BOARD_MARGIN_BETWEEN_CARDS);

                cards.add(new Card(x, y, Config.CARD_WIDTH, Config.CARD_HEIGHT));
            }
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public boolean evaluateRevealedCards(ArrayList<CardView> cardsToEvaluate) {
        score.incrementNumEvaluations();
        return cardsToEvaluate.get(0).getCard().getEvaluationNumber() == cardsToEvaluate.get(1).getCard().getEvaluationNumber();
    }

    public boolean isGameOver(ArrayList<CardView> cardViews) {
        for(CardView view: cardViews) {
            if(view.isClickable()) {
                return false;
            }
        }

        return true;
    }

    public Score getScore() {
        return score;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
