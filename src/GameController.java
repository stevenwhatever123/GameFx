import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * The class is the controller for the whole game, every action is controlled here.
 *
 * @version 1.6
 */
public class GameController extends Application {

    // Store what key has been pressed
    private final Set<KeyCode> pressedKeys = new HashSet<>();

    // Map for the game, not yet implemented
    private Tile[][] mapData;

    // Pane for the window
    private Pane root = new Pane();

    // Setting image views for player and different tiles
    ImageView playerView = new ImageView();
    ImageView view = new ImageView();

    // Creating player object
    private Player player = new Player(playerView, root);

    // An array list of not walkable tiles and walkable tiles
    private ArrayList<Tile> notWalkableTiles = new ArrayList<Tile>();
    private ArrayList<Tile> walkableTiles = new ArrayList<Tile>();
    private ArrayList<Tile> interactiveTile = new ArrayList<Tile>();

    // Boolean if the dialogue is on
    private boolean dialogueOn = false;

    // timeCounter for storing every how may nano second has passed
    private long timeCounter = 0;

    // time passed after the game has start
    private int time = 0;

    // store time as a temp
    private long checkPointTime;

    // Timer for updating the frame every nano second
    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            timeCounter ++;
            if(now - timeCounter >= 1000000000){
                time++;
                //System.out.println("Current time: " + time);
                timeCounter = now;
            }
            update();
        }
    };

    public GameController() throws FileNotFoundException {
    }

    /**
     * This create the initial content of the game.
     * @return the root pane
     */
    private Parent createContent() throws IOException {
        // Setting the size of the window
        root.setPrefSize(1280,768);

        int offsetX = 0;
        int offsetY = 0;

        // Filling the tile for the screen
        for (int x = 0; x < root.getPrefWidth() / 64; x++){
            offsetY = 0;
            for(int y = 0; y < root.getPrefHeight() / 64; y++){
                ImageView view = new ImageView();
                Tile ground = new Tile(view,"Ground", offsetX, offsetY);
                walkableTiles.add(ground);
                offsetY += 64;
                root.getChildren().addAll(ground);
            }
            offsetX += 64;
        }
        // Adding a wall to a specific location
        ImageView redView = new ImageView();
        Tile wall = new Tile(redView, "Wall",200,200);
        interactiveTile.add(wall);
        notWalkableTiles.add(wall);
        root.getChildren().add(wall);

        // setting the background colour of the game
        root.setStyle("-fx-background-color: #ff00c1");

        // Setting the position of the player
        player.setTranslateX(300);
        player.setTranslateY(300);

        // Adding the player to the pane
        root.getChildren().addAll(player);
        return root;
    }

    /**
     * This method controls the action for the player to move upwards.
     */
    public void moveUp(){
        if(dialogueOn == true){
        } else {
            player.animation.play();
            player.animation.setOffsetY(96);
            player.moveY(-5);
            if(player.checkCollision(notWalkableTiles)){
                while(player.checkCollision(notWalkableTiles)){
                    player.moveY(1);
                }
            }
            if(player.checkBoundary(root)){
                while (player.checkBoundary(root)) {
                    player.moveY(1);
                }
            }
        }

    }

    /**
     * This method controls the action for the player to move downwards.
     */
    public void moveDown(){
        if(dialogueOn == true){

        } else{
            player.animation.play();
            player.animation.setOffsetY(0);
            player.moveY(5);
            if(player.checkCollision(notWalkableTiles)) {
                while (player.checkCollision(notWalkableTiles)) {
                    player.moveY(-1);
                }
            }
            if(player.checkBoundary(root)){
                while (player.checkBoundary(root)) {
                    player.moveY(-1);
                }
            }
        }
    }

    /**
     * This method controls the action for the player to move left.
     */
    public void moveLeft(){
        if(dialogueOn == true){

        } else{
            player.animation.play();
            player.animation.setOffsetY(32);
            player.moveX(-5);
            if(player.checkCollision(notWalkableTiles)) {
                while (player.checkCollision(notWalkableTiles)) {
                    player.moveX(1);
                }
            }
            if(player.checkBoundary(root)){
                while (player.checkBoundary(root)) {
                    player.moveX(1);
                }
            }
        }
    }

    /**
     * This method controls the action for the player to move right.
     */
    public void moveRight(){
        if(dialogueOn == true){

        } else{
            player.animation.play();
            player.animation.setOffsetY(64);
            player.moveX(5);
            if(player.checkCollision(notWalkableTiles)) {
                while (player.checkCollision(notWalkableTiles)) {
                    player.moveX(-1);
                }
            }
            if(player.checkBoundary(root)){
                while (player.checkBoundary(root)) {
                    player.moveX(-1);
                }
            }
        }
    }

    /**
     * This method controls the action when the player tries to interact with the tile
     */
    public void interact(){
        // Check if the tile is interactable
        if(checkHitBox(interactiveTile)){
            if(dialogueOn == false){
                dialogueOn = true;
                player.startDialogue();
            } else {
                dialogueOn = false;
                player.clearDialogue();
            }
        }
    }

    /**
     * This method updates the frame of the game.
     */
    public void update(){
        if(pressedKeys.contains(KeyCode.W)){
            moveUp();
        } else if(pressedKeys.contains(KeyCode.S)){
            moveDown();
        } else if(pressedKeys.contains(KeyCode.A)){
           moveLeft();
        } else if(pressedKeys.contains(KeyCode.D)){
            moveRight();
        } else if(pressedKeys.contains(KeyCode.SPACE)){
            if(System.currentTimeMillis() - checkPointTime >= 80){
                interact();
            }
            checkPointTime = System.currentTimeMillis();
        } else {
            player.animation.stop();
        }
    }

    /**
     * This method checks if the characters can interact with each other in a certain range.
     * @param tile interactiveTile
     * @return true if in range, otherwise, false
     */
    public boolean checkHitBox(ArrayList<Tile> tile){
        boolean hitBoxDetected = false;
        for(int i = 0; i < tile.size(); i++){
            player.moveX(-1);
            if(player.checkCollision(tile)){
                hitBoxDetected = true;
            }
            player.moveX(1);

            player.moveX(1);
            if(player.checkCollision(tile)){
                hitBoxDetected = true;
            }
            player.moveX(-1);

            player.moveY(-1);
            if(player.checkCollision(tile)){
                hitBoxDetected = true;
            }
            player.moveY(1);

            player.moveY(1);
            if(player.checkCollision(tile)){
                hitBoxDetected = true;
            }
            player.moveY(-1);
        }
        return hitBoxDetected;
    }

    /**
     * This method launches the game.
     * @param stage the current stage
     */
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        scene.setOnKeyPressed(e -> pressedKeys.add(e.getCode()));
        scene.setOnKeyReleased(e -> pressedKeys.remove(e.getCode()));
        timer.start();
        stage.setTitle("GameFX");
        stage.setScene(scene);
        stage.show();
        checkPointTime = System.currentTimeMillis();
    }

}
