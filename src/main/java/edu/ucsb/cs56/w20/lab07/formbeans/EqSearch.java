package edu.ucsb.cs56.w20.lab07.formbeans;
public class EqSearch{
    private int distance;
    private int minmag;

    EqSearch(){
    
    }
    public void setDistance(int d){
        this.distance = d;
    }

    public void setMinmag(int m){
        this.minmag = m;
    }

    public int getDistance(){
        return this.distance;
    }
    public int getMinmag(){
        return this.minmag;
    }
}
