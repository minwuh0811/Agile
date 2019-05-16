import java.util.ArrayList;

public class VaruLager {

    private String kategori;
    ArrayList<Varor> products = new ArrayList<>();

    //Constructor
    public VaruLager (String kategori, ArrayList<Varor> products){
        this.kategori = kategori;
        this.products = products;

    }

    //Getters & Setters
    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public ArrayList<Varor> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Varor> products) {
        this.products = products;
    }

}