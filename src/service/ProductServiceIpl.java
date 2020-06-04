package service;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceIpl implements ProductService {
     private  static List<Product> products;
     static {
         products= new ArrayList<>();
         products.add(new Product("Product 1",12000,"Good 1","Maker 1"));
         products.add(new Product("Product 2",10000,"Good 2","Maker 2"));
         products.add(new Product("Product 1",11000,"Good 3","Maker 3"));
         products.add(new Product("Product 3",13000,"Good 1","Maker 4"));
         products.add(new Product("Product 4",14000,"Good 3","Maker 5"));
         products.add(new Product("Product 2",17000,"Good 1","Maker 1"));
         products.add(new Product("Product 5",16000,"Good 1","Maker 1"));
         products.add(new Product("Product 7",15000,"Medium 1","Maker 4"));
         products.add(new Product("Product 6",16000,"Medium 2","Maker 3"));
         products.add(new Product("Product 1",17000,"Medium 3","Maker 1"));
     }


    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void saveProduct(Product product) {
         products.add(product);

    }

    @Override
    public Product findById(String id) {
         int index=-1;
         for (int i=0; i<products.size();i++){
             if (products.get(i).getId().equalsIgnoreCase(id));
             index=i;
         }
         return products.get(index);
    }


    @Override
    public void removeProduct(String id) {
         Product product= findById(id);
         products.remove(product);

    }

    @Override
    public Product searchByName(String productName) {
        int index=-1;
        for (int i=0; i<products.size();i++){
            if (products.get(i).getName().equalsIgnoreCase(productName)){
                index=i;
            };

        }
        if (index!=-1){
            return products.get(index);
        } else {
            return null;
        }
    }
}

