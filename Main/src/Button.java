import processing.core.PApplet;

public class Button {
    private int x;
    private int y;
    private String text;
    private PApplet app;

    public Button (PApplet p,int x, int y, String text){
        this.x = x;
        this.y = y;
        this.text = text;
        this.app = p;
    }

    public void draw(){
        if (text.equals("exit") || text.equals("login") || text.equals("next") || text.equals("back")){
            app.fill(0,0,0);
            app.rect(x,y,100,50);
            app.fill(255,255,255);
            app.textSize(24);
            app.text(text,x+20,y+32);
        }else{
            app.fill(0,0,0);
            app.rect(x,y,100,100);
            app.fill(255,255,255);
            app.textSize(24);
            app.text(text,x+18,y+55);
        }
        
    }

    public boolean isClicked(int mouseX, int mouseY){
        if (text.equals("exit") || text.equals("login") || text.equals("next") || text.equals("back")){
            float d = app.dist(mouseX,mouseY,x+45,y);
            return d<=60;
        }
        else{
            float d = app.dist(mouseX,mouseY,x+45,y);
            return d<=73;
        }
    }



}
