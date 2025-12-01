package projekpbo;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {
    public Admin(String u, String p) { 
        super(u, p); 
    }

    // @Override
    // public String toCsv() {
    //     return "ADMIN;" + getUsername() + ";" + getPassword();
    // }

    // @Override
    // public void tampilkanMenu(Scanner scan) {
    //     System.out.println("\n[ MENU ADMIN ]");
    //     System.out.println("1. Tambah Film");
    //     System.out.println("2. Logout");
    // }

    public void tambahFilm(ArrayList<Film> listFilm, Scanner scan) {
        System.out.print("Judul: "); String j = scan.nextLine();
        System.out.print("Harga: "); int h = Integer.parseInt(scan.nextLine());
        System.out.print("Studio: "); String s = scan.nextLine();
        System.out.print("Baris: "); int b = Integer.parseInt(scan.nextLine());
        System.out.print("Kolom: "); int k = Integer.parseInt(scan.nextLine());

        // Menggunakan Constructor Film & Studio
        listFilm.add(new Film(j, h, new Studio(s, b, k)));
        System.out.println("Film Tersimpan.");
    }
}
