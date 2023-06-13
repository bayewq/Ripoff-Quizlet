import processing.core.PApplet;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Sketch extends PApplet {

  public int random(int min, int max){
    return ThreadLocalRandom.current().nextInt(min, max + 1);
  }

  String userInput = "";
  String status = "game1";
  Boolean scored = false;
  static int score = 5;
  private Block block;
  public void settings() {
    size(720, 480);
  }

  public void setup() {
    background(204, 255, 255);
    textSize(32);

    block = new Block(this,"poo",random(50,450),0,2);
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
      block.draw();
      block.move(0, 1);
      if (block.getY()>=350 && scored==false){
        block.setY(0);
        block.setX(random(25,450));
      }
      else if (scored){
        block.setY(0);
        block.setX(random(25,450));
        scored = false;
      }
    }

  }

  public void processInput(){
    if (userInput.equals(block.getName())){
      scored = true;
    }
    userInput = "";
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