import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GamePanel extends JPanel {
    private Ship ship;

    //Key states
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    public GamePanel() {
        ship = new Ship(400, 300);
        setBackground(Color.BLACK);
        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> leftPressed = true;
                    case KeyEvent.VK_RIGHT -> rightPressed = true;
                    case KeyEvent.VK_UP -> upPressed = true;
                    case KeyEvent.VK_DOWN -> downPressed = true;
                }
            }

            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> leftPressed = false;
                    case KeyEvent.VK_RIGHT -> rightPressed = false;
                    case KeyEvent.VK_UP -> upPressed = false;
                    case KeyEvent.VK_DOWN -> downPressed = false;
                }
            }
        });

        // Game loop (60 FPS)
        Timer timer = new Timer(1000 / 60, e -> {
            int speed = 5;
            if (leftPressed) ship.move(-speed, 0);
            if (rightPressed) ship.move(speed, 0);
            if (upPressed) ship.move(0, -speed);
            if (downPressed) ship.move(0, speed);
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ship.draw(g);
    }

}
