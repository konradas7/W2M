package com.konradas.where2meet.obj;

public class Location {
    double X;
    double Y;

     public Location() {}
     public Location(double X, double Y) {
         this.X= X;
         this.Y= Y;
     }

     void setPos(double X, double Y) {
         this.X= X;
         this.Y= Y;
     }

     public double getX() {
         return X;
     }

     public double getY() {
        return Y;
    }
}
