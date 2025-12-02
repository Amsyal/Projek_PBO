package projekpbo;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {
    
    public Admin(String u, String p) { 
        super(u, p); 
    }

    @Override
    public String toCsv() {
        return "ADMIN;" + getUsername() + ";" + getPassword();
    }

    @Override
    public void tampilkanMenu(Scanner scanner, ArrayList<Film> listFilm) {
        while(true) {
            System.out.println("\n[ ADMIN PANEL ]");
            System.out.println("1. Tambah Film");
            System.out.println("2. Logout");
            System.out.print("Pilih: ");
            String pilihan = scanner.nextLine();

            if(pilihan.equals("1")) {
                this.tambahFilm(listFilm, scanner);
            } else if (pilihan.equals("2")) {
                break;
            }
        }
    }

    private void tambahFilm(ArrayList<Film> listFilm, Scanner scan) {
        try {
            System.out.print("Judul : "); String judul = scan.nextLine();
            System.out.print("Harga : "); int harga = Integer.parseInt(scan.nextLine());
            System.out.print("Studio: "); String studio = scan.nextLine();
            System.out.print("Jam: "); String jam = scan.nextLine();
            System.out.print("Tanggal: "); String tgl = scan.nextLine();
            System.out.print("Baris : "); int b = Integer.parseInt(scan.nextLine());
            System.out.print("Kolom : "); int k = Integer.parseInt(scan.nextLine());

            Studio studioBaru = new Studio(studio, b, k);
            listFilm.add(new Film(judul, harga, studioBaru, tgl, jam));
            System.out.println("Film Berhasil Disimpan!");
        } catch (NumberFormatException e) {
            System.out.println("Input angka tidak valid.");
        }
    }

    private void hapusFilm(ArrayList<Film> listFilm, Scanner scan) {
        try {
            System.out.println("Judul: "); String judul = scan.nextLine();

            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}