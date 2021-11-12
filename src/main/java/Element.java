import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {
    public Position position;

    public Element(Position position) {
        this.position=position;
    }
    abstract void draw(TextGraphics graphics);

    public int getY() {
        return this.position.getY();
    }
    public int getX() {
        return this.position.getX();
    }
    public Position getPosition(){
        return this.position;
    }
}
