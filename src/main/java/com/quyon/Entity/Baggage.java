package com.quyon.Entity;

public class Baggage {
    public Baggage(double _weight,double _length,double _width,double _height){
        assert (_weight>0&&_length>0&&_width>0&&_height>0);
        weight = _weight;
        length = _length;
        width = _width;
        height = _height;
        size = length+width+height;
    }
    private double weight;
    private double length;
    private double width;
    private double height;
    private double size;

    public double getWeight() {
        return weight;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getSize() {
        return size;
    }
}
