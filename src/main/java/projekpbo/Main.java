package projekpbo;

import java.io.*;
import java.util.*;

public class Main {
    static final String FILE_USERS = "users.csv";
    static final String FILE_FILMS = "films.csv";
    static final String FILE_TICKETS = "tickets.csv";
    
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<User> listAkun = new ArrayList<>();
    static ArrayList<Film> listFilm = new ArrayList<>();

    public static void main(String[] args) {
        loadAllData();

        if(listAkun.isEmpty()) {
            listAkun.add(new Admin("admin", "admin"));
            listAkun.add(new Pelanggan("user", "user"));
            saveAllData();
        }

        while(true) {
            System.out.println("\n=== BIOSKOP (COMPOSITION & AGGREGATION) ===");
            System.out.println("1. Login");
            System.out.println("2. Keluar & Simpan");
            System.out.print("Pilih: ");
            String menu = scanner.nextLine();

            if(menu.equals("1")) processLogin();
            else if(menu.equals("2")) {
                saveAllData();
                System.out.println("Data tersimpan. Bye!");
                break;
            }
        }
    }

    static void processLogin() {
        System.out.print("User: "); String u = scanner.nextLine();
        System.out.print("Pass: "); String p = scanner.nextLine();
        
        User userAktif = null;
        for(User a : listAkun) {
            if(a.login(u, p)) { userAktif = a; break; }
        }

        if(userAktif != null) {
            System.out.println("Login Sukses: " + userAktif.getUsername());
            if(userAktif instanceof Admin) menuAdmin((Admin) userAktif);
            else if(userAktif instanceof Pelanggan) menuCustomer((Pelanggan) userAktif);
        } else {
            System.out.println("Login Gagal.");
        }
    }

    static void menuAdmin(Admin admin) {
        while(true) {
            System.out.println("\n[ ADMIN ] \n1. Tambah Film  \n2. Logout");
            if(scanner.nextLine().equals("1")) {
                admin.tambahFilm(listFilm, scanner);
                saveAllData();
            } else break;
        }
    }

    static void menuCustomer(Pelanggan p) {
        while(true) {
            System.out.println("\n[ PELANGGAN ] \n1. Booking \n2. Lihat Tiket \n3. Logout");
            String inp = scanner.nextLine();
            if(inp.equals("1")) {
                if(listFilm.isEmpty()) { 
                    System.out.println("Belum ada film."); continue; 
                }
                for(int i=0; i<listFilm.size(); i++) 
                    System.out.println(1+i + ". " + listFilm.get(i).judul + " (" + listFilm.get(i).studio.nama + ")");
                
                try {
                    System.out.print("Pilih Index: ");
                    int idx = Integer.parseInt(scanner.nextLine());
                    bookingLogic(p, listFilm.get(idx));
                } catch(Exception e) { System.out.println("Input salah."); }
            } 
            else if(inp.equals("2")) p.lihatTiket();
            else break;
        }
    }

    static void bookingLogic(Pelanggan p, Film film) {
        film.studio.tampilkanDenah(); // Delegasi ke Studio
        System.out.print("Baris (A..): "); String bStr = scanner.nextLine().toUpperCase();
        System.out.print("Kolom (1..): "); String kStr = scanner.nextLine();
        
        try {
            int b = bStr.charAt(0) - 'A';
            int k = Integer.parseInt(kStr) - 1;
            
            if(film.studio.bookKursi(b, k)) {
                System.out.println("Booking Berhasil!");
                Tiket t = new Tiket(null, film.judul, bStr+kStr, film.harga, 0);
                p.simpanTiket(t);
                saveAllData();
            } else System.out.println("Kursi penuh/terisi.");
        } catch(Exception e) { System.out.println("Input salah."); }
    }

    // --- CSV FILE HANDLING ---

    static void saveAllData() {
        try {
            PrintWriter pwUser = new PrintWriter(new FileWriter(FILE_USERS));
            for(User a : listAkun) {
                String role = (a instanceof Admin) ? "ADMIN" : "CUST";
                pwUser.println(role + ";" + a.getUsername() + ";" + a.getPassword());
            }
            pwUser.close();

            PrintWriter pwFilm = new PrintWriter(new FileWriter(FILE_FILMS));
            for(Film f : listFilm) {
                pwFilm.println(f.toCSV());
            }
            pwFilm.close();

            PrintWriter pwTkt = new PrintWriter(new FileWriter(FILE_TICKETS));
            for(User a : listAkun) {
                if(a instanceof Pelanggan) {
                    Pelanggan c = (Pelanggan) a;
                    for(Tiket t : c.getDompet()) {
                        pwTkt.println(c.getUsername() + ";" + t.toCSV());
                    }
                }
            }
            pwTkt.close();

        } catch (IOException e) {
            System.out.println("Gagal save CSV: " + e.getMessage());
        }
    }

    static void loadAllData() {
        listAkun.clear(); listFilm.clear();
        String line;
        try {
            File fUser = new File(FILE_USERS);
            if(fUser.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(fUser));
                while((line = br.readLine()) != null) {
                    String[] data = line.split(";");
                    if(data[0].equals("ADMIN")) listAkun.add(new Admin(data[1], data[2]));
                    else listAkun.add(new Pelanggan(data[1], data[2]));
                }
                br.close();
            }

            File fFilm = new File(FILE_FILMS);
            if(fFilm.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(fFilm));
                while((line = br.readLine()) != null) {
                    String[] data = line.split(";");
                    // Rekonstruksi Komposisi & Agregasi saat Load
                    // Buat Studio dulu
                    Studio s = new Studio(data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4]));
                    s.loadKursiFromString(data[5]); 
                    // Lalu pasang ke Film (Agregasi)
                    listFilm.add(new Film(data[0], Integer.parseInt(data[1]), s));
                }
                br.close();
            }

            File fTkt = new File(FILE_TICKETS);
            if(fTkt.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(fTkt));
                while((line = br.readLine()) != null) {
                    String[] data = line.split(";");
                    Tiket t = new Tiket(data[1], data[2], data[3], Integer.parseInt(data[4]), Long.parseLong(data[5]));
                    for(User a : listAkun) {
                        if(a.getUsername().equals(data[0]) && a instanceof Pelanggan) {
                            ((Pelanggan)a).simpanTiket(t);
                            break;
                        }
                    }
                }
                br.close();
            }
        } catch (Exception e) { System.out.println("Load Error."); }
    }
}