package TrenTuristico;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author merimendez
 */
public class Pasajero implements Runnable {

    TrenTuristico tren;

    public Pasajero(TrenTuristico t) {
        this.tren = t;
    }

    public void hacerRecorrido(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Pasajero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run() {
        tren.comprarTicket();
        tren.recibirTicket();
        System.out.println("El " + Thread.currentThread().getName() + " compro un ticket");
        tren.subirTren();
        hacerRecorrido();
        tren.bajarTren();
    }
}
