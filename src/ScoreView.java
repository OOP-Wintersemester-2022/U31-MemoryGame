import de.ur.mi.oop.graphics.Label;

public class ScoreView {
    private Score score;
    private Label label;
    private int verticalCenter;
    private int horizontalCoordinate;

    public ScoreView(Score score, int width, int height) {
        this.score = score;
        verticalCenter = width / 2;
        horizontalCoordinate = width / 16;

        this.label = new Label(0, verticalCenter, "", Config.SCORE_FONT_COLOR);
        this.label.setFontSize(Config.SCORE_FONTSIZE);
    }

    public void draw() {
        label.draw();
    }

    public void setScore(int numEvaluations) {
        this.label.setText(Config.SCORE_MESSAGE_PRE + score.getNumEvaluations() + Config.SCORE_MESSAGE_POST);
        this.label.setXPos(horizontalCoordinate);
        this.label.setYPos(verticalCenter - label.getHeight() / 2);
    }
}