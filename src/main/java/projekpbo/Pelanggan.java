package projekpbo;

import java.util.ArrayList;

public class Pelanggan extends User {
    private ArrayList<Tiket> dompetTiket = new ArrayList<>();

    public Pelanggan(String u, String p) { 
        super(u, p); 
    }

    // @Override
    // public String toCsv() {
    //     return "CUST;" + getUsername() + ";" + getPassword();
    // }

    // @Override
    // public void tampilkanMenu(Scanner scan) {
    //     System.out.println("\n[ MENU CUSTOMER ]");
    //     System.out.println("1. Booking Tiket");
    //     System.out.println("2. Lihat Tiket");
    //     System.out.println("3. Logout");
    // }

    public void simpanTiket(Tiket t) { 
        dompetTiket.add(t); 
    }

    public ArrayList<Tiket> getDompet () {
        return dompetTiket;
    }

    public void lihatTiket() {
        System.out.println("\n--- Tiket Saya ---");
        if(dompetTiket.isEmpty()) System.out.println("(Belum ada tiket)");
        else for(Tiket t : dompetTiket) System.out.println(t.toString());
    }
}
