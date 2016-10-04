package net.digaly.doodle.util;

/**
 * Created by Tom Dobbelaere on 4/10/2016.
 */
public class DoodleUtil
{
    public static boolean rectangleOverlaps(double sourceX, double sourceY, double sourceWidth, double sourceHeight,
                                            double targetX, double targetY, double targetWidth, double targetHeight) {
        return (sourceX < targetX + targetWidth && sourceX + sourceWidth > targetX &&
                sourceY < targetY + targetHeight && sourceY + sourceHeight > targetY);
    }

}
