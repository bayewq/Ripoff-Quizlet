import processing.core.PApplet;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Sketch extends PApplet {

  ArrayList<Block> blocks = new ArrayList<Block>();
  ArrayList<Flashcard> cards = new ArrayList<Flashcard>();

  public int random(int min, int max){
    return ThreadLocalRandom.current().nextInt(min, max + 1);
  }

  String userInput = "";
  String status = "menu";
  Boolean scored = false;

  int currentblock = 0;
  int prevblock;
  int currentCard;
  
  private Button gameButton;
  private Button backButton;
  private Button adminButton;
  private Button loadButton;
  private Button createButton;
  private Button learnButton;
  private Button nextButton;
  private Button prevButton;

  int x = 0;

  static int score = 0;
  public void settings() {
    size(720, 480);
  }

  public void loadFromFile(String file){
    String output = "";
    blocks.clear();
    cards.clear();
    try{
      Scanner fileInput = new Scanner(new File (file));
      while (fileInput.hasNext()){
        output = fileInput.nextLine();
        String[] data = output.split(",",0);
        String name = data[0];
        String name2 = data[1];
        Block tempBlock = new Block(this,name,name2,random(20,600),-100);
        Flashcard tempFlash = new Flashcard(this,name,name2,100,50);

        cards.add(tempFlash);
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
    loadFromFile("Main\\src\\terms.txt");
    gameButton = new Button(this,100,200,"game");
    backButton = new Button(this,600,418,"exit");
    adminButton = new Button(this,450,65,"login");
    learnButton = new Button(this,225,200,"learn");
    loadButton = new Button(this,350,200,"load");
    createButton = new Button (this,475,200,"create");
    nextButton = new Button(this,375,420,"next");
    prevButton = new Button(this,225,420,"back");
    newBlock();
    
  }

  public void draw() {
    
    background(204, 255, 255);
    fill(0,0,0);
    if (!(status.equals("menu"))){
      rect(0, 400, 800,5);
      backButton.draw();
    }
    

    if (status.equals("menu")){
      score = 0;
      userInput = "";
      fill(0,0,0);
      textSize(32);
      text("Bootleg Quizlet (TM)",100,100);
      gameButton.draw();
      adminButton.draw();
      learnButton.draw();
      loadButton.draw();
      createButton.draw();
    }
    
    else if (status.equals("game1")){
      fill(0,0,0);
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

    else if (status.equals("learn")){
      cards.get(currentCard).draw();
      nextButton.draw();
      prevButton.draw();
    }

    else if (status.equals("create")){
      
    }
    
    else if (status.equals("load")){

    }
    
    else if (status.equals("admin")){

    }

  }

  public void mousePressed(){
    
    if (gameButton.isClicked(mouseX, mouseY) && status.equals("menu")){
      newBlock();
      userInput = "";
      status = "game1";
    }

    if (backButton.isClicked(mouseX, mouseY) && !(status.equals("menu"))){
      status = "menu";
      userInput = "";
      score = 0;
    }

    if (learnButton.isClicked(mouseX, mouseY) && status.equals("menu")){
      cards.get(currentCard).reset();
      status = "learn";
      cards.get(currentCard).reset();
    }

    if (createButton.isClicked(dmouseX, dmouseY)&& status.equals("menu")){
      status = "create";
    }

    if (loadButton.isClicked(dmouseX, dmouseY)&& status.equals("menu")){
      status = "load";
    }

    if (adminButton.isClicked(dmouseX, dmouseY)&& status.equals("menu")){
      status = "admin";
    }

    if (cards.get(currentCard).isClicked(dmouseX, dmouseY) && status.equals("learn")){
      cards.get(currentCard).flip();
    }

    else if (nextButton.isClicked(dmouseX, dmouseY) && status.equals("learn")){
      cards.get(currentCard).reset();
      if (currentCard == cards.size()-1){
        currentCard = 0;
      }
      else{
        currentCard++;
      }
    }

    else if (prevButton.isClicked(dmouseX, dmouseY) && status.equals("learn")){
      cards.get(currentCard).reset();
      if (currentCard == 0){
        currentCard = cards.size()-1;
      }
      else{
        currentCard--;
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
  