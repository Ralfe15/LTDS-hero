import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

public class Arena {
    private final int width;
    private final int height;
    public Hero hero;

    Arena(int width, int height){
        this.height = height;
        this.width = width;
        this.hero = new Hero(new Position(10,10));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean canMoveHero(Position position){
        if(position.getY() < 0 || position.getY() >= height){
            return false;
        }
        else if(position.getX() < 0 || position.getX() >= width){
            return false;
        }
        return true;
    }
    public void moveHero(Position position){
        if(canMoveHero(position)) {
            hero.setPosition(position);
        }
    }
    public void processKey(KeyStroke key){
        switch (key.getKeyType()){
            case ArrowUp: moveHero(hero.moveUp()); break;
            case ArrowDown: moveHero(hero.moveDown()); break;
            case ArrowLeft: moveHero(hero.moveLeft()); break;
            case ArrowRight: moveHero(hero.moveRight()); break;
            default: break;

        }
    }

    public void draw(TextGraphics screen){

        screen.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width * 2, height * 2), ' ');
        screen.putString(new TerminalPosition(hero.getX() * 2, hero.getY() * 2), "\\/");
        screen.putString(new TerminalPosition(hero.getX() * 2, hero.getY() * 2 + 1), "/\\");
        hero.draw(screen);
    }
    }

