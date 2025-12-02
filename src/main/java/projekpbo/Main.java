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
            saveAllData();
        }

        while(true) {
            System.out.println("\n=== CINEPUNK ===");
            System.out.println("1. Login");
            System.out.println("2. Registrasi");
            System.out.println("3. Keluar & Simpan");
            System.out.print("Pilih: ");
            String menu = scanner.nextLine();

            if(menu.equals("1")) {
                processLogin();
            } else if(menu.equals("2")) {
                proccessRegis();
            } else if(menu.equals("3")) {
                saveAllData();
                System.out.println("Terima kasih!");
                break;
            }
        }
    }

    static void processLogin() {
        System.out.print("Username: "); String u = scanner.nextLine();
        System.out.print("Password: "); String p = scanner.nextLine();
        
        User userAktif = null;
        for(User a : listAkun) {
            if(a.login(u, p)) { 
                userAktif = a; 
                break; 
            }
        }

        if(userAktif != null) {
            System.out.println("Selamat Datang, " + userAktif.getUsername());

            userAktif.tampilkanMenu(scanner, listFilm);
            
            saveAllData(); 
        } else {
            System.out.println("Username atau Password salah.");
        }
    }

    static void saveAllData() {
        try {
            PrintWriter pwUser = new PrintWriter(new FileWriter(FILE_USERS));
            for(User a : listAkun) pwUser.println(a.toCsv());
            pwUser.close();

            PrintWriter pwFilm = new PrintWriter(new FileWriter(FILE_FILMS));
            for(Film f : listFilm) pwFilm.println(f.toCsv());
            pwFilm.close();

            PrintWriter pwTkt = new PrintWriter(new FileWriter(FILE_TICKETS));
            for(User a : listAkun) {
                if(a instanceof Pelanggan) {
                    Pelanggan c = (Pelanggan) a;
                    for(Tiket t : c.getDompet()) {
                        pwTkt.println(c.getUsername() + ";" + t.toCsv());
                    }
                }
            }
            pwTkt.close();
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data: " + e.getMessage());
        }
    }

    static void proccessRegis() {
        System.out.println("---Daftar Akun---");
        System.out.println("Username: ");
        String newUser = scanner.nextLine();

        for (User u: listAkun) {
            if (u.getUsername().equals(newUser)) {
                System.out.println("User sudah ada.");
                return;
            }
        }

        System.out.println("Password: "); String pass = scanner.nextLine();

        listAkun.add(new Pelanggan(newUser, pass));
        saveAllData();
        System.out.println("Registrasi berhasil.");
    }

    static void loadAllData() {
        // listAkun.clear(); listFilm.clear();
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
                    Studio s = new Studio(data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4]));
                    s.loadKursiFromString(data[5]); 
                    listFilm.add(new Film(data[0], Integer.parseInt(data[1]), s, data[6], data[7]));
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
        } catch (Exception e) { 
            System.out.println("Terjadi kesalahan saat memuat data (file mungkin corrupt atau kosong)."); 
        }
    }
}