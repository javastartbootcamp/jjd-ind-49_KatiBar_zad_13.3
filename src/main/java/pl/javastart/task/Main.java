package pl.javastart.task;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String productsFileName = "src/main/resources/products.csv";
        String currenciesFileName = "src/main/resources/currencies.csv";
        ProductsUtility productsUtility = new ProductsUtility();

        try {
            List<Product> products = productsUtility.readFileAndCreateProductsList(productsFileName);
            List<Currency> currencies = productsUtility.readFileAndCreateCurrenciesList(currenciesFileName);
            List<BigDecimal> euroPriceList = productsUtility.createEuroPriceList(products, currencies);
            BigDecimal sumAllEuroPrices = productsUtility.calculateSumAllEuroPrices(euroPriceList);
            System.out.println("Suma wszystkich produktów: " + sumAllEuroPrices + " EUR");
            BigDecimal avgEuroPrices = productsUtility.calculateAvgEuroPrices(euroPriceList);
            System.out.println("Średnia wartość produktów: " + avgEuroPrices + " EUR");
            Product theMostExpensiveProduct = productsUtility.getTheMostExpensiveProduct(euroPriceList, products);
            System.out.println("Najdroższy produkt: " + theMostExpensiveProduct.getName());
            Product theCheapestProduct = productsUtility.getTheCheapestProduct(euroPriceList, products);
            System.out.println("Najtańszy produkt: " + theCheapestProduct.getName());
        } catch (FileNotFoundException e) {
            System.err.println("Podany plik nie istnieje");
        }
    }
}
