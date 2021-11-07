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

    public void draw(Screen screen){
        hero.draw(screen);
    }
    }

