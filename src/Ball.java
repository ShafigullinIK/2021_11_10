import java.awt.*;
import java.util.Random;

public class Ball extends Thread {

    int x;
    int y;
    int size;
    Color color;
    int speed = new Random().nextInt(200);

    public Ball(int x, int y, int size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    public void moveBall() {
        int add = 1;
        while (x >= 10 && x <= 600) {
            x += add;
            if (x == 10 || x == 600) {
                add *= -1;
            }
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        moveBall();
    }
}
