import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private final int width;
    private final int height;
    private List<Wall> walls;
    public Hero hero;

    Arena(int width, int height){
        this.height = height;
        this.width = width;
        this.hero = new Hero(new Position(10,10));
        this.walls = createWalls();
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
        for(Wall wall: walls){
            if(wall.getX() == position.getX() && wall.getY() == position.getY()){
                return false;
            }
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

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(40, 20), ' ');
        for(Wall wall: walls){
            wall.draw(graphics);
        }
        hero.draw(graphics);
    }
    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }

        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }

        return walls;
    }
    }

