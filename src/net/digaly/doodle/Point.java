package net.digaly.doodle;

/**
 * Created by Tom Dobbelaere on 2/10/2016.
 */
public class Point
{
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }
}
