import processing.core.PApplet;

public class Block{
    private int x;
    private int y;
    private PApplet app;
    private double speed;
    private String name;
    private String name2;

    public Block(PApplet p, String name, String name2, int x, int y, double speed){
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
        this.name2 = name2;
        this.speed = speed;
    }

    public Block(PApplet p, String name, String name2, int x, int y){
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
        this.name2 = name2;
        this.speed = 1;
    }

    public String getName(){
        return name;
    }

    public PApplet getApp(){
        return app;
    }

    public String getName2(){
        return name2;
    }
    
    public void setX(int nx){
        x = nx;
    }

    public int getX(){
        return x;
    }

    public void setY(int ny){
        y = ny;
    }
    public int getY(){
        return y;
    }

    public void setSpeed(double nspeed){
        speed = nspeed;
    }

    public void move(int nx, int ny){
        x += nx*speed;
        y += ny*speed; 
    }

    public void draw(){
        app.fill(0,0,0);
        app.rect(x,y,150,50);
        app.fill(255,255,255);
        app.textSize(24);
        app.text(name2,x+30,y+30);
    }

    
}