/* Mostrar el valor de la torre en la GUI */

import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaTorre extends JFrame{
    
    private Torre torre;
    private JLabel PtsTorre;
    
    public VentanaTorre(){
        // Crear la torre y el Jlabel
        torre = new Torre(this);
        PtsTorre = new JLabel("Puntos Torre: "+torre.getVida());
        
        // Agregar el Label a la Ventana
        add (PtsTorre);
        
        // Configuracion de la ventana
        setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
        
    // Método Actualizar el contenido del JLabel
    public void actualizarVidas() {
        PtsTorre.setText("Puntos Torre: " + torre.getVida());
    }
    
    // Metodo para actualizar los puntos
    public void actualizarPuntos() {
        PtsTorre.setText("Puntos Torre: " + torre.getPuntaje());
    }
}
