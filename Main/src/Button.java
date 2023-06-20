import processing.core.PApplet;

/**
 * The Button class represents a button object in a graphical application.
 * It has properties such as position and text.
 */
public class Button {
    private int x;          // X-coordinate of the button
    private int y;          // Y-coordinate of the button
    private String text;    // Text displayed on the button
    private PApplet app;    // Reference to the PApplet instance

    /**
     * Constructs a Button object with the given parameters.
     *
     * @param p     The PApplet instance representing the application.
     * @param x     The X-coordinate of the button.
     * @param y     The Y-coordinate of the button.
     * @param text  The text displayed on the button.
     */
    public Button(PApplet p, int x, int y, String text) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.text = text;
    }

    /**
     * Constructs a Button object with the given parameters.
     * The text is set to an empty string.
     *
     * @param p     The PApplet instance representing the application.
     * @param x     The X-coordinate of the button.
     * @param y     The Y-coordinate of the button.
     */
    public Button(PApplet p, int x, int y) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.text = "";
    }

    /**
     * Draws the button on the screen.
     * The appearance of the button depends on its text value.
     */
    public void draw() {
        
        // draws differently based on what type of button it is 
        if (text.equals("exit") || text.equals("login") || text.equals("next") || text.equals("back")) {
            app.fill(255, 255, 255);
            app.rect(x, y, 100, 50);
            app.fill(0, 0, 0);
            app.textSize(24);
            app.text(text, x + 20, y + 32);
        } 
        // draws nothing if its a button with no text
        else if (text.equals("")) {

        } else {
            app.fill(255, 255, 255);
            app.rect(x, y, 100, 100);
            app.fill(0, 0, 0);
            app.textSize(24);
            app.text(text, x + 18, y + 55);
        }
    }

    /**
     * Checks if the button is clicked based on the provided mouse coordinates.
     *
     * @param mouseX The X-coordinate of the mouse.
     * @param mouseY The Y-coordinate of the mouse.
     * @return true if the button is clicked, false otherwise.
     */
    public boolean isClicked(int mouseX, int mouseY) {
        
        // checks what text it is and calculates distance differently 
        if (text.equals("exit") || text.equals("login") || text.equals("next") || text.equals("back")) {
            float d = app.dist(mouseX, mouseY, x + 45, y + 50);
            return d <= 60;
        } else if (text.equals("")) {
            float d = app.dist(mouseX, mouseY, x + 250, y);
            return d < 295;
        } else {
            float d = app.dist(mouseX, mouseY, x + 45, y);
            return d <= 73;
        }
    }
}
