import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Main extends JFrame {

    //    Graphics panelsGraphics;
    Animator animator;
    int x;
    int y;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        super();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(200, 50, 800, 600);
        this.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 2, 800, 500);
        panel.setBackground(Color.WHITE);
        this.add(panel);
        Graphics panelsGraphics = panel.getGraphics();
        animator = new Animator(panelsGraphics);
        animator.start();   // 60 раз в секунду перерисовываем шарики
        System.out.println("Аниматор создан!");

        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                animator.drawOval(e.getX(), e.getY(), 10, 10);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int releasedX = e.getX();
                int releasedY = e.getY();
                panelsGraphics.drawOval(x, y, releasedX - x, releasedY - y);

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        JButton button = new JButton();
        button.setBounds(700, 520, 70, 40);
        button.setText("refresh");
        this.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animator.addBall(
                        10,
                        new Random().nextInt(500),
                        10,
                        Color.BLUE
                );

            }
        });
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (animator != null) {
            animator.refresh();
        }
    }
}
