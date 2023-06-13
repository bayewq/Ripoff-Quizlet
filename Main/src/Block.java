import processing.core.PApplet;

public class Block extends Term{
    private int x;
    private int y;
    private PApplet app;
    private int speed;

    public Block(PApplet p, String name, int x, int y, int speed){
        super(name);
        this.app = p;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public Block(PApplet p, String name, int x, int y){
        super(name);
        this.app = p;
        this.x = x;
        this.y = y;
        this.speed = 2;
    }
    
    public void setX(int nx){
        x = nx;
    }

    public void setY(int ny){
        y = ny;
    }
    public int getY(){
        return y;
    }

    public void setSpeed(int nspeed){
        speed = nspeed;
    }

    public void move(int nx, int ny){
        x += nx*speed;
        y += ny*speed; 
    }

    public void draw(){
        app.fill(0,0,0);
        app.rect(x,y,100,50);
        app.fill(255,255,255);
        app.textSize(24);
        app.text(super.name,x+30,y+30);
    }

    
}