import java.io.Serializable;

public class Varor implements Serializable {
    private final static long serialVersionUID = 4661471824932115886L;

    private String varorName;
    private double varorPrice;
    private String typ;
    private int ID;
    private String varorKategori;

    public Varor()
    { }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getVarorKategori() {
        return varorKategori;
    }

    public void setVarorKategori(String varorKategori) {
        this.varorKategori = varorKategori;
    }

    public String getVarorNameName() {
        return varorName;
    }

    public void setVarorName(String varorName) {
        this.varorName = varorName;
    }

    public double getVarorPrice() {
        return varorPrice;
    }

    public void setVarorPrice(double VarorPrice) {
        this.varorPrice = varorPrice;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public Varor(String varorName, double varorPrice, String typ,String varorKategori, int ID )
    {
        this.varorName = varorName;
        this.varorPrice = varorPrice;
        this.typ = typ;
        this.varorKategori = varorKategori;
        this.ID = ID;

    }

    @Override
    public String toString()
    {
        return varorName + ": SEK " + varorPrice + " x " + "item/" + typ + " " + varorKategori + " " + ID ;
    }
}