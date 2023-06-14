import processing.core.PApplet;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Sketch extends PApplet {

  ArrayList<Block> blocks = new ArrayList<Block>();

  public int random(int min, int max){
    return ThreadLocalRandom.current().nextInt(min, max + 1);
  }

  String userInput = "";
  String status = "menu";
  Boolean scored = false;

  int currentblock;
  int prevblock;
  private Button button1;
  private Button backbutton;

  static int score = 0;
  public void settings() {
    size(720, 480);
  }

  public void loadFromFile(String file){
    String output = "";
    blocks.clear();
    try{
      Scanner fileInput = new Scanner(new File (file));
      while (fileInput.hasNext()){
        output = fileInput.nextLine();
        String[] data = output.split(",",0);
        String name = data[0];
        String name2 = data[1];
        Block tempBlock = new Block(this,name,name2,random(20,600),-100);
        blocks.add(tempBlock);
      }
    }
    catch(IOException e){
    }
  }

  public void newBlock(){
    prevblock = currentblock;
    currentblock = random(0,blocks.size()-1);
    if (prevblock==currentblock){
      while (prevblock==currentblock){
        currentblock = random(0,blocks.size()-1);
      }
    }
  }

  public void setup() { 
    background(204, 255, 255);
    loadFromFile("C:\\Users\\bayew\\Desktop\\Final Project\\Main\\src\\terms.txt");
    button1 = new Button(this,100,100,"game");
    backbutton = new Button(this,625,425,"exit");
    newBlock();
  }

  public void draw() {
    background(204, 255, 255);

    if (status.equals("menu")){
      userInput = "";
      fill(0,0,0);
      textSize(32);
      text("Bootleg Quizlet (TM)",100,50);
      button1.draw();

    }
    
    else if (status.equals("game1")){
      fill(0,0,0);
      rect(0, 400, 700,5);
      textSize(32);
      text("Input: "+userInput,100,450);
      textSize(18);
      text("Score: "+score,10,445);
      backbutton.draw();
      blocks.get(currentblock).draw();
      blocks.get(currentblock).move(0,1);

      if(blocks.get(currentblock).getY()>=350 && !scored){
        score -= 1;
        int prevx = blocks.get(currentblock).getX();
        blocks.get(currentblock).setX(random(50,600));
        if (prevx==blocks.get(currentblock).getX()){
          while (prevx==blocks.get(currentblock).getX()){
            blocks.get(currentblock).setX(random(50,600));
          }
        }

        blocks.get(currentblock).setY(-25);
      }
      else if (scored){
        newBlock();
        blocks.get(currentblock).setY(-25);
        score+= 1;
        scored = false;
      }
    }



  }

  public void processInput(){
    if (userInput.equals(blocks.get(currentblock).getName())){
      scored = true;
    }
    userInput="";
  }

  public void keyPressed() {
    if (keyCode == LEFT) {
    
    }
    else if (keyCode == RIGHT) {

    }
    else if (keyCode == UP){

    }
    else if (keyCode == DOWN){

    }
    else if (key != ENTER && status.equals("game1")){
      userInput+= key;

      if(keyCode == BACKSPACE){
        userInput = "";
      }
    }
    else if (keyCode == ENTER){
      processInput();
    }
 
  }

  public void mousePressed(){
    
    if (button1.isClicked(mouseX, mouseY) && status.equals("menu")){
      newBlock();
      userInput = "";
      status = "game1";
    }

    if (backbutton.isClicked(mouseX, mouseY) && !(status.equals("menu"))){
      status = "menu";
      userInput = "";
      score = 0;
    }
  }
}