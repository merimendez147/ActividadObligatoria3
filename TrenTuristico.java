package TrenTuristico;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elina
 */
public class TrenTuristico {

    private final int capacidadTren; // máximo de  pasajeros en el carrito
    CyclicBarrier barreraEntrada;
    CyclicBarrier barreraSalida;
    private Semaphore vendedorTickets;
    private Semaphore mutexTicket;
    private Semaphore ticket;


    public TrenTuristico(int c, int p) {
        this.capacidadTren = c;
        barreraEntrada = new CyclicBarrier(capacidadTren);   
        barreraSalida = new CyclicBarrier(capacidadTren); 
        vendedorTickets = new Semaphore(0, true);
        mutexTicket = new Semaphore(1, true);//exclusion mutua
        ticket = new Semaphore(0, true);
    }

    public void comprarTicket() {
        try {
            mutexTicket.acquire();
            vendedorTickets.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(TrenTuristico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void venderTicket() {
        try {
            vendedorTickets.acquire();
            ticket.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(TrenTuristico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recibirTicket() {
        try {
            ticket.acquire();
            mutexTicket.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(TrenTuristico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       public void subirTren() {        
            try {              
                barreraEntrada.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(TrenTuristico.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BrokenBarrierException ex) {
                Logger.getLogger(TrenTuristico.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("El " + Thread.currentThread().getName() + " se subio al tren");
    }
    
       public void bajarTren(){
        try {
            barreraSalida.await();
            System.out.println("El " + Thread.currentThread().getName() + " se bajo del tren");
        } catch (InterruptedException ex) {
            Logger.getLogger(TrenTuristico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BrokenBarrierException ex) {
            Logger.getLogger(TrenTuristico.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
}
