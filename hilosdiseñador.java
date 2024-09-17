import java.awt.*;
import javax.swing.*;

public class hilosdiseñador {

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

        // Configurando el JLabel
        label = new JLabel("");
        label.setFont(new Font("Verdana", Font.BOLD, 18)); // Fuente más grande
        label.setForeground(new Color(255, 99, 99)); // Rojo coral más suave (#FF6363)

        // Configurando el JTextField
        hilonom = new JTextField(5);
        hilonom.setFont(new Font("Arial", Font.PLAIN, 16)); // Fuente del texto
        hilonom.setHorizontalAlignment(JTextField.CENTER); // Centrar el texto
        hilonom.setBackground(new Color(240, 240, 240)); // Fondo gris claro
        hilonom.setForeground(Color.BLUE); // Texto azul

        // Configurando el contenedor principal
        caja = getContentPane();
        caja.setLayout(new BorderLayout()); // Usamos BorderLayout para mejor disposición
        caja.setBackground(new Color(244, 248, 230)); // Fondo suave para toda la ventana (#F4F8E6)

        // Panel de contenido
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.NORTH); // Colocar el label arriba
        panel.add(hilonom, BorderLayout.CENTER); // Colocar el JTextField en el centro
        panel.setBackground(new Color(216, 222, 196)); // Fondo verde grisáceo (#D8DEC4)

        // Añadir márgenes al panel
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Añadir el panel al contenedor principal
        caja.add(panel, BorderLayout.CENTER);

        // Configuraciones generales de la ventana
        setSize(400, 200); // Tamaño más amplio
        setLocationRelativeTo(null); // Centrar en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar al salir
        setVisible(true); // Hacer visible la ventana
    }

    public void run() {
        label.setText(Thread.currentThread().getName());
        for (int i = 0; i < max; i++) {
            hilonom.setText("" + i);
            System.out.println("Hilo: " + Thread.currentThread().getName() + " - Valor: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                // Manejar excepción si ocurre
            }
        }
    }
}
