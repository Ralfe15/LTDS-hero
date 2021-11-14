import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.concurrent.ThreadLocalRandom;

public class Monster extends Element{

    public Monster(int x, int y) {
        super(new Position(x,y));
    }

    public Position move(){
        int randomNum = ThreadLocalRandom.current().nextInt(0, 4);
        switch(randomNum){
            case 0: return new Position(this.position.getX()+1, this.position.getY());
            case 1: return new Position(this.position.getX()-1, this.position.getY());
            case 2: return new Position(this.position.getX(), this.position.getY()+1);
            case 3: return new Position(this.position.getX(), this.position.getY()-1);
            default: break;
        }
        return null;
    }
    public void setPosition(Position position){
        this.position = position;
    }

    public void draw(TextGraphics graphics){
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "M");
    }
}
