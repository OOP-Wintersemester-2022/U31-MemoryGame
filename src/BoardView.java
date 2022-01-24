import java.util.ArrayList;

public class BoardView {
    private Board board;
    private ArrayList<CardView> cardViews;
    private ScoreView scoreView;

    private int numClickedCards;

    public BoardView(Board board) {
        this.cardViews = new ArrayList<CardView>();
        this.board = board;
        this.scoreView = new ScoreView(board.getScore(), board.getWidth(), board.getHeight());
        this.numClickedCards = 0;

        for (Card card : board.getCards()) {
            cardViews.add(new CardView(card));
        }
    }

    public void draw() {
        for (CardView view : cardViews) {
            view.draw();
        }

        if (board.isGameOver(cardViews)) {
            scoreView.setScore(board.getScore().getNumEvaluations());
            scoreView.draw();
        }
    }

    public void onClick(int x, int y) {
        if(numClickedCards == 2) {
            hideRevealedCards();
        } else {
            handleCardClick(x, y);

            if(numClickedCards == 2) {
                evaluateRevealedCards();
            }
        }
    }

    private void hideRevealedCards() {
        numClickedCards = 0;

        for (CardView view : cardViews) {
            view.setToggle(false);
        }
    }

    private void handleCardClick(int x, int y) {
        for (CardView view : cardViews) {
            if (view.hitTest(x, y)) {
                if(!view.isToggled()) {
                    view.onClick();
                    numClickedCards++;
                }
            }
        }
    }

    private void evaluateRevealedCards() {
        ArrayList<CardView> cardsToEvaluate = new ArrayList<CardView>();

        for (CardView view : cardViews) {
            if(view.isToggled()) {
                cardsToEvaluate.add(view);
            }
        }

        if(board.evaluateRevealedCards(cardsToEvaluate)) {
            cardsToEvaluate.get(0).setClickable(false);
            cardsToEvaluate.get(1).setClickable(false);
            numClickedCards = 0;

            for (CardView view : cardViews) {
                view.setToggle(false);
            }
        }
    }
}