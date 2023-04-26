/* Movimientos del juego*/

import java.awt.BorderLayout;
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
import javax.swing.Timer;

public abstract class Juego extends JFrame implements ActionListener {
// MARCO DE LA APP
    static JFrame ventana;

    // PANEL DE PRESENTACIÓN
    JPanel panelPresentacion;
    JButton iniciar;
    JLabel fondoPresentacion;
    ImageIcon imagenFondoPres;

    // PANEL MENU: VA SOBRE EL PANEL DE PRESENTACIÓN
    JPanel panelMenu;
    JLabel fondoMenu;
    ImageIcon imagenFondoMenu;

    // COMBO BOX
    private JLabel label1, label2;
    private JComboBox<String> combo1, combo2;
    private JButton boton1;

    // PANEL JUEGO
    JPanel panelJuego;
    JLabel fondoJuego;
    ImageIcon imagenFondoJuego;

    // CRONOMETRO
    JLabel tiempo;
    Thread hilo;
    boolean cronometroActivo;
    //Cronometro n = new Cronometro();
    
    // Para el label de la torre
    private JLabel puntosValorLabel;
    
   
    
    public Juego () {
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

        // DARLE UNA IMAGEN DE FONDO AL PANEL DE PRESENTACIÓN
        fondoPresentacion = new JLabel();
        /* le damos posicion y el tamaño a la imagen que vamos a poner
        entre el parentesis se da la ruta de donde se encuentra la imagen */
        fondoPresentacion.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
        imagenFondoPres = new ImageIcon("Imagen/fondo1.png");
        /* (getImage: es para coger la imagen) 
        (.getScaleInstance: le vamos a dar una nueva escala) 
        (ventana.getWidth: del tamaño/marco de la ventana) 
        (Image.SCALE_DEFAULT): Ayuda a adaptar el tamaño de la imagen a nuestro marco */
        imagenFondoPres = new ImageIcon(imagenFondoPres.getImage().getScaledInstance(ventana.getWidth(), ventana.getHeight(), Image.SCALE_DEFAULT));

        //le agregamos la imagen al Label
        fondoPresentacion.setIcon(imagenFondoPres);
        //Para poder ver la imagen
        fondoPresentacion.setVisible(true);
        //Agregamos la imagen a la posicion cero
        panelPresentacion.add(fondoPresentacion, 0);

        //Dentro del parentesis se pone el texto que va a contener el boton
        iniciar = new JButton("Iniciar");
        iniciar.setBounds(ventana.getWidth() - 120, 20, 90, 40);
        iniciar.setVisible(true); //Para que el boton sea visible
        // iniciar.setBackground(Color.white);
        //Aqui estamos montandolo encima de nuestro marco y lo colocamos como una capa, va a ser la parte de abajo
        //Debo agregar los componenetes al panel, no la a ventana
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
        //
        fondoMenu = new JLabel();
        fondoMenu.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
        imagenFondoMenu = new ImageIcon("Imagen/camino.png");
        imagenFondoMenu = new ImageIcon(imagenFondoMenu.getImage().getScaledInstance(ventana.getWidth(), ventana.getHeight(), Image.SCALE_DEFAULT));
        fondoMenu.setIcon(imagenFondoMenu);
        fondoMenu.setVisible(true);
        panelMenu.add(fondoMenu, 0);

        //NUEVO
        boton1.setText("Continuar");
        boton1.setBounds(110, 675, 100, 40);
        boton1.setVisible(true);
        boton1.setBackground(Color.white);
        panelMenu.add(boton1, 0);

        label1 = new JLabel("PERSONAJES");
        label1.setBounds(35, 610, 100, 30);
        label1.setVisible(true);
        panelMenu.add(label1, 0);

        combo1 = new JComboBox<String>();
        combo1.setBounds(125, 610, 110, 30);
        combo1.addItem("Arquero");
        combo1.addItem("Caballero");
        combo1.addItem("Mago");
        combo1.setVisible(true);
        panelMenu.add(combo1, 0);

        label2 = new JLabel("CAMINOS");
        label2.setBounds(35, 640, 100, 30);
        label2.setVisible(true);
        panelMenu.add(label2, 0);

        combo2 = new JComboBox<String>();
        combo2.setBounds(125, 640, 155, 30);
        combo2.addItem("Camino superior");
        combo2.addItem("Camino inferior");
        combo2.setVisible(true);
        panelMenu.add(combo2, 0);

        ventana.add(panelMenu);
        ventana.setVisible(true);

        boton1.addMouseListener(new MouseAdapter() {
            //Función del mouse
            public void mouseReleased(MouseEvent e) {
                // Al dale click a iniciar debería aparecer el panel de menu
                juego();
                iniciarCronometro();
                run();
            }
        });
    }

    public void juego() {
        panelMenu.setVisible(false);

        // CREAMOS UN PANEL DEL TAMAÑO DE LA VENTNA
        panelJuego = new JPanel();
        panelJuego.setLayout(null);
        panelJuego.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
        panelJuego.setVisible(true);
        //
        fondoJuego = new JLabel();
        fondoJuego.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
        imagenFondoJuego = new ImageIcon("Imagen/camino.png");
        imagenFondoJuego = new ImageIcon(imagenFondoMenu.getImage().getScaledInstance(ventana.getWidth(), ventana.getHeight(), Image.SCALE_DEFAULT));
        fondoJuego.setIcon(imagenFondoJuego);
        fondoJuego.setVisible(true);
        panelJuego.add(fondoJuego, 0);

        //Etiqueta donde se colocara el tiempo 
        tiempo = new JLabel("00:00:000");
        tiempo.setBounds(500, 20, 200, 20);
        tiempo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20)); // tamaño de las letras y tipo de letra
        tiempo.setHorizontalAlignment(JLabel.CENTER); // el numero este en el centro
        tiempo.setForeground(Color.BLUE); //Los números se ponen azul
        tiempo.setBackground(Color.WHITE);
        tiempo.setOpaque(true); // permite que el setBackground se ponga blanco
        tiempo.setVisible(true);
        panelJuego.add(tiempo, 0);
        

        ventana.add(panelJuego);
        ventana.setVisible(true);

    }
    // CRONOMETRO
    public void run() {
        Integer minutos = 0, segundos = 0, milesimas = 0;
        //min es minutos, seg es segundos y mil es milesimas de segundo
        String min = "", seg = "", mil = "";
        try {
            //Mientras cronometroActivo sea verdadero entonces seguira
            //aumentando el tiempo
            while (cronometroActivo) {
                Thread.sleep(4);
                //Incrementamos 4 milesimas de segundo
                milesimas += 4;

                //Cuando llega a 1000 osea 1 segundo aumenta 1 segundo
                //y las milesimas de segundo de nuevo a 0
                if (milesimas == 1000) {
                    milesimas = 0;
                    segundos += 1;
                    //Si los segundos llegan a 60 entonces aumenta 1 los minutos
                    //y los segundos vuelven a 0
                    if (segundos == 60) {
                        segundos = 0;
                        minutos++;
                    }
                }

                //Esto solamente es estetica para que siempre este en formato
                //00:00:000
                if (minutos < 10) {
                    min = "0" + minutos;
                } else {
                    min = minutos.toString();
                }
                if (segundos < 10) {
                    seg = "0" + segundos;
                } else {
                    seg = segundos.toString();
                }

                if (milesimas < 10) {
                    mil = "00" + milesimas;
                } else if (milesimas < 100) {
                    mil = "0" + milesimas;
                } else {
                    mil = milesimas.toString();
                }

                //Colocamos en la etiqueta la informacion
                tiempo.setText(min + ":" + seg + ":" + mil);
            }
        } catch (Exception e) {
        }
        //Cuando se reincie se coloca nuevamente en 00:00:000
        tiempo.setText("00:00:000");
    }
    
    public void iniciarCronometro() {
        cronometroActivo = true;
        hilo = new Thread( (Runnable) this);
        hilo.start();
    }

    //Esto es para parar el cronometro
    public void pararCronometro(){
        cronometroActivo = false;
    }
    
// Panel de puntos Torre
public void puntosTorre(){
   
    JPanel panelPuntos = new JPanel();
    panelPuntos.setBounds(0,0,200,50);
    panelPuntos.setBackground(Color.white);

    //Etiqueta "Puntos: "
    JLabel puntosLabel = new JLabel("Puntos Torre: ");
    puntosLabel.setBounds(10,10,80,30);
    puntosLabel.setFont(new Font("SANS_SERIF", Font.BOLD, 20));

    //Etiqueta para mostrar la cantidad de puntos
    //JLabel puntosValorLabel = new JLabel("0");
    puntosValorLabel.setBounds(100,10,80,30);
    puntosValorLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 20));

    //Agregar las etiquetas al panel de puntos
    panelPuntos.add(puntosLabel);
    panelPuntos.add(puntosValorLabel);

    //Agregar el panel de puntos al marco de la aplicación
    ventana.add(panelPuntos);
    
}
    
public void actualizarPuntos(int puntos) {
    puntosValorLabel.setText(Integer.toString(puntos));
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
