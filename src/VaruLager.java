import java.util.ArrayList;

public class VaruLager {

    private ArrayList<Varor> products = new ArrayList<>();
    private Varor varor;
    private double varorAntal;

    //Constructor

    public VaruLager (){}

    public VaruLager (Varor varor, double varorAntal){
        this.varor = varor;
        this.varorAntal = varorAntal;
    }

    //Getters & Setters

    public Varor getVaror() {
        return varor;
    }

    public void setVaror(Varor varor) {
        this.varor = varor;
    }

    public ArrayList<Varor> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Varor> products) {
        this.products = products;
    }

    public void addVaror(Varor varor){
        products.add(varor);
    }
}