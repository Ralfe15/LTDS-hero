import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    Screen screen;
    Hero hero;
    Game(){
        try {
            Hero hero = new Hero(10,10);
            this.hero = hero;
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            Screen screen = new TerminalScreen(terminal);
            this.screen = screen;
            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(){
        try {
            screen.clear();
            hero.draw(screen);
            screen.refresh();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void run(){
        while (true) {
            draw();
            KeyStroke key = null;
            try {
                key = screen.readInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
            processKey(key);
        }

    }
    private void processKey(KeyStroke key) {
        switch (key.getKeyType()){
            case ArrowUp: hero.moveUp(); break;
            case ArrowDown: hero.moveDown(); break;
            case ArrowLeft: hero.moveLeft(); break;
            case ArrowRight: hero.moveRight(); break;
            default: break;

        }
    }
}
