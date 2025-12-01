package projekpbo;

public class Film implements CsvConverter {
    // ENCAPSULATION: Field private
    private String judul;
    private int harga;
    private Studio studio; // AGREGASI

    public Film(String judul, int harga, Studio studio) {
        this.judul = judul;
        this.harga = harga;
        this.studio = studio; 
    }
    
    // Getter methods
    public String getJudul() { return judul; }
    public int getHarga() { return harga; }
    public Studio getStudio() { return studio; }

    @Override
    public String toCsv() {
        return judul + ";" + harga + ";" + studio.toCsv();
    }
}