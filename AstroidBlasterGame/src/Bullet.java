import java.awt.*;

public class Bullet {
    private int x,y;
    private final int speed = 10;
    private final int width = 4;
    private final int height = 10;

    public Bullet(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }
    public void update(){
        y -= speed;
    }
    public boolean isOffScreen(int panelHeight) {
        return y + height < 0;
    }
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x - width / 2, y, width, height);
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
