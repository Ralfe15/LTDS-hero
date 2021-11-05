import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private int x;
    private int y;

    Hero(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void moveUp(){
        setY(y-1);
    }
    public void moveDown(){ setY(y+1);
    }
    public void moveLeft(){
        setX(x-1);
    }
    public void moveRight(){
        setX(x+1);
    }
    public void draw(Screen screen){
        screen.setCharacter(getX(), getY(), TextCharacter.fromCharacter('R')[0]);
    }
}
