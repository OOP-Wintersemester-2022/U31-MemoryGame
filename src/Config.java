import de.ur.mi.oop.colors.Color;

public class Config {
    // Card
    public static final Color CARD_BACK_COLOR = new Color(53,146,196);
    public static final int CARD_WIDTH = 150;
    public static final int CARD_HEIGHT = 150;

    // App
    public static final Color APP_BACKGROUND_COLOR = new Color(255, 255, 255, 255);

    // Board
    public static final int BOARD_MARGIN_TO_CARDS = 10;
    public static final int BOARD_MARGIN_BETWEEN_CARDS = 3;
    public static final String BOARD_ASSET_PATH =  "data/assets";

    public static final int CARDS_PER_ROW = 4;
    public static final int NUM_CARD_ROWS = 4;

    // Score
    public static final Color SCORE_FONT_COLOR = new Color(0,0,0);
    public static final int SCORE_FONTSIZE = 22;
    public static final String SCORE_MESSAGE_PRE = "You completed Memory with ";
    public static final String SCORE_MESSAGE_POST = " comparisons!";

}
