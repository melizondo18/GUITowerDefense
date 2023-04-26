/* Clase Torre - copia del Proyecto Tower Defense*/

public class Torre {
    // Cantidad de vidas total de la torre
    private double vida = 10;
    
    // Método para restar las vidas según el daño causado
    public void danio (double danio) {
        vida = vida - danio;
    }
        
    // Método para comprobar si la torre ha sido destruido
    public boolean isDestruido(){
        if (vida <= 0){     // Cuando el castillo ya no tenga vidas,             
            return true;    // devuelve que destruido es verdadero
        } 
        // Si la torre todavía tiene vidas, devuelve falso
        return false;
    }
    
    // Gets y sets
    
    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }
    
}
