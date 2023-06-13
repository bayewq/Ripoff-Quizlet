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
  String status = "game1";
  Boolean scored = false;

  int currentblock;
  int currentblock2;
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
  public void newBlocks(){
    currentblock = random(0,blocks.size()-1);
  }
  public void setup() {
    background(204, 255, 255);
    loadFromFile("C:\\Users\\bayew\\Desktop\\Final Project\\Main\\src\\terms.txt");
    newBlocks();
  }

  public void draw() {
    background(204, 255, 255);
    
    if (status.equals("game1")){
      fill(0,0,0);
      rect(0, 400, 700,5);
      textSize(32);
      text("Input: "+userInput,100,450);
      textSize(16);
      text("Score: "+score,20,445);
      blocks.get(currentblock).draw();
      blocks.get(currentblock).move(0,1);

      if(blocks.get(currentblock).getY()>=350 && !scored){
        score -= 1;
        blocks.get(currentblock).setX(random(50,600));
        blocks.get(currentblock).setY(-25);
      }
      else if (scored){
        newBlocks();
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
}