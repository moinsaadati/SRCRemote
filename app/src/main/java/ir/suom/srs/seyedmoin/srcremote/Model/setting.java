package ir.suom.srs.seyedmoin.srcremote.Model;

/**
 * Created by seyedmoin on 2/25/17.
 */

public class setting {

    public setting(String tittle, String number) {
        this.tittle = tittle;
        this.number = number;
    }

    String tittle, number;

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
