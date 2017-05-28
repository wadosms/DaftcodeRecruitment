package com.example.vadik.recruitmentapp;

/**
 * Items for rows of Recycler View
 */

public class RecyclerItem {

    private int counter;
    private Colour colorOfCircle;

    public RecyclerItem(int counter, Colour colorOfCircle) {
        this.counter = counter;
        this.colorOfCircle = colorOfCircle;
    }

    public int getCounter() {return counter; }

    public Colour getColorOfCircle(){return colorOfCircle; }

    public String getRecyclerItemText(){
        return String.valueOf(counter);
    }

    public void setColorOfCircle (Colour color){
        this.colorOfCircle = color;
    }

    public void setCounter (int counter){
        this.counter = counter;
    }
}
