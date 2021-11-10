import java.awt.*;
import java.util.ArrayList;

public class Animator extends Thread {

    private Graphics g;
    int[] points = new int[4];

    ArrayList<Ball> balls = new ArrayList<>();

    public Animator(Graphics g) {
        this.g = g;
    }

    public void drawOval(int x, int y, int width, int height) {
        g.setColor(Color.black);
        g.drawOval(x, y, width, height);
        points[0] = x;
        points[1] = y;
        points[2] = width;
        points[3] = height;
    }

    public void refresh() {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 800, 600);
        for (int i = 0; i < balls.size(); i++) {
            g.setColor(balls.get(i).color);
            g.fillOval(balls.get(i).x,
                    balls.get(i).y,
                    balls.get(i).size,
                    balls.get(i).size);
        }
    }

    public void addBall(int x, int y, int size, Color color) {
        Ball b = new Ball(x, y, size, color);
        balls.add(b);
        b.start(); // в отдельном потоке пересчитываем координаты этого шарика
    }


    @Override
    public void run() {
        super.run();
        while (true) {
            refresh();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
