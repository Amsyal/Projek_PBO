package projekpbo;


public class Film implements CsvConverter {
    private String judul;
    private int harga;
    private Studio studio; 
    private String tglTayang;
    private String jamTayang;

    public Film(String judul, int harga, Studio studio, String tglTayang, String jamTayang) {
        this.judul = judul;
        this.harga = harga;
        this.studio = studio;
        this.tglTayang = tglTayang;
        this.jamTayang = jamTayang;
    }
    
    public String getJudul() { return judul; }
    public int getHarga() { return harga; }
    public Studio getStudio() { return studio; }
    public String getTglTayang() { return tglTayang; }
    public String getJamTayang() { return jamTayang; }

    @Override
    public String toCsv() {
        return judul + ";" + harga + ";" + studio.toCsv() + getTglTayang() + ";" + getJamTayang();
    }
}