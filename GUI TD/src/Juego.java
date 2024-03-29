/* Movimientos del juego*/

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public abstract class Juego extends JFrame implements ActionListener {

    // MARCO DE LA APP
    JFrame ventana;

    // PANEL DE PRESENTACIÓN
    JPanel panelPresentacion;
    JButton iniciar;
    JLabel fondoPresentacion;
    ImageIcon imagenFondoPres;

    // PANEL MENU: VA SOBRE EL PANEL DE PRESENTACIÓN
    JPanel panelMenu;
    JLabel fondoMenu;
    ImageIcon imagenFondoMenu;
    String jugador;
    JLabel nombre;
    JLabel cpu;

    // COMBO BOX
    private JLabel label1, label2;
    private JComboBox<String> combo1, combo2;
    private JButton boton1;

    // PANEL JUEGO
    static JPanel panelJuego;
    JLabel fondoJuego;
    ImageIcon imagenFondoJuego;
    JLabel ronda;
  
    
    // TORRE
    JLabel puntajeTorre1;
    JLabel puntajeTorre2;

    // TIEMPO

    
    public Juego() {
        //CREAR EL MARCO DE LA APP
        ventana = new JFrame("Tower Defense");
        ventana.setSize(1200, 750); // Tamaño de la ventana
        /*Permite poner botones e imagenes en la posición que queramos, de lo
        contrario el sistema lo pone por defecto
         */
        ventana.setLayout(null);
        //Colocar la ventana principal en el medio
        ventana.setLocationRelativeTo(null);
        // Permite que cuando le demos "x" la app termine
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Es para desactivar el boton de agradar la pestaña(el amarillo)
        ventana.setResizable(false);

        // CREAR PANEL DE PRESENTACIÓN
        panelPresentacion = new JPanel();
        panelPresentacion.setLayout(null);
        /*Lo vamos a poner en la posición (0,0) es decir en la esquina superior 
        izq de nuestra ventana y el panel va a ser del tamaño de la ventana 
        (tomamos el ancho y alto de la ventana principal)*/
        panelPresentacion.setBounds(0, 0, ventana.getWidth(),
                ventana.getHeight());
        panelPresentacion.setVisible(true); //Para que el panel sea visible

        // IMAGEN DE FONDO AL PANEL DE PRESENTACIÓN
        fondoPresentacion = new JLabel();
        fondoPresentacion.setBounds(0, 0, ventana.getWidth(),
                ventana.getHeight());
        imagenFondoPres = new ImageIcon("Imagen/fondo1.png");
        imagenFondoPres = new ImageIcon(imagenFondoPres.getImage().getScaledInstance
            (ventana.getWidth(), ventana.getHeight(), Image.SCALE_DEFAULT));
        fondoPresentacion.setIcon(imagenFondoPres);
        fondoPresentacion.setVisible(true);
        //Agregamos la imagen a la posicion cero
        panelPresentacion.add(fondoPresentacion, 0);

        //Dentro del parentesis se pone el texto que va a contener el boton
        iniciar = new JButton("Iniciar");
        iniciar.setBounds(ventana.getWidth() - 120, 20, 90, 40);
        iniciar.setVisible(true); //Para que el boton sea visible
        /*Aqui estamos montandolo encima de nuestro marco y lo colocamos como una capa, va a ser la parte de abajo
        Debo agregar los componenetes al panel, no la a ventana*/
        panelPresentacion.add(iniciar, 0);

        //MENU, darle memoria al boton
        boton1 = new JButton();
        
        // El boton por ahora no hace nada por tanto se crea un evento del boton
        iniciar.addMouseListener(new MouseAdapter() {
            //Función del mouse
            public void mousePressed(MouseEvent e) {
                // Al dale click a iniciar debería aparecer el panel de menu
                menu();
            }
        });
        
           
        
 
        
        ventana.add(panelPresentacion);
        ventana.setVisible(true);
    }

    public void menu() {
        /*
        Al llamar el método menú, lo primero que va a hacer es ocultar el panelPresentación
        por eso el "false" dentro del paréntesis
         */
        panelPresentacion.setVisible(false);

        // CREAMOS UN PANEL DEL TAMAÑO DE LA VENTNA
        panelMenu = new JPanel();
        panelMenu.setLayout(null);
        panelMenu.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
        panelMenu.setVisible(true);

        // FONDO DEL PANEL
        fondoMenu = new JLabel();
        fondoMenu.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
        imagenFondoMenu = new ImageIcon("Imagen/camino.png");
        imagenFondoMenu = new ImageIcon(imagenFondoMenu.getImage().getScaledInstance(ventana.getWidth(), ventana.getHeight(), Image.SCALE_DEFAULT));
        fondoMenu.setIcon(imagenFondoMenu);
        fondoMenu.setVisible(true);
        panelMenu.add(fondoMenu, 0);

        // BOTÓN CONTINUAR
        boton1.setText("Continuar");
        boton1.setBounds(110, 675, 100, 40);
        boton1.setVisible(true);
        boton1.setBackground(Color.white);
        panelMenu.add(boton1, 0);

        // TÍTULO "PERSONAJES"
        label1 = new JLabel("PERSONAJES");
        label1.setBounds(35, 610, 100, 30);
        label1.setVisible(true);
        panelMenu.add(label1, 0);

        // COMBO BOX DE PERSONAJES
        combo1 = new JComboBox<String>();
        combo1.setBounds(125, 610, 110, 30);
        combo1.addItem("Arquero");
        combo1.addItem("Caballero");
        combo1.addItem("Mago");
        combo1.setVisible(true);
        panelMenu.add(combo1, 0);

        // TÍTULO "CAMINOS"
        label2 = new JLabel("CAMINOS");
        label2.setBounds(35, 640, 100, 30);
        label2.setVisible(true);
        panelMenu.add(label2, 0);

        // COMBO BOX DE CAMINOS
        combo2 = new JComboBox<String>();
        combo2.setBounds(125, 640, 155, 30);
        combo2.addItem("Camino superior");
        combo2.addItem("Camino inferior");
        combo2.setVisible(true);
        panelMenu.add(combo2, 0);

        // AGREGAR EL PANEL MENU A LA VENTANA
        ventana.add(panelMenu);
        ventana.setVisible(true);

        // Cuando le damos click al boton1 (continuar) pasa al método juego
        boton1.addMouseListener(new MouseAdapter() {
            //Función del mouse
            public void mouseReleased(MouseEvent e) {
                // Al dale click a iniciar debería aparecer el panel de menu
                jugador = JOptionPane.showInputDialog(ventana, "Nombre del jugador:",
                        "Escriba el nombre aqui");

                while (jugador == null || jugador.compareTo("Escriba el nombre aqui") == 0
                        || jugador.compareTo("") == 0) {
                    jugador = JOptionPane.showInputDialog(ventana, "Nombre del jugador:",
                            "Escriba el nombre aqui");
                }
                juego();
            }
        });
    }
    
    
    public void juego() {
        // CREAR OBJETOS TORRE Y CRONOMETRO
        Torre torre1 = new Torre();
        Torre torre2 = new Torre();
        Cronometro crono = new Cronometro();

        panelMenu.setVisible(false);

        // CREAMOS UN PANEL DEL TAMAÑO DE LA VENTNA
        panelJuego = new JPanel();
        panelJuego.setLayout(null);
        panelJuego.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
        panelJuego.setVisible(true);

        // FONDE DEL JUEGO
        fondoJuego = new JLabel();
        fondoJuego.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
        imagenFondoJuego = new ImageIcon("Imagen/camino.png");
        imagenFondoJuego = new ImageIcon(imagenFondoMenu.getImage().getScaledInstance(ventana.getWidth(), ventana.getHeight(), Image.SCALE_DEFAULT));
        fondoJuego.setIcon(imagenFondoJuego);
        fondoJuego.setVisible(true);
        panelJuego.add(fondoJuego, 0);

        // PUNTOS DE LAS TORRES
        // TORRE 1
        puntajeTorre1 = new JLabel("Puntos Torre: " + torre1.getVida());
        puntajeTorre1.setBounds(25, 200, 200, 30);
        puntajeTorre1.setFont(new Font("SANS_SERIF", Font.BOLD, 10));
        panelJuego.add(puntajeTorre1, 0);
        
        //NOMBRE DEL USUARIO EN TORRE 1
        nombre = new JLabel ("Jugador: "+ jugador);
        nombre.setBounds (20, 175, 400, 30);
        nombre.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        panelJuego.add(nombre, 0);
        
        // TORRE 2
        puntajeTorre2 = new JLabel("Puntos Torre: " + torre2.getVida());
        puntajeTorre2.setBounds(1075, 200, 200, 30);
        puntajeTorre2.setFont(new Font("SANS_SERIF", Font.BOLD, 10));
        panelJuego.add(puntajeTorre2, 0);
        
        //NOMBRE DEL CPU EN TORRE 2
        cpu = new JLabel("CPU");
        cpu.setBounds(1107, 175, 70, 30);
        cpu.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        panelJuego.add(cpu, 0);
        
        // Nombre Ronda
        ronda = new JLabel ("Ronda: "); // Agregar el resto del codigo cuando se haga el merge con el proyecto principal
        ronda.setBounds(550, 70 , 100 , 30);
        ronda.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        ronda.setHorizontalAlignment(JLabel.CENTER);
        ronda.setForeground(Color.BLACK);
        ronda.setBackground(Color.CYAN);
        ronda.setOpaque(true);
        ronda.setVisible(true);
        panelJuego.add(ronda , 0);
        
        
        //Etiqueta donde se colocara el tiempo 
        panelJuego.add(crono.getTiempo(), 0);

        
        ventana.add(panelJuego);
        ventana.setVisible(true);
    }
    
       
     
    public static void main(String[] ar) {
        Juego formulario1 = new Juego() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
    }
}
