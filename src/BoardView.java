import java.util.ArrayList;

public class BoardView {
    private Board board;
    private CardView[][] cardViews;
    private ScoreView scoreView;

    private int numClickedCards;

    public BoardView(Board board) {
        this.board = board;
        numClickedCards = 0;
        initViews();
    }

    /**
     * Der Score und das Gitter an Memory-Karten wird erstellt.
     */
    private void initViews(){
        scoreView = new ScoreView(board.getScore());
        cardViews = new CardView[Config.NUM_CARD_ROWS][Config.CARDS_PER_ROW];

        int numCardsCreated = 0;
        for (int i = 0; i < cardViews.length; i++) {
            for (int j = 0; j < cardViews[i].length; j++) {
                Card currentCard = board.getCards().get(numCardsCreated);
                int cardX = Config.BOARD_MARGIN_TO_CARDS + (i * (Config.CARD_WIDTH + Config.BOARD_MARGIN_BETWEEN_CARDS));
                int cardY = Config.BOARD_MARGIN_TO_CARDS + (j * (Config.CARD_HEIGHT + Config.BOARD_MARGIN_BETWEEN_CARDS));
                cardViews[i][j] = new CardView(currentCard, cardX, cardY);
                numCardsCreated ++;
            }
        }
    }

    /**
     * Solange das Spiel noch nicht vorbei ist, werden alle Karten gezeichnet.
     * Wenn das Spiel vorbei ist, wird der erzielte Score angezeigt.
     */
    public void draw() {
        for (CardView[] row : cardViews) {
            for (CardView cardView : row) {
                cardView.draw();
            }
        }
        if (board.isGameOver()) {
            scoreView.setScore();
            scoreView.draw();
        }
    }

    /**
     * Registriert einen Klick und handelt je nach Anzahl vorangegangener Klicks:
     * Sind bereits zwei Karten aufgedeckt worden, werden alle Karten wieder verdeckt.
     * Ansonsten wird der Klick auf eine Karte verarbeitet.
     * @param x xPos des Klicks
     * @param y yPos des Klicks
     */
    public void onClick(int x, int y) {
        if(numClickedCards == 2) {
            hideRevealedCards();
        } else {
            handleCardClick(x, y);
        }
    }

    /**
     * Alle Karten werden wieder umgedreht.
     */
    private void hideRevealedCards() {
        numClickedCards = 0;
        for (CardView[] row : cardViews) {
            for (CardView cardView : row) {
                cardView.setRevealed(false);
            }
        }
    }

    /**
     * Die geklickte Karte wird umgedreht.
     * War bereits eine Karte umgedreht, wird überprüft, ob ein Paar gefunden wurde.
     * Nach jedem Versuch, wird das Board beauftragt, den Score hochzuzählen.
     * @param x xPos des Klicks
     * @param y yPos des Klicks
     */
    private void handleCardClick(int x, int y) {
        for (CardView[] row : cardViews) {
            for (CardView cardView : row) {
                if (cardView.hitTest(x, y)) {
                    cardView.flip();
                    numClickedCards++;
                }
            }
        }
        if(numClickedCards == 2) {
            performPairCheck();
            board.increaseScore();
        }
    }

    /**
     * Wurde ein Paar gefunden, werden die zugehörigen Karten aus dem Spiel genommen.
     * Die Anzahl der geklickten Karten wird zurückgesetzt.
     */
    private void performPairCheck() {
        ArrayList<CardView> cardsToCheck = getRevealedCards();

        if(cardsToCheck.size() == 2){
            Card firstRevealedCard = cardsToCheck.get(0).getCard();
            Card secondRevealedCard = cardsToCheck.get(1).getCard();
            if(board.isPair(firstRevealedCard, secondRevealedCard)){
                board.solvePair(firstRevealedCard, secondRevealedCard);
                cardsToCheck.get(0).solveCard();
                cardsToCheck.get(1).solveCard();
                numClickedCards = 0;
            }
        }

    }

    /**
     * Findet alle Karten, die gerade aufgedeckt am Spielbrett liegen.
     * @return ArrayList of revealed CardViews
     */
    private ArrayList<CardView> getRevealedCards() {
        ArrayList<CardView> revealedCards = new ArrayList<>();
        for (CardView[] row : cardViews) {
            for (CardView cardView : row) {
                if (cardView.isRevealed()) {
                    revealedCards.add(cardView);
                }
            }
        }
        return revealedCards;
    }
}