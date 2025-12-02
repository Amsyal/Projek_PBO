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
            System.out.println("--------------------------------");
            System.out.println("1. Tambah Film");
            System.out.println("2. Lihat Film");
            System.out.println("3. Hapus Film");
            System.out.println("4. Logout");
            System.out.println("--------------------------------");
            System.out.print("Pilih: ");
            String pilihan = scanner.nextLine();

            if(pilihan.equals("1")) {
                this.tambahFilm(listFilm, scanner);
            } else if (pilihan.equals("2")) {
                lihatFilm(listFilm);
            } else if (pilihan.equals("3")) {
                hapusFilm(listFilm, scanner);            
            } else if (pilihan.equals("4")) {
                break;
            } else {
                System.out.println("Pilihan tidak valid.");
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
    
    public static void lihatFilm(ArrayList<Film> daftarFilm) {
    System.out.println("\n=== DAFTAR FILM TERSEDIA ===");

    if (daftarFilm.isEmpty()) {
        System.out.println("Belum ada data film.");
        return; 
    }

    for (int i = 0; i < daftarFilm.size(); i++) {
        Film film = daftarFilm.get(i);
        
        System.out.println("--------------------------------");
        System.out.println("No. Film : " + (i + 1)); 
        System.out.println("Judul    : " + film.getJudul());
        System.out.println("Harga    : Rp " + film.getHarga());
        System.out.println("Jam      : Rp " + film.getJamTayang());
        System.out.println("Tanggal  : Rp " + film.getTglTayang());
        System.out.println("Studio   : " + film.getStudio().getNama());
    }
    System.out.println("--------------------------------");
}

    public static void hapusFilm(ArrayList<Film> daftarFilm, Scanner scanner) {
        System.out.println("\n--- HAPUS FILM ---");
        
        // 1. Cek apakah daftar kosong
        if (daftarFilm.isEmpty()) {
            System.out.println("Belum ada film yang tersedia untuk dihapus.");
            return;
        }

        // 2. Tampilkan daftar film agar Admin tahu nomor/indeks-nya
        System.out.println("Daftar Film Saat Ini:");
        for (int i = 0; i < daftarFilm.size(); i++) {
            // Mengasumsikan class Film punya method getJudul()
            System.out.println((i + 1) + ". " + daftarFilm.get(i).getJudul()); 
        }

        // 3. Minta input nomor film yang ingin dihapus
        System.out.print("Masukkan nomor film yang ingin dihapus (0 untuk batal): ");
        int nomor = 0;
        
        try {
            String input = scanner.nextLine();
            nomor = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println("Input harus berupa angka!");
            scanner.nextLine();
            return;
        }

        // 4. Logika penghapusan
        if (nomor == 0) {
            System.out.println("Penghapusan dibatalkan.");
        } else if (nomor > 0 && nomor <= daftarFilm.size()) {
            // Ambil nama film untuk konfirmasi (opsional)
            String judulTerhapus = daftarFilm.get(nomor - 1).getJudul();
            
            // HAPUS DARI LIST
            daftarFilm.remove(nomor - 1);
            
            System.out.println("Film \"" + judulTerhapus + "\" berhasil dihapus!");
        } else {
            System.out.println("Nomor tidak valid/tidak ditemukan.");
        }
    }

}