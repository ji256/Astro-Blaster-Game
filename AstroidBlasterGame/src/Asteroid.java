import java.awt.*;
import java.util.Random;

public class Asteroid {
    private int x, y;
    private int size;
    private int speed;

    private static final Random rand = new Random();

    public Asteroid(int panelWidth) {
        this.size = 30 + rand.nextInt(20); // Size between 30–50
        this.x = rand.nextInt(panelWidth - size);
        this.y = -size; // Start above screen
        this.speed = 2 + rand.nextInt(3); // Speed between 2–4
    }

    public void update() {
        y += speed;
    }

    public boolean isOffScreen(int panelHeight) {
        return y > panelHeight;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillOval(x, y, size, size);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }
}
