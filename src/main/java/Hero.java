import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private Position position;

    Hero(Position position){
        this.position = position;
    }

    public int getY() {
        return position.getY();
    }
    public int getX() {
        return position.getX();
    }
    public void setPosition(Position position){
        this.position = position;
    }
    public Position moveUp(){
        return new Position(position.getX(), position.getY()-1);
    }
    public Position moveDown(){ return new Position(position.getX(), position.getY()+1);}
    public Position moveLeft(){
        return new Position(position.getX()-1, position.getY());
    }
    public Position moveRight(){
        return new Position(position.getX()+1, position.getY());
    }
    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(10, 10), ' ');
        graphics.enableModifiers(SGR.BOLD);
        graphics.setCharacter(getX(), getY(), TextCharacter.fromCharacter('R')[0]);


    }
}
