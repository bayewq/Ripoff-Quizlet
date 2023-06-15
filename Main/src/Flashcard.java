import java.util.jar.Attributes.Name;

import processing.core.PApplet;

public class Flashcard extends Block{
    String curName = super.getName();

    public Flashcard(PApplet p, String name, String name2, int x, int y){
        super(p,name,name2,x,y);
    }

    public void draw(){
        super.getApp().fill(255,255,255);
        super.getApp().rect(super.getX(),super.getY(),500,300);
        super.getApp().fill(0,0,0);
        super.getApp().textSize(48);
        super.getApp().text(curName,super.getX()+150,super.getY()+150);
    }

    public boolean isClicked(int mouseX, int mouseY){
        float d = super.getApp().dist(mouseX,mouseY,super.getX()+250,super.getY());
        return d<295;

    }

    public void flip(){
        if (curName.equals(super.getName())){
            curName = super.getName2();
        }
        else{
            curName = super.getName();
        }
    }

    public void reset(){
        curName =(super.getName());
    }
}
