import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Rectangle;

public class CardView {
    private Card card;

    private Image foreground;
    private Rectangle background;

    private boolean isVisible;
    private boolean isRevealed;

    public CardView(Card card, int x, int y) {
        this.card = card;

        isVisible = true;
        isRevealed = false;

        foreground = new Image(x, y, card.getImageFile().getPath());
        foreground.setWidth(Config.CARD_WIDTH);
        foreground.setHeight(Config.CARD_HEIGHT);

        background = new Rectangle(x, y, Config.CARD_WIDTH, Config.CARD_HEIGHT, Config.CARD_BACK_COLOR);
    }

    public void draw() {
        if(isVisible) {
            drawCard();
        }
    }

    private void drawCard(){
        if(isRevealed){
            foreground.draw();
        } else {
            background.draw();
        }
    }

    public boolean hitTest(int x, int y) {
        return this.foreground.hitTest(x, y);
    }

    public void flip() {
        isRevealed = !isRevealed;
    }

    /**
     * Ist ein Paar gefunden (solved), wird die Karte unsichtbar gemacht.
     */
    public void solveCard(){
        isVisible = false;
        isRevealed = false;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean isRevealed) {
        this.isRevealed = isRevealed;
    }

    public Card getCard() {
        return card;
    }
}
