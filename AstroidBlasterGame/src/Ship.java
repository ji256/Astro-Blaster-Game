import java.awt.*;

public class Ship {
    private int x, y;

    private final int halfWidth = 10;
    private final int topOffset = 15;
    private final int bottomOffset = 10;
    private boolean visible = true;      // used for blinking effect
    private boolean invincible = false;  // prevents repeated hits
    private int invincibleTimer = 0;     // counts frames
    private int blinkTimer = 0;          // toggles visible on/off


    public Ship(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void move(int dx, int dy, int panelWidth, int panelHeight) {
        x+=dx;
        y+=dy;

        //sets boundaries for ship to go
        x = Math.max(halfWidth, Math.min(x, panelWidth - halfWidth));
        y = Math.max(topOffset, Math.min(y, panelHeight - bottomOffset));
    }

    public void draw(Graphics g) {
        if (!visible) return;
        int[] xs = {x, x - halfWidth, x + halfWidth};
        int[] ys = {y - topOffset, y + bottomOffset, y + bottomOffset};
        g.setColor(Color.WHITE);
        g.fillPolygon(xs, ys, 3);
    }
    //Collision Bounds for Ship
   public Rectangle getBounds() {
        int width = halfWidth * 2;
        int height = topOffset * 2;
        return new Rectangle(x - halfWidth, y - topOffset, width, height);
   }
   //Method to simulate ship being hit by asteroid
    public void hit() {
        if (!invincible) {
            invincible = true;
            invincibleTimer = 120; // ~2 seconds if 60 FPS
            blinkTimer = 0;
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void updateInvincibility() {
        if (invincible) {
            invincibleTimer--;

            // Blink every 6 frames
            blinkTimer++;
            if (blinkTimer >= 6) {
                visible = !visible;
                blinkTimer = 0;
            }

            // Invincibility ends
            if (invincibleTimer <= 0) {
                invincible = false;
                visible = true;
            }
        }
    }

    public boolean isInvincible() {
        return invincible;
    }

    public int getx() {return x; }
    public int gety() {return y; }
}
