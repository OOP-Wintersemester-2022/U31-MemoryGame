import java.io.File;

public class Card {
    private int xPos;
    private int yPos;
    private int width;
    private int height;

    private String imagePath;
    private int evaluationNumber;

    public Card(int x, int y, int width, int height) {
        this.xPos = x;
        this.yPos = y;
        this.width = width;
        this.height = height;

        this.evaluationNumber = -1;
        this.imagePath = "";
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getEvaluationNumber() {
        return evaluationNumber;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setEvaluationNumber(int evaluationNumber) {
        this.evaluationNumber = evaluationNumber;
    }

    public void setImage(String imagePath) {
        this.imagePath = imagePath;
    }
}