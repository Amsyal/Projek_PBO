package projekpbo;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Tiket implements CsvConverter {
    private String idTiket;
    private String judulFilm;
    private String infoKursi;
    private int harga;
    private long tanggalBeliEpoch;

    public Tiket(String id, String judul, String kursi, int harga, long waktu) {
        this.idTiket = (id == null) ? "TKT-" + System.currentTimeMillis() : id;
        this.judulFilm = judul;
        this.infoKursi = kursi;
        this.harga = harga;
        this.tanggalBeliEpoch = (waktu == 0) ? System.currentTimeMillis() : waktu;
    }

    @Override
    public String toCsv() {
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