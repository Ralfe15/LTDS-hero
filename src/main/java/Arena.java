import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private final int width;
    private final int height;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;
    public Hero hero;

    Arena(int width, int height) {
        this.height = height;
        this.width = width;
        this.hero = new Hero(new Position(10, 10));
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean canMoveHero(Position position) {
        if (position.getY() < 0 || position.getY() >= height) {
            return false;
        } else if (position.getX() < 0 || position.getX() >= width) {
            return false;
        }
        for (Wall wall : walls) {
            if(wall.getPosition().equals(position)){
                return false;
            }
        }
        return true;
    }

    public boolean canMoveMonster(Position position){
        if (position.getY() < 0 || position.getY() >= height) {
            return false;
        } else if (position.getX() < 0 || position.getX() >= width) {
            return false;
        }
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) {
                return false;
            }
        }
        return true;
    }

    public void moveHero(Position position) {
        if (canMoveHero(position)) {
            verifyMonsterCollisions(position);
            for(Monster monster: monsters){
                Position newpos = monster.move();
                while(!canMoveMonster(newpos)){
                    newpos = monster.move();
                }
                monster.setPosition(newpos);
                }
            verifyMonsterCollisions(position);
            retrieveCoins(position);
            hero.setPosition(position);
        }
        }


    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case ArrowDown:
                moveHero(hero.moveDown());
                break;
            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                break;
            default:
                break;

        }
    }

    public void retrieveCoins(Position position) {
        for (int i = 0; i < coins.size(); i++) {
            if (position.equals(coins.get(i).getPosition())) {
                coins.remove(i);
            }
        }
    }

    public void verifyMonsterCollisions(Position position) {
        for (int i = 0; i < monsters.size(); i++) {
            if (position.equals(monsters.get(i).getPosition())) {
                hero.die();
            }
        }
    }
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(40, 20), ' ');
        for (Wall wall : walls) {
            wall.draw(graphics);
        }
        for (Coin coin : coins) {
            coin.draw(graphics);
        }
        for (Monster monster : monsters) {
            monster.draw(graphics);
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

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Coin tmp = new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1);
            if (!coins.stream().map(coin -> coin.getPosition()).anyMatch(x -> x.equals(tmp.getPosition()))) {
                coins.add(tmp);
            }
        }
        return coins;
    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Monster tmp = new Monster(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1);
            if (!coins.stream().map(coin -> coin.getPosition()).anyMatch(x -> x.equals(tmp.getPosition())) && !monsters.stream().map(monster -> monster.getPosition()).anyMatch(x -> x.equals(tmp.getPosition()))) {
                monsters.add(tmp);
            }
        }
        return monsters;
    }

}
