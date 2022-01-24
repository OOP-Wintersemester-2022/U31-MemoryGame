import de.ur.mi.oop.graphics.Label;

public class ScoreView {
    private Score score;
    private Label label;
    private int verticalCenter;
    private int horizontalCenter;

    public ScoreView(Score score) {
        this.score = score;
        verticalCenter = Config.APP_HEIGHT / 2;
        horizontalCenter = Config.APP_WIDTH / 16;

        label = new Label(0, verticalCenter, "", Config.SCORE_FONT_COLOR);
        label.setFontSize(Config.SCORE_FONTSIZE);
    }

    public void draw() {
        label.draw();
    }

    public void setScore() {
        label.setText(Config.SCORE_MESSAGE_PRE + score.getNumEvaluations() + Config.SCORE_MESSAGE_POST);
        label.setYPos(verticalCenter - label.getHeight() / 2);
        label.setXPos(horizontalCenter);
    }
}