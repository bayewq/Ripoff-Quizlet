import java.util.jar.Attributes.Name;

import processing.core.PApplet;

public class Flashcard extends Block{
    private String curName = super.getName();
    private Button butt;

    public Flashcard(PApplet p, String name, String name2, int x, int y, Button butt){
        super(p,name,name2,x,y);
        this.butt = butt;
    }

    public void draw(){
        super.getApp().fill(255,255,255);
        super.getApp().rect(super.getX(),super.getY(),500,300);
        super.getApp().fill(0,0,0);
        super.getApp().textSize(48);
        super.getApp().text(curName,super.getX()+150,super.getY()+150);
    }

    public boolean isClicked(int mouseX, int mouseY){
        if(butt.isClicked(mouseX, mouseY)){
            return true;
        }
        else{
            return false;
        }

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
