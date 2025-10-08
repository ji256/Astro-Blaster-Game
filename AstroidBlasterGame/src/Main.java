import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class Main {


    public static void main(String[] args) {


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

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        SwingUtilities.invokeLater(gamePanel::requestFocusInWindow);


        }
    }
