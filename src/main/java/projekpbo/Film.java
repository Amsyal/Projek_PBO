package projekpbo;

public class Film implements CsvConverter {
    // ENCAPSULATION: Field private
    private String judul;
    private int harga;
    private Studio studio; // AGREGASI
    //private String jamTayang;
    //private String tanggalTayang;

    public Film(String judul, int harga, Studio studio) {
        this.judul = judul;
        this.harga = harga;
        this.studio = studio;
        //this.jamTayang = jamTayang;         // Baru
        //this.tanggalTayang = tanggalTayang 
         
    }
    
    // Getter methods
    public String getJudul() { return judul; }
    public int getHarga() { return harga; }
    public Studio getStudio() { return studio; }
    // public String getJamTayang() { return jamTayang; }
    // public String getTanggalTayang() { return tanggalTayang; }

    @Override
    public String toCsv() {
        return judul + ";" + harga + ";" + studio.toCsv();
    }
}