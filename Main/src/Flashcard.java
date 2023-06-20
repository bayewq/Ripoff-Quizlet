import processing.core.PApplet;

/**
 * The Flashcard class represents a flashcard object in a graphical application.
 * It is a subclass of the Block class and adds functionality specific to flashcards.
 */
public class Flashcard extends Block {
    
    // init variables 
    private String curName = super.getName();  // Current name being displayed on the flashcard
    private Button butt;                        // Button associated with the flashcard

    /**
     * Constructs a Flashcard object with the given parameters.
     *
     * @param p     The PApplet instance representing the application.
     * @param name  The name of the flashcard.
     * @param name2 The additional name of the flashcard.
     * @param x     The initial X-coordinate of the flashcard.
     * @param y     The initial Y-coordinate of the flashcard.
     * @param butt  The button associated with the flashcard.
     */
    public Flashcard(PApplet p, String name, String name2, int x, int y, Button butt) {
        super(p, name, name2, x, y);
        this.butt = butt;
    }

    /**
     * Draws the flashcard on the screen.
     * The flashcard is represented by a filled rectangle with text inside it.
     */
    public void draw() {
        super.getApp().fill(255, 255, 255);
        super.getApp().rect(super.getX(), super.getY(), 500, 300);
        super.getApp().fill(0, 0, 0);
        super.getApp().textSize(48);
        super.getApp().text(curName, super.getX() + 150, super.getY() + 150);
    }

    /**
     * Checks if the flashcard or its associated button is clicked based on the provided mouse coordinates.
     *
     * @param mouseX The X-coordinate of the mouse.
     * @param mouseY The Y-coordinate of the mouse.
     * @return true if the flashcard or button is clicked, false otherwise.
     */
    public boolean isClicked(int mouseX, int mouseY) {
        if (butt.isClicked(mouseX, mouseY)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Flips the flashcard to display the alternative name if the current name is being displayed,
     * or vice versa.
     */
    public void flip() {
        if (curName.equals(super.getName())) {
            curName = super.getName2();
        } else {
            curName = super.getName();
        }
    }

    /**
     * Resets the flashcard to display its original name.
     */
    public void reset() {
        curName = super.getName();
    }
}
