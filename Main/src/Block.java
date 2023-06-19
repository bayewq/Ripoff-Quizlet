import processing.core.PApplet;

/**
 * The Block class represents a block object in a graphical application.
 * It has properties such as position, speed, and name.
 */
public class Block {
    private int x;          // X-coordinate of the block
    private int y;          // Y-coordinate of the block
    private PApplet app;    // Reference to the PApplet instance
    private double speed;   // Speed at which the block moves
    private String name;    // Name of the block
    private String name2;   // Additional name of the block

    /**
     * Constructs a Block object with the given parameters.
     *
     * @param p     The PApplet instance representing the application.
     * @param name  The name of the block.
     * @param name2 The additional name of the block.
     * @param x     The initial X-coordinate of the block.
     * @param y     The initial Y-coordinate of the block.
     * @param speed The speed at which the block moves.
     */
    public Block(PApplet p, String name, String name2, int x, int y, double speed) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
        this.name2 = name2;
        this.speed = speed;
    }

    /**
     * Constructs a Block object with the given parameters.
     * The speed is set to the default value of 1.
     *
     * @param p     The PApplet instance representing the application.
     * @param name  The name of the block.
     * @param name2 The additional name of the block.
     * @param x     The initial X-coordinate of the block.
     * @param y     The initial Y-coordinate of the block.
     */
    public Block(PApplet p, String name, String name2, int x, int y) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
        this.name2 = name2;
        this.speed = 1;
    }

    /**
     * Returns the name of the block.
     *
     * @return The name of the block.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the PApplet instance associated with the block.
     *
     * @return The PApplet instance.
     */
    public PApplet getApp() {
        return app;
    }

    /**
     * Returns the additional name of the block.
     *
     * @return The additional name of the block.
     */
    public String getName2() {
        return name2;
    }

    /**
     * Sets the X-coordinate of the block.
     *
     * @param nx The new X-coordinate value.
     */
    public void setX(int nx) {
        x = nx;
    }

    /**
     * Returns the X-coordinate of the block.
     *
     * @return The X-coordinate of the block.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the Y-coordinate of the block.
     *
     * @param ny The new Y-coordinate value.
     */
    public void setY(int ny) {
        y = ny;
    }

    /**
     * Returns the Y-coordinate of the block.
     *
     * @return The Y-coordinate of the block.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the speed of the block.
     *
     * @param nspeed The new speed value.
     */
    public void setSpeed(double nspeed) {
        speed = nspeed;
    }

    /**
     * Moves the block by the given amount in the X and Y directions.
     *
     * @param nx The amount to move in the X direction.
     * @param ny The amount to move in the Y direction.
     */
    public void move(int nx, int ny) {
        x += nx * speed;
        y += ny * speed;
    }

    /**
     * Draws the block on the screen.
     * The block is represented by a filled rectangle with text inside it.
     */
    public void draw() {
        app.fill(0, 0, 0);
        app.rect(x, y, 150, 50);
        app.fill(255, 255, 255);
        app.textSize(24);
        app.text(name2, x + 30, y + 30);
    }
}
