//import nessecary libraries
import processing.core.PApplet;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents a sketch that extends the PApplet class from the Processing library.
 * It includes methods for creating blocks and flashcards, loading data from a file, and handling user input.
 */

public class Sketch extends PApplet {

  // initiate all nessecary storage items (arraylist, variables, objects, etc.)
  ArrayList<Block> blocks = new ArrayList<Block>();
  ArrayList<Flashcard> cards = new ArrayList<Flashcard>();
  String userInput = "";
  String status = "menu";
  Boolean scored = false;
  int createStage = 0;
  int currentBlock = 0;
  int prevBlock;
  int currentCard;
  String fileName;
  String term1;
  String term2;
  int textX = random(10,600);
  int textY = random(10,400);
  boolean loaded = false;
  int curBlockX = 0;
  private Button gameButton;
  private Button backButton;
  private Button gameButton2;
  private Button loadButton;
  private Button createButton;
  private Button learnButton;
  private Button nextButton;
  private Button prevButton;
  int x = 0;
  static int score = 0;

  /**
   * Generates a random integer within the specified range.
   *
   * @param min the minimum value (inclusive)
   * @param max the maximum value (inclusive)
   * @return a random integer within the specified range
   */

  public int random(int min, int max){
    return ThreadLocalRandom.current().nextInt(min, max + 1);
  }

  /**
   * Sets the settings for the sketch window.
   * 
   * This method is called before the sketch starts and is used to set the initial settings for 
   * the sketch window. In this case, it sets the size of the window to 720 pixels wide and 
   * 480 pixels high using the size() function provided by the processing library.
   * 
   * It is recommended to call this method once in the setup() method of the sketch.
   */

  public void settings() {
    size(720, 480);
  }

  /**
   * Retrieves the number of lines in a file.
   *
   * @param fileName the name of the file to be processed
   * @return the number of lines in the file as an integer
   * @throws IOException if an I/O error occurs while reading the file
   */
  public static int getNumberLines(String fileName) {
      //creates path object & long variable
      Path path = Paths.get(fileName);
      long lines = 0;

      // counts number of lines 
      try {
          lines = Files.lines(path).count();

      } catch (IOException e) {
          e.printStackTrace();
      }
      
      // casts long into int
      int intlines = (int) lines;
      return intlines;

  }
  /**
   * Retrieves the number of lines in a file.
   *
   * @param file the name of the file to be processed
   * @throws IOException if an I/O error occurs while reading the file
   */
  public void loadFromFile(String file){
    
    // init int variables and gets number of lines in file
    int totalLines = getNumberLines(file);
    int currentLine = 0;
    String output = "";

    // resets arraylists
    blocks.clear();
    cards.clear();

    // initiates 2d array 
    String[][] importArray = new String[totalLines][2];
    
    try{

      // starts scanner
      Scanner fileInput = new Scanner(new File (file));

      // runs for every line
      while (fileInput.hasNext()){

        // sets output to the file line
        output = fileInput.nextLine();

        // splits the string and assigns the values into 2d aray 
        String[] data = output.split(",",0);
        importArray[currentLine][0] = data[0];
        importArray[currentLine][1] = data[1];

        // pulls terms from 2d array
        String name = importArray[currentLine][0];
        String name2 = importArray[currentLine][1];

        // creates objects based on terms and randomizes the x position
        Block tempBlock = new Block(this,name,name2,random(20,600),-100);
        Button butt = new Button(this,100,50);
        Block tempBlock2 = new Flashcard(this,name,name2,100,50,butt);
        
        // adds objects to arraylist
        cards.add(((Flashcard)tempBlock2));
        blocks.add(tempBlock);

        // adds 1 to the currentline
        currentLine++;
      }
    }
    catch(IOException e){
    }
  }

  /**
   * Generates a new block # and checks if it's the same as the previous, using recursion
   */
  public void newBlock() {
    // stores the currentblock
    prevBlock = currentBlock;

    //randomizes the block value
    currentBlock = random(0, blocks.size() - 1);

    // checks if the new block is the same as the previous block
    if (prevBlock == currentBlock) {
        newBlock();
    }
}
  /**
   * Setup method that runs once at the start of the program, this is where I set up all of my objects and load the first terms from the file. 
   */

  public void setup() { 

    // sets background
    background(204, 255, 255);

    // loads default text file 
    loadFromFile("Main\\src\\terms.txt");

    //inits new blocks
    gameButton = new Button(this,100,200,"game");
    backButton = new Button(this,600,418,"exit");
    gameButton2 = new Button(this,100,325,"game2");
    learnButton = new Button(this,225,200,"learn");
    loadButton = new Button(this,350,200,"load");
    createButton = new Button (this,475,200,"create");
    nextButton = new Button(this,375,420,"next");
    prevButton = new Button(this,225,420,"back");
    
    // randomizes which block to choose
    newBlock();
    
  }

  /**
   * Draw method that draws stuff to the screen; runs multiple times per second
   */
  public void draw() {
    
    background(204, 255, 255);
    fill(0,0,0);
    
    // if the user isn't on the menu screen, the program displays a exit button for the user to return back to the menu 
    if (!(status.equals("menu"))){
      rect(0, 400, 800,5);
      backButton.draw();
    }

    // if the user is on the menu screen, it draws out all the options for stuff the user can do 
    if (status.equals("menu")){
      score = 0;
      userInput = "";
      fill(0,0,0);
      textSize(32);
      text("Bootleg Quizlet (TM)",100,100);
      gameButton.draw();
      gameButton2.draw();
      learnButton.draw();
      loadButton.draw();
      createButton.draw();
    }
    
    //create screen
    else if (status.equals("create")){
      
      // checks if the user is at the end of the stages, then doesnt allow them to contineu
      if (createStage != 4){
        nextButton.draw();
      }
      prevButton.draw();
      
      textSize(32);
      fill(0,0,0);
      

      // for each stage, the user will be able to enter text and that text will be stored for later
      if (createStage == 0){
        text("Enter filename: "+userInput,100,200);
      }
      else if (createStage == 1){
        text("Enter term 1: "+userInput,100,200);
      }
      else if (createStage == 2){
        text("Enter term 2: "+userInput,100,200);
      }

      // output all the user imputed to the screen for double checking
      else if (createStage == 3){
        text("Press enter to confirm:",100,100);
        text("filename: "+fileName,100,150);
        text("term1: "+term1,100,200);
        text("term2: "+term2,100,250);  
      }

      //confirmation message
      else if (createStage == 4){
        text("done.",100,100);
      }
    }
    
    // game # 1
    else if (status.equals("game1")){
      
      // output the user's input and the score to the screen
      fill(0,0,0);
      textSize(32);
      text("Input: "+userInput,100,450);
      textSize(18);
      text("Score: "+score,10,445);
      
      // draw the current falling block and move it down 
      blocks.get(currentBlock).draw();
      blocks.get(currentBlock).move(0,1);
      
      // if the block passes a certain point, it is reset and the score goes down by 1 
      if(blocks.get(currentBlock).getY()>=350 && !scored){
        score -= 1;
        int prevX = blocks.get(currentBlock).getX();
        blocks.get(currentBlock).setX(random(50,600));
        
        // makes sure the block isnt in the same place twice 
        if (prevX==blocks.get(currentBlock).getX()){
          while (prevX==blocks.get(currentBlock).getX()){
            blocks.get(currentBlock).setX(random(50,600));
          }
        }

        // sets the block's Y value to off screen 
        blocks.get(currentBlock).setY(-25);
      }

      // if the user types the right thing in then score is added 
      else if (scored){
        // generate new block (different term )
        newBlock();

        // set new block to offscreen
        blocks.get(currentBlock).setY(-25);
        score++;

        // multiplier based on score for speed
        double mult = score/10;
        // sets the new speed
        blocks.get(currentBlock).setSpeed(1+mult);
        scored = false;
      }
    }

    // on the learn screen, the program just draws the current card and the next/previous buttons 
    else if (status.equals("learn")){
      cards.get(currentCard).draw();
      nextButton.draw();
      prevButton.draw();
    }
    
    //if the user is on this screen, they will be able to specify what file to load form 
    else if (status.equals("load")){
      fill(0,0,0);
      textSize(32);
      // text to screen 
      text("Load from: "+userInput,25,450);
      
      // outputs message if file is loaded 
      if (loaded){
        text("file loaded!",100,100);
      }
    }
    
    // for the second game, the user will pilot a block to its corrosponding text and if they touch, then the user scores 
    else if (status.equals("game2")){
      textSize(18);
      // draws current block 
      blocks.get(currentBlock).draw();
      fill(0,0,0);
      text("Score: "+score,10,445);
      text(blocks.get(currentBlock).getName(),textX,textY);
      
      // checks distance of the 2 blocks
      if (dist(blocks.get(currentBlock).getX(),blocks.get(currentBlock).getY(),textX,textY)<50){
        scored = true;
        processInput();
      }

    }

  }

  /**
   * Method that runs as long as the mouse is pressed, used for detecting clicks 
   */
  public void mousePressed(){
    
    // checks if game button is clicked as well as if the user is on the menu and sets the status to game1
    if (gameButton.isClicked(mouseX, mouseY) && status.equals("menu")){
      newBlock();
      userInput = "";
      status = "game1";
    }

    // checks if the exit button is clicked and returns user to menu as well as resets all values 
    if (backButton.isClicked(mouseX, mouseY) && !(status.equals("menu"))){
      status = "menu";
      loaded = false;
      userInput = "";
      score = 0;
    }

    // takes user to learn menu if learn button is clicked 
    if (learnButton.isClicked(mouseX, mouseY) && status.equals("menu")){
      currentCard = 0;
      cards.get(currentCard).reset();
      status = "learn";
    }

    // takes user to create menu if create menu is clicked 
    if (createButton.isClicked(dmouseX, dmouseY)&& status.equals("menu")){
      status = "create";
      createStage = 0;
      userInput = "";
    }

    // takes user to load menu if load menu is clicked 
    if (loadButton.isClicked(dmouseX, dmouseY)&& status.equals("menu")){
      status = "load";
    }

    // takes user to game2 menu if game2 button is clicked 
    if (gameButton2.isClicked(dmouseX, dmouseY)&& status.equals("menu")){
      newBlock();
      blocks.get(currentBlock).setY(100);
      status = "game2";
    }

    // if a card is clicked, and the user is on the learn screen, the card is flipped and the other term is showwn 
    if (cards.get(currentCard).isClicked(dmouseX, dmouseY) && status.equals("learn")){
      cards.get(currentCard).flip();
    }

    // if the next button is clicked..
    else if (nextButton.isClicked(dmouseX, dmouseY)){
      
      // when the user is on the learn page, the program will cycle to the next flashcard, if the program is on the last flashcard, it goes back to beginning
      if (status.equals("learn")){
        cards.get(currentCard).reset();
        if (currentCard == cards.size()-1){
          currentCard = 0;
        }
        else{
          currentCard++;
        }
      }

      // on the create page, the program simply goes through each of the stages unless they are on the last stage
      else if (status.equals("create")){
        if (createStage == 3){
          processInput();
        }
        createStage++;
      }
      
    }

    // if the previous button is clicked...
    else if (prevButton.isClicked(dmouseX, dmouseY)){
      
      // on the learn page, the user will go to the previous flashcard 
      if (status.equals("learn")){
        cards.get(currentCard).reset();
        if (currentCard == 0){
          currentCard = cards.size()-1;
        }
        else{
          currentCard--;
        }
      }

      // on the create page the program will go to the previous stage 
      else if (status.equals("create")){
        createStage--;
      }
    }

  }

  /**
   * Method that runs when the user presses enter (to input text), used for (obviously) processing the input with regard to the current context of the situation
   */
  public void processInput(){
    
    // sets scored bollean to true if the user input matches the translation of the block 
    if (status.equals("game1") && userInput.equals(blocks.get(currentBlock).getName())){
      scored = true;
    }

    // if the user is loading a file, then the program will subsequently load that file and set loaded boolean to true;
    else if (status.equals("load")){
      loadFromFile("Main\\src\\"+userInput);
      loaded = true;
      userInput="";
    }

    // on the create page, the program will assign the userinput to variables to be used in creating a new text file 
    else if (status.equals("create")){
      if (createStage == 0){
        fileName = userInput;
        userInput = "";
      }
      else if(createStage == 1){
        term1 = userInput;
        userInput = "";
      }
      else if (createStage == 2){
        term2 = userInput;
        userInput = "";
      }
      else if (createStage == 3){
        // the program will create a new file here and output the new term there 
        try{
          // starts filewriter
          FileWriter writer = new FileWriter("Main/src/"+fileName, true);
          // writes the terms to the text file 
          writer.write(term1+","+term2+"\n");
          writer.close();
        }
        // catching ioexceptions
        catch(IOException e){
          System.out.println("fail");
        }
      }
    }

    // on the game 2 page the program will add score if the 2 blocks are touching 
    else if (status.equals("game2")){
      score++;
      // generates new block
      newBlock();
      // sets block position to y = 100
      blocks.get(currentBlock).setY(100);
      // sets a random spot for the new text 
      textX = random(10, 600);
      textY = random(10,400);
      scored = false;
    }
  }

  /**
   * Method that runs whenever a key is pressed; used for moving object around the screen with arrow keys as well as for user inputting text on the sdreen. 
   */
  public void keyPressed() {
    
    // controlling the block with arrow keys if the user is on game2 
    if (status.equals("game2")){
      
      // moves block in the corrosponding direction of the arrow key 
      if (keyCode == LEFT) {
        blocks.get(currentBlock).move(-10,0);
      }
      else if (keyCode == RIGHT) {
        blocks.get(currentBlock).move(10,0);
      }
      else if (keyCode == UP){
        blocks.get(currentBlock).move(0,-10);
      }
      else if (keyCode == DOWN){
        blocks.get(currentBlock).move(0,10);
      }
    }
    
    //adds keystrokes to userinput if enter not pressed
    else if (key != ENTER){
      userInput+= key;

      // resets userinput if backspace pressed 
      if(keyCode == BACKSPACE){
        userInput = "";
      }
    }
    // processes user input if enter is pressed
    else if (keyCode == ENTER){
      processInput();
      userInput = "";
    }
 
  }
}
  