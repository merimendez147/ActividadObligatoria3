package TrenTuristico;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author merimendez
 */
public class VendedorTickets  implements Runnable {

    TrenTuristico tren;
    int P;

    public VendedorTickets(TrenTuristico t, int cantP) {
        this.tren = t;
        this.P=cantP;
    }
    
    private void registrarTicket() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(VendedorTickets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i <P; i++) {
            tren.venderTicket();
            //System.out.println("El " + Thread.currentThread().getName() + " vende un ticket");
            registrarTicket();
        }
    }
    
}
