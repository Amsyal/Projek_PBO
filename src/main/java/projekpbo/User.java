package projekpbo;

import java.util.Scanner;
import java.util.ArrayList;

// ABSTRACTION: User tidak bisa diinstansiasi langsung, harus via subclass
public abstract class User implements CsvConverter {
    // ENCAPSULATION: Access modifier private
    private String username;
    private String password;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    // POLYMORPHISM: Setiap role punya tampilan menu berbeda
    public abstract void tampilkanMenu(Scanner scanner, ArrayList<Film> listFilm);
}