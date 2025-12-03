package projekpbo;

import java.util.ArrayList;
import java.util.Scanner;

public class Pelanggan extends User {
    private ArrayList<Tiket> dompetTiket = new ArrayList<>();

    public Pelanggan(String u, String p) { 
        super(u, p); 
    }

    public void simpanTiket(Tiket t) { 
        dompetTiket.add(t); 
    }
    
    public ArrayList<Tiket> getDompet() {
        return dompetTiket;
    }

    @Override
    public String toCsv() {
        return "CUST;" + getUsername() + ";" + getPassword();
    }

    @Override
    public void tampilkanMenu(Scanner scanner, ArrayList<Film> listFilm) {
        while(true) {
            System.out.println("\n[ CUSTOMER MENU ]");
            System.out.println("1. Booking Tiket");
            System.out.println("2. Lihat Tiket Saya");
            System.out.println("3. Logout");
            System.out.print("Pilih: ");
            String inp = scanner.nextLine();

            if(inp.equals("1")) {
                prosesBooking(scanner, listFilm);
            } else if(inp.equals("2")) {
                lihatTiket();
            } else {
                break;
            }
        }
    }

    private void prosesBooking(Scanner scanner, ArrayList<Film> listFilm) {
        if(listFilm.isEmpty()) { 
            System.out.println("Belum ada film yang tayang."); 
            return; 
        }
        
        System.out.println("--- DAFTAR FILM ---");
        for(int i=0; i<listFilm.size(); i++) {
            Film f = listFilm.get(i);
           
            System.out.println("--------------------------------");
            System.out.println("No. Film : " + (i + 1)); 
            System.out.println("Judul    : " + f.getJudul());
            System.out.println("Harga    : Rp " + f.getHarga());
            System.out.println("Jam      : " + f.getJamTayang());
            System.out.println("Tanggal  : " + f.getTglTayang());
            System.out.println("Studio   : " + f.getStudio().getNama());
            System.out.println("--------------------------------");
        }
        
        try {
            System.out.print("Pilih Nomor Film: ");
            int idx = Integer.parseInt(scanner.nextLine()) - 1;
            
            if(idx >= 0 && idx < listFilm.size()) {
                Film selectedFilm = listFilm.get(idx);
                bookingLogic(scanner, selectedFilm);
            } else {
                System.out.println("Film tidak ditemukan.");
            }
        } catch(Exception e) { 
            System.out.println("Input salah."); 
        }
    }

    private void bookingLogic(Scanner scanner, Film film) {
        film.getStudio().tampilkanDenah();
        System.out.print("Pilih Baris (A..): "); String bStr = scanner.nextLine().toUpperCase();
        System.out.print("Pilih Kolom (1..): "); String kStr = scanner.nextLine();
        
        try {
            int b = bStr.charAt(0) - 'A';
            int k = Integer.parseInt(kStr) - 1;
            
            if(film.getStudio().bookKursi(b, k)) {
                Tiket t = new Tiket(null, film.getJudul(), bStr+kStr, film.getHarga(), 0);
                this.simpanTiket(t);
                System.out.println("Booking Berhasil! Tiket tersimpan di dompet.");
            } else {
                System.out.println("Gagal: Kursi sudah terisi atau tidak valid.");
            }
        } catch(Exception e) { 
            System.out.println("Format input kursi salah."); 
        }
    }

    public void lihatTiket() {
        System.out.println("\n--- Tiket Saya ---");
        if(dompetTiket.isEmpty()) System.out.println("(Belum ada tiket)");
        else for(Tiket t : dompetTiket) System.out.println(t.toString());
    }
}