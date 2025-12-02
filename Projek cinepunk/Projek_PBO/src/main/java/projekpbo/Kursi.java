package projekpbo;

public class Kursi {
    private String nomor;    // Misal: "A1"
    private boolean isTerisi;

    public Kursi(String nomor) {
        this.nomor = nomor;
        this.isTerisi = false;
    }

    public String getNomor() { 
        return nomor; 
    }
    
    public boolean isTerisi() { 
        return isTerisi; 
    }
    
    public void setTerisi(boolean status) { 
        this.isTerisi = status; 

    }
}