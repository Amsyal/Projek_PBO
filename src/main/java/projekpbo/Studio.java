package projekpbo;

public class Studio {
    public String nama;
    public Kursi[][] daftarKursi; 

    public Studio(String nama, int baris, int kolom) {
        this.nama = nama;
        this.daftarKursi = new Kursi[baris][kolom];

        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                String nomorKursi = "" + (char)('A' + i) + (j + 1);
                this.daftarKursi[i][j] = new Kursi(nomorKursi);
            }
        }
    }

    public String getKursiString() {
        StringBuilder sb = new StringBuilder();
        for (Kursi[] baris : daftarKursi) {
            for (Kursi k : baris) {
                sb.append(k.isTerisi() ? "1" : "0");
            }
        }
        return sb.toString();
    }

    // Helper untuk CSV: Mengubah String "1010" kembali ke status Objek Kursi
    public void loadKursiFromString(String data) {
        int index = 0;
        for (int i = 0; i < daftarKursi.length; i++) {
            for (int j = 0; j < daftarKursi[i].length; j++) {
                if (index < data.length()) {
                    boolean status = (data.charAt(index++) == '1');
                    daftarKursi[i][j].setTerisi(status);
                }
            }
        }
    }

    public void tampilkanDenah() {
        System.out.println("   [ " + nama + " ]");
        System.out.print("   ");
        for (int k = 1; k <= daftarKursi[0].length; k++) System.out.print(k + " ");
        System.out.println();

        for (int i = 0; i < daftarKursi.length; i++) {
            System.out.print((char)('A' + i) + "  ");
            for (int j = 0; j < daftarKursi[i].length; j++) {
                // Akses status via getter objek Kursi
                System.out.print(daftarKursi[i][j].isTerisi() ? "X " : "O ");
            }
            System.out.println();
        }
    }

    public boolean bookKursi(int b, int k) {
        if (!daftarKursi[b][k].isTerisi()) {
            daftarKursi[b][k].setTerisi(true);
            return true;
        }
        return false;
    }
}