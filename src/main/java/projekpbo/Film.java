package projekpbo;

public class Film {
    public String judul;
    public int harga;
    public Studio studio; // AGREGASI: Film "has-a" Studio

    // Constructor menerima objek Studio yang SUDAH JADI (dari luar)
    public Film(String judul, int harga, Studio studio) {
        this.judul = judul;
        this.harga = harga;
        this.studio = studio; 
    }
    
    public String toCSV() {
        // Kita menyimpan data studio agar saat load bisa direkonstruksi
        return judul + ";" + harga + ";" + studio.nama + ";" + 
               studio.daftarKursi.length + ";" + studio.daftarKursi[0].length + ";" + 
               studio.getKursiString();
    }
}
