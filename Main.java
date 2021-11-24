package TrenTuristico;

/**
 *
 * @author merimendez
 */
public class Main {
    public static void main(String[] args) {
        int P=15; //cantidad de pasajeros
        int C=5; // máximo de  pasajeros en el carrito        
        TrenTuristico tren = new TrenTuristico(C,P);
        Thread vendedor = new Thread(new VendedorTickets(tren, P));
        vendedor.setName("Vendedor de Tickets");
        vendedor.start();
        Thread[] pasajeros = new Thread[P];
        for (int i = 0; i < pasajeros.length; i++) {
            pasajeros[i] = new Thread(new Pasajero(tren));
            pasajeros[i].setName("Pasajero" + i);
        }
        for (Thread pasajero : pasajeros) {
            pasajero.start();
        }

    }  
}
