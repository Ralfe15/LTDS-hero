import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element{
    Wall(int x, int y){
        super(new Position(x,y));
    }
    void draw(TextGraphics graphics){
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");

    }

}

