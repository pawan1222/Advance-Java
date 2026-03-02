package com.example.ProductApp.Repository;

import com.example.ProductApp.Model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class ProductRepository {
    private List<Product> productList = new ArrayList<>();
    private Long idcount=1L;

    public List<Product> findAll(){
        return productList;
    }

    public  void save(Product product){
        if(product.getId()==null){
            product.setId(idcount++);
            productList.add(product);
        }else{
            update(product);
        }
    }

    public Optional<Product> findById(Long id){
        return productList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public void deleteById(Long id){
        productList.removeIf(p->p.getId().equals(id));
    }

    private void update(Product updatedProduct){
        for(int i=0;i<productList.size();i++){
            if(productList.get(i).getId().equals(updatedProduct.getId())){
                productList.set(i,updatedProduct);
                break;
            }
        }
    }
}
