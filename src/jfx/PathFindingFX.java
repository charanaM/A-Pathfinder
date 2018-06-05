/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx;

//import static algocoursework.PathFindingOnSquaredGrid.show;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import static javax.swing.text.StyleConstants.Background;

/**
 * Name - Charana Mayakaduwa
 * UOW student number - w1626663
 * IIT student number - 2016139
 */

public class PathFindingFX extends Application {
    
    public static int choise;
    
    static int colX;
    static int colY;
    
    Node startNode =null;
    
    boolean start = false;
    boolean stop = false;
    static Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) {
      
    this.primaryStage =primaryStage; 
    double width = 30;
    Pane p = new Pane();
    
    Image background  = new Image("jfx/map.JPG");
    p.setBackground(new Background(new BackgroundImage (background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            
    //ImagePattern bgPattern = new ImagePattern(background);
    
    
    Alert alertbox = new Alert(AlertType.INFORMATION);
    alertbox.setTitle("Grid Option");
    alertbox.setHeaderText("Select the distance metric");
    
    ButtonType btnMN = new ButtonType("Manhattan");
    ButtonType btnEU = new ButtonType("Euclidean");
    ButtonType btnCHE = new ButtonType("Chebyshev");
    
    alertbox.getButtonTypes().setAll(btnMN,btnEU, btnCHE);
    
    Optional<ButtonType> btnresult = alertbox.showAndWait();
    
    if(btnresult.get() == btnMN){
        choise = 1;
    }else if(btnresult.get() == btnEU){
        choise = 2;
    }else if(btnresult.get() == btnCHE){
        choise = 3;
        
    }
    
    
    int n = newArray.length;

    Rectangle [][] square = new Rectangle [n][n];

    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            square[i][j] = new Rectangle();
            square[i][j].setX(j * width);
            square[i][j].setY(i * width);
            square[i][j].setWidth(width);
            square[i][j].setHeight(width);
            //square[i][j].setFill(null);
            square[i][j].setStroke(Color.BLACK);
            
            square[i][j].setFill(new ImagePattern(background));
            square[i][j].setOpacity(0.1);
            p.getChildren().addAll(square[i][j]);
            
            
            int colorArr = newArray[i][j];
                
                switch(colorArr){
                    case 0:
                        square[i][j].setFill(Color.BLACK);
                        break;
                    
                    case 1:
                        square[i][j].setFill(Color.WHITE);
                        break;
                        
                    case 2:
                        square[i][j].setFill(Color.LIGHTGRAY);
                        break;
                        
                    case 3:
                        square[i][j].setFill(Color.DARKGRAY);
                        break;
                        
                    case 4:
                        square[i][j].setFill(Color.GREY);
                        break;
                    
                }

        }
    }
    
    Scene scene = new Scene(p, width*20, width*20);

     primaryStage.setTitle("Grid");
    primaryStage.setScene(scene);
    primaryStage.show();
    
    
    p.setOnMouseClicked(new EventHandler <MouseEvent> (){
        @Override
        public void handle(MouseEvent me){
            
            int Ai=0,Aj=0,Bi=0,Bj=0;
            
            if(!start){
                
            double posX = me.getX();
            double posY = me.getY();

            Ai = (int)(posY / width);
            Aj = (int) (posX / width);

            square[Ai][Aj].setFill(Color.RED);
            square[Ai][Aj].setOpacity(0.9);
            
            System.out.println(Ai +" , "+ Aj);
            
            startNode =new Node(Aj,Ai);
            
            start = true;
                
            }else if(start && !stop){
                
            double posX = me.getX();
            double posY = me.getY();

            Bi = (int)(posY / width);
            Bj = (int) (posX / width);

            square[Bi][Bj].setFill(Color.BLUE);
            square[Bi][Bj].setOpacity(0.9);
            
            System.out.println(Bi +" , "+ Bj);
            
            stop = true;
            
            try{
                Thread.sleep(100);
                   
                      Stopwatch timerFlow = new Stopwatch();
                      
                    //show(newArray, 1, Ai, Aj, Bi, Bj);
                    // THIS IS AN EXAMPLE ONLY ON HOW TO USE THE JAVA INTERNAL WATCH
                    // Stop the clock ticking in order to capture the time being spent on inputting the coordinates
                    // You should position this command accordingly in order to perform the algorithmic analysis
                    StdOut.println("Elapsed time = " + timerFlow.elapsedTime());

                    // System.out.println("Coordinates for A: [" + Ai + "," + Aj + "]");
                    // System.out.println("Coordinates for B: [" + Bi + "," + Bj + "]");
                    //show(randomlyGenMatrix, true, Ai, Aj, Bi, Bj);

                    Stopwatch total = new Stopwatch();
                    
//                     int[][] newArray2 = 
//    {
//        
//            {1,1,1,1,1,1,1,1,1,1,2,1,2,1,1,1,1,1,1,1},
//            {4,4,1,4,1,1,1,2,2,2,2,2,2,1,1,1,1,1,1,1},
//            {4,4,4,4,4,4,1,1,2,3,3,3,2,1,1,1,1,1,1,1},
//            {4,4,4,4,4,4,1,1,2,3,3,3,3,2,1,1,1,1,1,1},
//            {1,1,4,1,1,1,1,1,2,2,3,3,3,2,1,1,2,2,1,1},
//            {1,4,4,1,2,2,1,1,1,2,2,2,2,2,1,2,2,2,1,1},
//            {4,2,1,1,2,2,1,1,1,1,1,1,1,1,1,2,2,1,1,1},
//            {1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,4},
//            {1,1,2,3,3,2,2,1,1,1,1,1,1,1,1,1,1,4,4,4},
//            {1,2,3,3,3,3,2,2,1,1,1,1,4,4,4,4,4,4,4,1},
//            {1,2,3,2,2,2,3,2,4,1,1,1,4,4,4,4,2,1,1,1},
//            {1,2,2,1,1,1,4,4,4,4,1,1,4,4,4,1,1,1,1,1},
//            {1,1,4,4,4,4,4,4,4,4,1,1,1,1,1,1,1,1,1,1},
//            {4,4,4,4,4,4,4,4,4,1,1,0,1,1,1,1,1,1,1,0},
//            {1,1,4,4,4,4,1,1,1,2,2,0,0,1,1,1,1,1,1,0},
//            {1,1,1,1,1,1,1,1,1,1,2,1,0,0,0,1,1,0,0,0},
//            {1,2,2,2,2,2,1,1,1,2,1,1,1,0,0,0,0,0,0,0},
//            {2,2,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0},
//            {1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
//            {1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0}
//            
//    };
                     
                    Node endNode = new Node(Bj,Bi);
                     
                    System.out.println("start - x- "+startNode.getX()+" y - "+startNode.getY());
                    System.out.println("start - x- "+endNode.getX()+" y - "+endNode.getY());
        
                   
                    try {
            PathFinder pf = new PathFinder(startNode, endNode);
            Stopwatch aStar = new Stopwatch();
            pf.astar(newArray);
            StdOut.println("Elapsed time  A* Search= " + aStar.elapsedTime());

            Stopwatch draw = new Stopwatch();
            
            Pane p = pf.drawpath(newArray);
            
            p.setBackground(new Background(new BackgroundImage (background, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            
            Scene scene = new Scene(p, 600, 600);

            primaryStage.setTitle("Grid");
            primaryStage.setScene(scene);
            primaryStage.show();
            
            StdOut.println("Elapsed time Draw Path= " + draw.elapsedTime());

            StdOut.println("Elapsed time Total= " + total.elapsedTime());
            System.out.println("Count "+endNode.getMoveCost());
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Statistics");
            alert.setHeaderText("Statistics of the path");
            alert.setContentText("Elapsed time A* Search= " + aStar.elapsedTime() +"\n"+ "Elapsed time Draw Path= " + draw.elapsedTime() + "\n" + "Elapsed time Total= " + total.elapsedTime() + "\n" + "Cost = "+endNode.getMoveCost());
            
            alert.showAndWait();
            
       
                    
        }catch (IndexOutOfBoundsException e){
            System.out.println("The system not percolates start point to end point");
        }
                    
                      
            }catch (InterruptedException ex) {
                    Logger.getLogger(PathFindingFX.class.getName()).log(Level.SEVERE, null, ex);
        }
                
            }
            
            
            
        }
    });

        //coordinates();
        
        
        
     
    }

    private static int[][] newArray = 
    {
    
        {1,1,1,1,1,1,1,1,1,1,2,1,2,1,1,1,1,1,1,1},
            {4,4,1,4,1,1,1,2,2,2,2,2,2,1,1,1,1,1,1,1},
            {4,4,4,4,4,4,1,1,2,3,3,3,2,1,1,1,1,1,1,1},
            {4,4,4,4,4,4,1,1,2,3,3,3,3,2,1,1,1,1,1,1},
            {1,1,4,1,1,1,1,1,2,2,3,3,3,2,1,1,2,2,1,1},
            {1,4,4,1,2,2,1,1,1,2,2,2,2,2,1,2,2,2,1,1},
            {4,2,1,1,2,2,1,1,1,1,1,1,1,1,1,2,2,1,1,1},
            {1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,4},
            {1,1,2,3,3,2,2,1,1,1,1,1,1,1,1,1,1,4,4,4},
            {1,2,3,3,3,3,2,2,1,1,1,1,4,4,4,4,4,4,4,1},
            {1,2,3,2,2,2,3,2,4,1,1,1,4,4,4,4,2,1,1,1},
            {1,2,2,1,1,1,4,4,4,4,1,1,4,4,4,1,1,1,1,1},
            {1,1,4,4,4,4,4,4,4,4,1,1,1,1,1,1,1,1,1,1},
            {4,4,4,4,4,4,4,4,4,1,1,0,1,1,1,1,1,1,1,0},
            {1,1,4,4,4,4,1,1,1,2,2,0,0,1,1,1,1,1,1,0},
            {1,1,1,1,1,1,1,1,1,1,2,1,0,0,0,1,1,0,0,0},
            {1,2,2,2,2,2,1,1,1,2,1,1,1,0,0,0,0,0,0,0},
            {2,2,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0}
    };
    
    
    
    
    public static void main(String[] args) {
        //makeGrid(5);
        launch(args);
        
        
        
        
    }
    
}
