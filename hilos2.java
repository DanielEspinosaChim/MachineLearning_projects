import java.awt.*;
import javax.swing.*;
public class hilos2 {

    public static void main(String[] args) {
        Thread t1 = new Thread(new ClaseRunnable(50));
        Thread t2 = new Thread(new ClaseRunnable(50));
        Thread t3 = new Thread(new ClaseRunnable(50));

        t1.setName("Hilo 1");
        t2.setName("Hilo 2");
        t3.setName("Hilo 3");

        t1.start();
        t2.start();
        t3.start();
    }
}
class ClaseRunnable extends JFrame implements Runnable {
    int max;
    Container caja;
    JLabel label;
    JTextField hilonom;
    JPanel panel;

    ClaseRunnable(int max) {
        super("Multihilos");
        this.max = max;

        label = new JLabel("");
        hilonom = new JTextField(3);
        caja = getContentPane();
        caja.setLayout(new FlowLayout());

        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(label);
        panel.add(hilonom);
        panel.setBackground(Color.CYAN);

        caja.add(panel);
        setSize(250, 150);
        setLocationRelativeTo(null);
        caja.setBackground(Color.blue);
        setVisible(true);
    }

    public void run() {
        label.setText(Thread.currentThread().getName());
        for (int i = 0; i < max; i++) {
            hilonom.setText("" + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                
            }
        }
    }
}
