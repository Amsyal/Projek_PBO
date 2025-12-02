package projekpbo;

import java.util.Scanner;
import java.util.ArrayList;

public abstract class User implements CsvConverter {
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

    public abstract void tampilkanMenu(Scanner scanner, ArrayList<Film> listFilm);
}