import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.events.GraphicsAppMouseListener;
import de.ur.mi.oop.events.MousePressedEvent;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

public class MemoryApp extends GraphicsApp implements GraphicsAppMouseListener {

    private Board board;
    private BoardView boardView;

    @Override
    public void initialize() {
        setupCanvas();
        setupBoard();
    }

    private void setupBoard() {
        board = new Board();
        boardView = new BoardView(board);
    }

    @Override
    public void draw() {
        drawBackground(Config.APP_BACKGROUND_COLOR);
        boardView.draw();
    }

    private void setupCanvas() {
        setCanvasSize(Config.APP_WIDTH, Config.APP_HEIGHT);
        drawBackground(Config.APP_BACKGROUND_COLOR);
    }


    @Override
    public void onMousePressed(MousePressedEvent event) {
        boardView.onClick(event.getXPos(), event.getYPos());
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }
}