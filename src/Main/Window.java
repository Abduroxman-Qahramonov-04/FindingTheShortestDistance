package Main;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
public class Window {
    private JFrame window;
    private JPanel panel;

    private final TreeSet<Shop> locations = new TreeSet<>(Comparator.comparing(Shop::getDistanceFromX));
    private ArrayList<Shop> shops;

    private static final int CIRCLE_RADIUS = 3;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private static final int SHOP_SIZE = 65;
    private static final int locationSize = 25;

    private static int circleX = -10;
    private static int circleY = -10;
    private int xCor=0;
    private int yCor=0;

    private String pathKorzinka = "C:\\Users\\viktusi5\\IdeaProjects\\ProjectFTCW\\src\\assets\\img_1.png";
    private String pathHavas = "C:\\Users\\viktusi5\\IdeaProjects\\ProjectFTCW\\src\\assets\\img_3.png";
    private String pathMakro = "C:\\Users\\viktusi5\\IdeaProjects\\ProjectFTCW\\src\\assets\\img_4.png";

    private BufferedImage image;
    private BufferedImage locationImage;
    private BufferedImage KorzinkaImage;
    private BufferedImage HavasImage;
    private BufferedImage MakroImage;


    public Window() {
        panelling();
    }
    public void show(){
        window.setVisible(true);
    }
    private void panelling(){
        init();
        fillShops();
        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.ORANGE);
                if (image != null) {
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                }
                if (circleX != -10 && circleY != -10) {
                    g.drawImage(locationImage,circleX - CIRCLE_RADIUS, circleY - CIRCLE_RADIUS, locationSize,locationSize, this);
                }

                for (int i = 0; i < shops.size(); i++) {
                    Shop shop = shops.get(i);
                    g.setColor(shop.getColor());
                    int[] ar = deFormulate(shop.getX(),shop.getY());
                    g.drawImage(shop.getImage(),ar[0],ar[1] , SHOP_SIZE, SHOP_SIZE,this);
                }
            }
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(Window.WIDTH, Window.HEIGHT);
            }
        };

        JLabel coordinatesLabel = new JLabel("Coordinates:");

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                xCor = e.getX();
                yCor = e.getY();

                circleX = xCor;
                circleY = yCor;
                int[] ar = formulate(xCor,yCor);
                xCor = ar[0];
                yCor = ar[1];

                for (int i = 0; i < shops.size(); i++) {
                    Shop shop = shops.get(i);
                    double distance = findDistance(xCor,yCor,shop.getX(),shop.getY());
                    shop.setDistanceFromX(distance);
                    locations.add(shop);
                }
                coordinatesLabel.setText("Coordinates: X = " + xCor + ", Y = " + yCor+"     The closest shop:  "+locations.first().getName()+" Distance:  "+Math.round((locations.first().getDistanceFromX())*100)/100+ " m");

                locations.clear();
                System.out.println();
                panel.repaint();
            }
        });
        window.setLayout(new BorderLayout());
        window.add(panel,BorderLayout.CENTER);
        window.add(coordinatesLabel, BorderLayout.NORTH);
        window.pack();
    }
    public void fillShops(){
        shops = new ArrayList<>();
        Shop shop1 = new Shop(430,237,"Havas",Color.GREEN,HavasImage);
        Shop shop2 = new Shop(-414,50,"Korzinka",Color.RED,KorzinkaImage);
        Shop shop3 = new Shop(25,-120,"Makro",Color.blue,MakroImage);

        shops.add(shop1);
        shops.add(shop2);
        shops.add(shop3);
    }
    public void init(){
        window = new JFrame();
        window.setTitle("Finding the closest shop program");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(WIDTH, HEIGHT);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        try {
            image = ImageIO.read(new File("C:\\Users\\viktusi5\\IdeaProjects\\ProjectFTCW\\src\\assets\\img.png"));
            locationImage = ImageIO.read(new File("C:\\Users\\viktusi5\\IdeaProjects\\ProjectFTCW\\src\\assets\\img_2.png"));
            KorzinkaImage = ImageIO.read(new File(pathKorzinka));
            MakroImage = ImageIO.read(new File(pathMakro));
            HavasImage = ImageIO.read(new File(pathHavas));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public double findDistance(int cX,int cY,int ShX,int ShY){
        return Math.sqrt(Math.pow((ShX-cX),2)+Math.pow((ShY-cY),2));
    }
    protected int[] formulate(int x, int y){
        int[] array = new int[2];
        int xC = x-WIDTH/2;
        int yC = HEIGHT/2-y;

        array[0] = xC;
        array[1] = yC;
        return array;
    }
    protected int[] deFormulate(int x, int y){
        int[] array = new int[2];
        int xC = WIDTH/2+x;
        int yC = HEIGHT/2-y;

        array[0] = xC;
        array[1] = yC;
        return array;
    }
}

