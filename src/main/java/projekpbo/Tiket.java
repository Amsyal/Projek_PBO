package projekpbo;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Tiket {
    public String idTiket;
    public String judulFilm;
    public String infoKursi;
    public int harga;
    public long tanggalBeliEpoch;

    public Tiket(String id, String judul, String kursi, int harga, long waktu) {
        this.idTiket = (id == null) ? "TKT-" + System.currentTimeMillis() : id;
        this.judulFilm = judul;
        this.infoKursi = kursi;
        this.harga = harga;
        this.tanggalBeliEpoch = (waktu == 0) ? System.currentTimeMillis() : waktu;
    }

    public String toCSV() {
        return idTiket + ";" + judulFilm + ";" + infoKursi + ";" + harga + ";" + tanggalBeliEpoch;
    }

    @Override
    public String toString() {
        Date d = new Date(tanggalBeliEpoch);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy HH:mm");
        return String.format("[%s] %s | Seat: %s | Rp %d | %s", 
               idTiket, judulFilm, infoKursi, harga, sdf.format(d));
    }
}