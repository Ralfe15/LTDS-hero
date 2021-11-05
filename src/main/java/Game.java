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
    private int x = 10;
    private int y = 10;
    Game(){
        try {
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
            screen.setCharacter(x, y, TextCharacter.fromCharacter('R')[0]);
            screen.refresh();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void run(){
        draw();
        KeyStroke key = null;
        try {
            key = screen.readInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
        processKey(key);


    }
    private void processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
            System.out.println(key.getCharacter());
        }
            System.out.println(key);
    }
}
