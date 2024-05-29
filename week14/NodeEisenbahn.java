package Uebungen_AD.week14;

import scala.Int;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class NodeEisenbahn {

    private Integer weight;

    /**
     *
     * weight wird mit Integer min initialisiert.
     */
    public NodeEisenbahn(){
        this.weight = Integer.MIN_VALUE;
    }

    public NodeEisenbahn(int weight){
        this.weight = weight;
    }

    public Integer getWeight() {
        return weight;
    }


    public void setWeight(int weight) {
        this.weight = weight;
    }


}
