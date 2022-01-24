import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Board  {

    private ArrayList<Card> cards;
    private Score score;
    private File[] files;

    public Board() {
        score = new Score();
        retrieveAssets();
        setupCards();
    }

    private void retrieveAssets() {
        files = new File(Config.BOARD_ASSET_PATH).listFiles();

        // Ist Anzahl der Files passend zur Anzahl der Karten?
        int numFiles = files.length;
        if (numFiles != Config.CARDS_PER_ROW * Config.NUM_CARD_ROWS / 2) {
            System.exit(0);
        }
    }

    private void setupCards() {
        cards = new ArrayList<>();
        for(File file : files){
            cards.add(new Card(file));
            cards.add(new Card(file));
        }
        shuffle();
    }

    /**
     * Randomisiert die Reihenfolge der Karten in der ArrayList,
     * damit diese später zufällig auf dem Spielbrett verteilt werden.
     */
    private void shuffle() {
        // Alternativ: Collections.shuffle(cards);
        ArrayList<Card> result = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < Config.CARDS_PER_ROW*Config.NUM_CARD_ROWS; i++){
            int randomIndex = random.nextInt(cards.size());
            Card randomCard = cards.get(randomIndex);
            result.add(randomCard);
            cards.remove(randomCard);
        }
        cards = result;
    }

    public void increaseScore(){
        score.incrementNumEvaluations();
    }

    public boolean isPair(Card card1, Card card2){
        if(card1.equals(card2)){
            return true;
        }
        return false;
    }

    public void solvePair(Card firstSolvedCard, Card secondSolvedCard){
        firstSolvedCard.setSolved();
        secondSolvedCard.setSolved();
    }

    public boolean isGameOver() {
        for(Card card: cards) {
            if(!card.isSolved()){
                return false;
            }
        }
        return true;
    }

    public Score getScore() {
        return score;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
