package vue;

import javax.swing.*;

public class JButtonmod extends JButton {

    private int intx;
    private int inty;

    public JButtonmod(){
        super("---");
    }

    public void setIntx(int intx) {
        this.intx = intx;
    }

    public void setInty(int inty) {
        this.inty = inty;
    }

    public int getIntx() {
        return intx;
    }

    public int getInty() {
        return inty;
    }
}
