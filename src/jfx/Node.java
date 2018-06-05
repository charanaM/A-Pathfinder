
package jfx;

/**
 * Name - Charana Mayakaduwa
 * UOW student number - w1626663
 * IIT student number - 2016139
 */

public class Node {
    
    //integers for node data
    private double heuristic;
    private double moveCost;
    private double actualCost;
    private int x;
    private int y;
    private Node parent;

    public Node(){

    }

    public Node(int x,int y){
        this.x=x;
        this.y=y;
    }

    public double getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(double heuristic) {
        this.heuristic = heuristic;
    }

    public double getMoveCost() {
        return moveCost;
    }

    public void setMoveCost(double moveCost) {
        this.moveCost = moveCost;
    }
    
    public double getActualCost() {
        return actualCost;
    }

    public void setActualCost(double actualCost) {
        this.actualCost = actualCost;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object obj) {
        Node n= (Node) obj;
        if(n.getX()==x && n.getY()==y){

            return true;
        }else {
            return false;
        }

    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        //String p = (parent!=null)?" || "+parent.toString()+ " || ":"Root";
        return x + " "+ y+" "+moveCost+" "+heuristic+" ";
    }
}

