/* Movimientos del juego*/

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Juego {
    static JFrame ventana;
    //Presentación
    JPanel panelPresentacion;
    JButton iniciar;
    JLabel fondoPresentacion;
    ImageIcon imagenFondoPres;
    
    // Menu- para lo que va sobre la ventana
    JPanel panelMenu;
    JButton botones[]; //Vector
    JLabel fondoMenu;
    ImageIcon imagenFondoMenu;
    
    //Juego
    /* Hacer estatico, para acceder en la clase y cambiar el valor 
    se modifique en esta clase juego si no esta estatico */
    static JPanel panelJuego; 
    JLabel fondoJuego;
    ImageIcon imagenFondoJuego;
    static int mat[][];// matriz logica, es donde hacemos los cambios 
    static JLabel matriz [][];//matriz de imagen que es la que va mostrar y verse reflejdao esos cambios
    int px; //posicion del pacman x (coordenada x)
    int py; //posicion del pacman y (coordenada y)
    String jugador;
    JLabel nombre;// es para mostrar el nombre del jugador
    int puntos;
    JLabel records; // mostrar los puntos por cada comida del pacman
    // banderas
    int abajo;
    int arriba;
    int izq;
    int der;
    Timer timer;
    
    public Juego () {
        //Crear el marco de la app
        ventana= new JFrame ("Tower Defense");
        ventana.setSize(1200,750);
        //Permite poner botones e imagenes en la posición que queramos
        ventana. setLayout(null); //Si no lo ponemos el sistema lo pone por defecto
        ventana.setLocationRelativeTo(null); //Colocar la ventana principal en el medio
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Permite que cuando le demos "x" la app termine
        ventana.setResizable(false); // Es para desactivar el boton de agradar la pestaña(el amarillo)                                                            // a la aplicación se cierre y no quede ejecutando
            
        //Panel de presentación
        panelPresentacion = new JPanel();
        panelPresentacion.setLayout(null); //Permite que pongamos los componentes en el panel donde queramos
        panelPresentacion.setBounds(0,0,ventana.getWidth(), ventana.getHeight()); 
          //Lo vamos a poner en la posición (0,0) es decir en la esquina superior izq de nuestra ventana y el panel va
          // a ser del tamaño de la ventana (tomamos el ancho y alto de la ventana principal)
        panelPresentacion.setVisible(true); //Para que el panel sea visible
        
        // Darle una imagen de fondo  al panel de presentación:
        fondoPresentacion = new JLabel(); // dandole memoria a la variable 
        fondoPresentacion.setBounds(0,0,ventana.getWidth(),ventana.getHeight()); // le damos posicion y el tamaño a la imagen que vamos a poner
         // entre el parentesis se da la ruta de donde se encuentra la imagen
        imagenFondoPres = new ImageIcon ("Imagen/fondo1.png");
        imagenFondoPres= new ImageIcon(imagenFondoPres.getImage().getScaledInstance
            (ventana.getWidth(), ventana.getHeight(),Image.SCALE_DEFAULT));
        // (getImage: es para coger la imagen) (.getScaleInstance: le vamos a dar una nueva escala) (ventana.getWidth: del tamaño/marco de la ventana) (Image.SCALE_DEFAULT)
        // Ayuda a adaptar el tamaño de la imagen a nuestro marco
        fondoPresentacion.setIcon(imagenFondoPres); //le agregamos la imagel al Label
        fondoPresentacion.setVisible(true); //Para poder ver la imagen
        panelPresentacion.add(fondoPresentacion,0); //Agregamos la imagen a la posicion cero
        
        // Darle memoria al boton y hacer el boton:
        iniciar = new JButton("Iniciar"); //Dentro del parentesis se pone el texto que va a contener el boton
        iniciar.setBounds(ventana.getWidth()-120, 20, 100, 30); // ancho de 50 y altura de 30. se coloca en la posición 
                                                                           // 20 y en "x" va a tomar el ancho de la ventana menos lo que
                                                                           // pesa el boton más 20
        iniciar.setVisible(true); //Para que el boton sea visible
        iniciar.setBackground(Color.white);
        //Aqui estamos montandolo encima de nuestro marco y lo colocamos como una capa, va a ser la parte de abajo
        panelPresentacion.add(iniciar,0); //Debo agregar los componenetes al panel, no la a ventana
        
        iniciar.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                menu();
            }});
        ventana.add(panelPresentacion);
        ventana.setVisible(true);
        
    }

public void menu () {
        // Creamos un panel del tamaño de la ventana
        panelPresentacion.setVisible(false); //Cuando se llame nuestro panel de menu (cuando llamemos esta funcion
                                                  //debemos decir panelPresentacion.setVisible(false), vamos a ocultar nuestro panel primero
                                                  // Estamos cambiando de capa, cambiamos la de presentacion por la de menu
        panelMenu = new JPanel();
        panelMenu.setLayout(null);
        panelMenu.setBounds(0,0, ventana.getWidth(),ventana.getHeight());
        panelMenu.setVisible(true);
        //
        fondoMenu = new JLabel();
        fondoMenu.setBounds(0,0, ventana.getWidth(),ventana.getHeight());
        imagenFondoMenu = new ImageIcon ("Imagen/camino.png");
        imagenFondoMenu = new ImageIcon (imagenFondoMenu.getImage().getScaledInstance(ventana.getWidth(), ventana.getHeight(), Image.SCALE_DEFAULT));
        fondoMenu.setIcon(imagenFondoMenu);
        fondoMenu.setVisible(true);
        panelMenu.add(fondoMenu,0);
                    
            ventana.add(panelMenu);
        } 

    //Panel de puntos Torre
    JPanel panelPuntos = new JPanel();
    panelPuntos.setBounds(0,0,200,50);
    panelPuntos.setBackground(Color.white);

    //Etiqueta "Puntos: "
    JLabel puntosLabel = new JLabel("Puntos: ");
    puntosLabel.setBounds(10,10,80,30);
    puntosLabel.setFont(new Font("Arial", Font.PLAIN, 20));

    //Etiqueta para mostrar la cantidad de puntos
    JLabel puntosValorLabel = new JLabel("0");
    puntosValorLabel.setBounds(100,10,80,30);
    puntosValorLabel.setFont(new Font("Arial", Font.PLAIN, 20));

    //Agregar las etiquetas al panel de puntos
    panelPuntos.add(puntosLabel);
    panelPuntos.add(puntosValorLabel);

    //Agregar el panel de puntos al marco de la aplicación
    ventana.add(panelPuntos);
    
    public void actualizarPuntos(int puntos) {
    puntosValorLabel.setText(Integer.toString(puntos));
    }

}
