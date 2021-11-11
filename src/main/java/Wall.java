import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall {
    public Position position;
    Wall(int x, int y){
        this.position = new Position(x,y);
    }
    public int getY() {
        return position.getY();
    }
    public int getX() {
        return position.getX();
    }
    public void draw(TextGraphics graphics){
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");

    }
}

