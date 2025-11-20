import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;



public class GamePanel extends JPanel {
    private Ship ship;
    private java.util.List<Bullet> bullets = new ArrayList<>();
    private java.util.List<Asteroid> asteroids = new ArrayList<>();
    private int spawnCounter = 0;
    private final int SPAWN_INTERVAL = 60;
    private int lives = 3;
    private int score = 0;


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
                    case KeyEvent.VK_SPACE -> fireBullet();
                }
            }
            protected void fireBullet() {
                bullets.add(new Bullet(ship.getx(), ship.gety()-15));
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
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            if (leftPressed) ship.move(-speed, 0, panelWidth, panelHeight);
            if (rightPressed) ship.move(speed, 0, panelWidth, panelHeight);
            if (upPressed) ship.move(0, -speed, panelWidth, panelHeight);
            if (downPressed) ship.move(0, speed, panelWidth, panelHeight);

            // Move and remove bullets
            Iterator<Bullet> iterator = bullets.iterator();
            while (iterator.hasNext()) {
                Bullet b = iterator.next();
                b.update();
                if (b.isOffScreen(panelHeight)) {
                    iterator.remove();
                }
            }

            // Move and remove asteroids
            Iterator<Asteroid> asteroidIterator = asteroids.iterator();
            while (asteroidIterator.hasNext()) {
                Asteroid a = asteroidIterator.next();
                a.update();
                if (a.isOffScreen(getHeight())) {
                    asteroidIterator.remove();
                }
            }

            // Spawn new asteroids
            spawnCounter++;
            if (spawnCounter >= SPAWN_INTERVAL) {
                asteroids.add(new Asteroid(getWidth()));
                spawnCounter = 0;
            }
            Rectangle shipBounds = ship.getBounds();
            for (Asteroid a : asteroids) {
                if (!ship.isInvincible() && shipBounds.intersects(a.getBounds())) {
                    lives--;

                    ship.hit();   // start blinking + invincibility
                    System.out.println("Ship hit! Lives: " + lives);

                    if (lives <= 0) {
                        JOptionPane.showMessageDialog(this, "Game Over!");
                        System.exit(0);
                    }
                }
            }
            ship.updateInvincibility();
            // Bullet vs Asteroid Collision
            Iterator<Bullet> bulletIterator = bullets.iterator();
            while (bulletIterator.hasNext()) {
                Bullet b = bulletIterator.next();
                Rectangle bulletBounds = new Rectangle(b.getX(), b.getY(), 4, 10);

                Iterator<Asteroid> asteroidIter = asteroids.iterator();
                while (asteroidIter.hasNext()) {
                    Asteroid a = asteroidIter.next();
                    if (bulletBounds.intersects(a.getBounds())) {
                        bulletIterator.remove();
                        asteroidIter.remove();
                        break; // Bullet is gone, move to next bullet
                    }
                }
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ship.draw(g);
        for (Bullet b : bullets) {
            b.draw(g);
        }
        for (Asteroid a : asteroids) {
            a.draw(g);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Lives: " + lives, 10, 25);
    }

}
