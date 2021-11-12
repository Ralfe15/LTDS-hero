import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.concurrent.ThreadLocalRandom;

public class Monster extends Element{

    public Monster(int x, int y) {
        super(new Position(x,y));
    }


    public void draw(TextGraphics graphics){
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "M");

    }
}
