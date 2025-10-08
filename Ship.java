
import java.awt.*;




public class Ship {
    private int x, y;

    public Ship(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void move(int dx, int dy){
        x+=dx;
        y+=dy;
    }

    public void draw(Graphics g) {
        int[] xs = {x, x - 10, x + 10};
        int[] ys = {y - 15, y + 10, y + 10};
        g.setColor(Color.WHITE);
        g.fillPolygon(xs, ys, 3);
    }

    public int getx() {return x; }
    public int gety() {return y; }
}
