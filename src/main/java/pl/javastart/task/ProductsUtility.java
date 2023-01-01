package pl.javastart.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductsUtility {
    public List<Product> readFileAndCreateProductsList(String fileName) throws FileNotFoundException {
        List<Product> products = new ArrayList<>();
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split(";");
            String name = split[0];
            double price = Double.parseDouble(split[1]);
            String currency = split[2];
            Product product = new Product(name, price, currency);
            products.add(product);
        }
        return products;
    }

    public List<Currency> readFileAndCreateCurrenciesList(String fileName) throws FileNotFoundException {
        List<Currency> currencies = new ArrayList<>();
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split(";");
            String currencyName = split[0];
            double price = Double.parseDouble(split[1]);
            Currency currency = new Currency(currencyName, price);
            currencies.add(currency);
        }
        return currencies;
    }

    public List<BigDecimal> createEuroPriceList(List<Product> products, List<Currency> currencies) {
        List<BigDecimal> euroPrices = new ArrayList<>();
        for (Product product : products) {
            for (int i = 0; i < currencies.size(); i++) {
                if (product.getCurrency().equals(currencies.get(i).getCurrencyName())) {
                    double productPrice = product.getPrice();
                    double currency = currencies.get(i).getPrice();
                    BigDecimal priceInEuro = BigDecimal.valueOf(productPrice).divide(BigDecimal.valueOf(currency), MathContext.DECIMAL64);
                    euroPrices.add(priceInEuro);
                    break;
                }
            }
        }
        return euroPrices;
    }

    public BigDecimal calculateSumAllEuroPrices(List<BigDecimal> bigDecimalList) {
        BigDecimal sum = BigDecimal.valueOf(0);
        for (BigDecimal price : bigDecimalList) {
            sum = sum.add(price);
        }
        return sum;
    }

    public BigDecimal calculateAvgEuroPrices(List<BigDecimal> bigDecimalList) {
        BigDecimal sum = calculateSumAllEuroPrices(bigDecimalList);
        BigDecimal avg = sum.divide(BigDecimal.valueOf(bigDecimalList.size()), MathContext.DECIMAL64);
        return avg;
    }

    public int getTheMostExpensiveProductsIndex(List<BigDecimal> bigDecimalList) {
        int index = 0;
        BigDecimal theBiggestPrice = BigDecimal.valueOf(0);
        for (int i = 0; i < bigDecimalList.size(); i++) {
            if (bigDecimalList.get(i).compareTo(theBiggestPrice) == 1) {
                theBiggestPrice = bigDecimalList.get(i);
                index = i;
            }
        }
        return index;
    }

    public int getTheCheapestProductsIndex(List<BigDecimal> bigDecimalList) {
        int index = 0;
        BigDecimal theSmallestPrice = bigDecimalList.get(0);
        for (int i = 0; i < bigDecimalList.size(); i++) {
            if (bigDecimalList.get(i).compareTo(theSmallestPrice) == -1) {
                theSmallestPrice = bigDecimalList.get(i);
                index = i;
            }
        }
        return index;
    }
}
