package projekpbo;

public class Studio implements CsvConverter {
    private String nama;
    private Kursi[][] daftarKursi;

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

    public String getNama() { return nama; }
    
    @Override
    public String toCsv() {
        return nama + ";" + daftarKursi.length + ";" + daftarKursi[0].length + ";" + getKursiString();
    }

    private String getKursiString() {
        StringBuilder sb = new StringBuilder();
        for (Kursi[] baris : daftarKursi) {
            for (Kursi k : baris) {
                sb.append(k.isTerisi() ? "1" : "0");
            }
        }
        return sb.toString();
    }

    public void loadKursiFromString(String data) {
        int index = 0;
        for (int i = 0; i < daftarKursi.length; i++) {
            for (int j = 0; j < daftarKursi[i].length; j++) {
                if (index < data.length()) {
                    daftarKursi[i][j].setTerisi(data.charAt(index++) == '1');
                }
            }
        }
    }

    public void tampilkanDenah() {
        System.out.println("   [ Screen: " + nama + " ]");
        System.out.print("   ");
        for (int k = 1; k <= daftarKursi[0].length; k++) System.out.print(k + " ");
        System.out.println();

        for (int i = 0; i < daftarKursi.length; i++) {
            System.out.print((char)('A' + i) + "  ");
            for (int j = 0; j < daftarKursi[i].length; j++) {
                System.out.print(daftarKursi[i][j].isTerisi() ? "X " : "O ");
            }
            System.out.println();
        }
    }

    public boolean bookKursi(int b, int k) {
        if (b >= 0 && b < daftarKursi.length && k >= 0 && k < daftarKursi[0].length) {
            if (!daftarKursi[b][k].isTerisi()) {
                daftarKursi[b][k].setTerisi(true);
                return true;
            }
        }
        return false;
    }
}