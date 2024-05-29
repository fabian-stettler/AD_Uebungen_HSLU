package Uebungen_AD.week14.GraphAlgorithmen;

import java.awt.*;

public class Node {

    private Color color;
    private int value;

    public Node(){
        this.color = Color.white;
        this.value = Integer.MIN_VALUE;
    }

    public Node(int value){
        this.color = Color.white;
        this.value = value;
    }


    public Color getColor(){
        return this.color;
    }

    public void setColor(Color newColor){
        this.color = newColor;
    }

    public String getValue() {
        return String.valueOf(value);
    }
}
