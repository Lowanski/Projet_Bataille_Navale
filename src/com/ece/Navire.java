package com.ece;
import java.awt.*;

public abstract class Navire {
    Point coord = null;
    String orientation;

    abstract public void tire();
    abstract public void bouger();

}
