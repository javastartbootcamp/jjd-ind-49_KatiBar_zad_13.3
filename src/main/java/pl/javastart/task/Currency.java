package pl.javastart.task;

public class Currency {
    private String currencyName;
    private double price;

    public Currency(String currencyName, double price) {
        this.currencyName = currencyName;
        this.price = price;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
