package Main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Shop {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistanceFromX() {
        return distanceFromX;
    }

    public void setDistanceFromX(double distanceFromX) {
        this.distanceFromX = distanceFromX;
    }

    public Shop(int x, int y, String name,Color color,BufferedImage image) {
        this.x = x;
        this.color = color;
        this.y = y;
        this.name = name;
        this.distanceFromX = 0;
        this.image = image;
    }

    private int x;
    private int y;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private Color color;
    private String name;
    private double distanceFromX;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
    private BufferedImage image;

}
