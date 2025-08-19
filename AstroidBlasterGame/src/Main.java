import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.event.*;
public class Main {

    private static int mouseX = 400, mouseY = 300;
    private static JPanel panel;

    public static void main(String[] args) {

        panel = new JPanel();
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setTitle("Astro Blaster");
        try{
            BufferedImage iconImage = ImageIO.read(new File("C:\\Users\\jerem\\IdeaProjects\\AstroidBlasterGame\\src\\resources\\Images\\image3.png"));
            window.setIconImage(iconImage);
        }catch(IOException e){
            e.printStackTrace();
        }
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blank = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank");
        window.setCursor(blank);


        //Adds the ship that will be used in the game.
        //Also moves based on input from the mouse or touchpad
        panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int[] xs = {mouseX, mouseX - 10, mouseX + 10};
                int[] ys = {mouseY - 15, mouseY + 10, mouseY + 10};
                g.setColor(Color.WHITE);
                g.fillPolygon(xs, ys, 3);
            }
        };

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                panel.repaint();
            }
        });
        panel.setFocusable(true);
        panel.requestFocusInWindow();




        panel.setBackground(Color.BLACK);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.add(panel);





        }
    }
