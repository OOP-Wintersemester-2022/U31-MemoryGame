import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.graphics.Rectangle;

public class CardView {
    private Card card;

    private Image imageView;
    private Rectangle rectangleView;

    private boolean isClickedToggle;
    private boolean isClickable;

    public CardView(Card card) {
        this.card = card;

        isClickedToggle = false;
        isClickable = true;

        imageView = new Image(card.getX(), card.getY(), card.getImagePath());
        imageView.setWidth(card.getWidth());
        imageView.setHeight(card.getHeight());

        rectangleView = new Rectangle(card.getX(), card.getY(), card.getWidth(), card.getHeight(), Config.CARD_BACK_COLOR);
    }

    public void draw() {
        if(isClickable) {
            show();
        } else {
            hide();
        }
    }

    private void show() {
        if(isClickedToggle) {
            imageView.draw();
        } else {
            rectangleView.draw();
        }
    }

    public void hide() {
        rectangleView.setColor(Config.APP_BACKGROUND_COLOR);
    }

    public boolean hitTest(int x, int y) {
        return this.imageView.hitTest(x, y);
    }

    public void onClick() {
        isClickedToggle = !isClickedToggle;
    }

    public boolean isToggled() {
        return isClickedToggle;
    }

    public void setToggle(boolean isToggled) {
        isClickedToggle = isToggled;
    }

    public void setClickable(boolean isClickable) {
        this.isClickable = isClickable;
    }

    public boolean isClickable() {
        return isClickable;
    }

    public Card getCard() {
        return card;
    }
}
