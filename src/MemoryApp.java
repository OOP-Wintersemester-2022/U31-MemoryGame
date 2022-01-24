import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.events.GraphicsAppMouseListener;
import de.ur.mi.oop.events.MousePressedEvent;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

public class MemoryApp extends GraphicsApp implements GraphicsAppMouseListener {
    private Board board;
    private BoardView boardView;

    private int app_width;
    private int app_height;

    @Override
    public void initialize() {
        setupCanvas();
        initBoard();
        initComponents();
    }

    @Override
    public void draw() {
        drawBackground(Config.APP_BACKGROUND_COLOR);
        boardView.draw();
    }

    private void setupCanvas() {
        app_width = Config.BOARD_MARGIN_TO_CARDS * 2 + Config.BOARD_MARGIN_BETWEEN_CARDS * (Config.CARDS_PER_ROW - 1) + Config.CARDS_PER_ROW * Config.CARD_WIDTH;
        app_height = Config.BOARD_MARGIN_TO_CARDS * 2 + Config.BOARD_MARGIN_BETWEEN_CARDS * (Config.NUM_CARD_ROWS - 1) + Config.NUM_CARD_ROWS * Config.CARD_HEIGHT;

        setCanvasSize(app_width, app_height);
        drawBackground(Config.APP_BACKGROUND_COLOR);
    }

    private void initBoard() {
        board = new Board(Config.NUM_CARD_ROWS, Config.CARDS_PER_ROW, app_width, app_height);
    }


    private void initComponents() {
        boardView = new BoardView(board);
    }

    @Override
    public void onMousePressed(MousePressedEvent event) {
        boardView.onClick(event.getXPos(), event.getYPos());
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }
}