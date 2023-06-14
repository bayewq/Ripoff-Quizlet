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
  static int score = 0;
  public void settings() {
    size(720, 480);
  }

  public void loadFromFile(String file){
    String output = "";
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
    loadFromFile("C:\\Users\\Bay\\Desktop\\All\\school\\ics final assignment\\awesomesauce\\Main\\src\\terms.txt");
    newBlock();
  }

  public void draw() {
    background(204, 255, 255);

    if (status.equals("menu")){
      fill(0,0,0);
      textSize(32);
      text("Welcome to knockoff Quizlet (TM)",100,50);

    }
    
    else if (status.equals("game1")){
      fill(0,0,0);
      rect(0, 400, 700,5);
      textSize(32);
      text("Input: "+userInput,100,450);
      textSize(18);
      text("Score: "+score,10,445);
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
    if (keyCode=='1'){
      status = "menu";
      userInput = "";
    }

    if (keyCode == LEFT) {
    
    }
    else if (keyCode == RIGHT) {

    }
    else if (keyCode == UP){

    }
    else if (keyCode == DOWN){

    }
    else if (key != ENTER){
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
    
  }
}