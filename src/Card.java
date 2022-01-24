import java.io.File;

public class Card {
    private File imageFile;
    private boolean solved;

    public Card(File imageFile) {
        this.imageFile = imageFile;
        solved = false;
    }

    public void setSolved(){
        solved = true;
    }

    public boolean isSolved(){
        return solved;
    }

    public File getImageFile(){
        return imageFile;
    }

    /**
     * Zwei Karten sind dann gleich, wenn ihr Image das gleiche ist.
     * @param cardToCompare
     * @return cards have equal images
     */
    public boolean equals(Card cardToCompare) {
        if(imageFile.equals(cardToCompare.getImageFile())){
            return true;
        }
        return false;
    }
}