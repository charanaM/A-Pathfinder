/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfx;

/**
 * Name - Charana Mayakaduwa
 * UOW student number - w1626663
 * IIT student number - 2016139
 */
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class PathFinder {
    
    //visited and checked nodes
    List<Node> openList = new ArrayList<>();
    //path nodes for calculation
    List<Node> closedList = new ArrayList<>();
    //path nodes for drawpath
    List<Node> path = new ArrayList<>();
    Node start;
    Node end;

    public PathFinder(Node start,Node end){
        this.start=start;
        this.end=end;
    }
    public void astar (int a[][]){
        
        
        start.setMoveCost(0);
        
        switch (PathFindingFX.choise){
            case 1:
                start.setHeuristic(manhatanDistance(start.getX(),start.getY(),end.getX(),end.getY()));
                break;
            case 2:
                start.setHeuristic(euclideanDistance(start.getX(),start.getY(),end.getX(),end.getY()));
                break;
            case 3:
                start.setHeuristic(chebyshevDistance(start.getX(),start.getY(),end.getX(),end.getY()));
                break; 
        }
        
        
        closedList.add(start);
        try {
            findNeighbours(a,start);
        }catch (Exception e){
        }
        while (true) {
            Node node = openList.get(0);
            
            //enhanced for loop for the openlist to continue till list stops
            for (Node n : openList) {
                //get the neighbour with the least cost
                if (   node.getMoveCost() > n.getMoveCost()) {//node.getMoveCost() > n.getMoveCost() &&
                    node = n;
                }
            }

            openList.remove(node);
            closedList.add(node);


            try {
                findNeighbours(a,node);
            }catch (Exception e){
                break;
            }
        }

//        for (Node n:closedList ) {
//            //StdDraw.setPenColor(StdDraw.GREEN);
//            //StdDraw.filledSquare(n.getY(),a.length-n.getX()-1, .5);
//
//        }




    }



    public void findNeighbours(int[][] a,Node node)throws Exception{
        calculateCost(node.getX(), node.getY() - 1, a, node);   // down
        calculateCost(node.getX(), node.getY() + 1, a, node);   // up
        calculateCost(node.getX() - 1, node.getY(), a, node);   //left
        calculateCost(node.getX() + 1, node.getY(), a, node);   //right

        //diaganally =====================================================================================


        calculateCost(node.getX() - 1, node.getY() - 1, a, node);

        calculateCost(node.getX() + 1, node.getY() - 1, a, node);

        calculateCost(node.getX() + 1, node.getY() + 1, a, node);

        calculateCost(node.getX() - 1, node.getY() + 1, a, node);


        // ==============================================================================================
    }



    public Pane drawpath(int a[][]){
        path.add(end);
        Pane p = new Pane();
        Node n= closedList.get(closedList.size()-1);
        path.add(n);

        while (true){
            if(n.getParent()!=null){
                n=n.getParent();
                path.add(n);
                if(n.equals(start)){
                    break;
                }  
            }
            
        }

        Image background  = new Image("jfx/map.JPG");
        
        double width = 30;
    
        int x = a.length;

        Rectangle [][] square = new Rectangle [x][x];

        for(int i=0; i<x; i++){
            for(int j=0; j<x; j++){
                square[i][j] = new Rectangle();
                square[i][j].setX(j * width);
                square[i][j].setY(i * width);
                square[i][j].setWidth(width);
                square[i][j].setHeight(width);
                //square[i][j].setFill(null);
                square[i][j].setStroke(Color.BLACK);
                
                square[i][j].setFill(new ImagePattern(background));
                square[i][j].setOpacity(0.1);
                p.getChildren().add(square[i][j]);
            
                int colorArr = a[i][j];
                
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

        for (Node node:path ) {
            //StdDraw.setPenColor(StdDraw.BLUE);
            try {
                //StdDraw.line(node.getParent().getY(), a.length-node.getParent().getX()-1, node.getY(), a.length-node.getX()-1);
                square[node.getY()][node.getX()].setFill(Color.YELLOW);
                square[node.getY()][node.getX()].setOpacity(0.9);
            }catch (Exception e){
                
                }
            }
        
        square[start.getY()][start.getX()].setFill(Color.RED);
        square[end.getY()][end.getX()].setFill(Color.BLUE);
        
        return p;
        

        }

    
    public void calculateCost(int x, int y, int[][] a, Node rootNode) throws Exception { //rootNode is th parent node
        int N = a.length;
        if (x < 0 || x >= N) {
            return;
        }
        if (y < 0 || y >= N) {
            return;
        }

        Node n = new Node(x, y);
        
        
        //check for black cells and avoid them
        if(a[y][x] != 0){
            
            double fValue = 0;
            int aCost = 0;
            
            switch (PathFindingFX.choise){
            case 1:
                fValue = manhatanDistance(rootNode.getX(),rootNode.getY(),x,y) +a[y][x];
                aCost = a[y][x];
                break;
            case 2:
                fValue = euclideanDistance(rootNode.getX(), rootNode.getY(), x, y)+a[y][x];
                aCost = a[y][x];
                break;
            case 3:
                fValue=chebyshevDistance(rootNode.getX(),rootNode.getY(),x,y)+a[y][x];
                aCost = a[y][x];
                break; 
        }
            
        
        //int gValue = manhatanDistance(parent.getX(),parent.getY(),x,y) +a[y][x];
        //double gValue = euclideanDistance(parent.getX(), parent.getY(), x, y)+a[y][x];
        //int gValue=chebyshevDistance(parent.getX(),parent.getY(),x,y)+a[y][x];
        if (n.equals(end)) {
            end.setParent(rootNode);
            //end.setMoveCost(gValue + rootNode.getMoveCost());
            //============
            
            //end.setActualCost(aCost + rootNode.getActualCost());
            end.setMoveCost(rootNode.getActualCost());
            //end.setActualCost(aCost + rootNode.getActualCost());
            end.setParent(rootNode);
            throw new Exception("Goal Found");
        }
        

        if (!(openList.contains(n) || closedList.contains(n))) {
//             n.setHeuristic(manhatanDistance(x, y, end.getX(), end.getY()));
//              n.setHeuristic(euclideanDistance(x, y, end.getX(), end.getY()));
//            n.setHeuristic(chebyshevDistance(x, y, end.getX(), end.getY()));
            n.setParent(rootNode);
            //n.setMoveCost(gValue + rootNode.getMoveCost());
            
            //==========================
            
            n.setActualCost(aCost + rootNode.getActualCost());
            n.setMoveCost(fValue + rootNode.getActualCost());
            //n.setActualCost(aCost + rootNode.getActualCost());
            openList.add(n);
        }
        }
    }



    public  int manhatanDistance(int x1, int y1, int x2, int y2){
        int distance = Math.abs(x1-x2) + Math.abs(y1-y2);
        return distance;
    }


    public  double euclideanDistance(int x1, int y1, int x2, int y2){
        double distance = Math.round((Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2)))* 10)/10.0;
        return distance;
    }

    public int chebyshevDistance(int x1, int y1,int x2 ,int y2){
        int distance = Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2));
        return distance;

    }

}

