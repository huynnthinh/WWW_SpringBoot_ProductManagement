package iuh.fit.se.services;

import iuh.fit.se.entities.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();
    public Product save(Product product);
    public Product update(Product product);
    public Product findById(int id);
    public void deleteById(int id);
    public List<Product> findByCodeAndNameCategory(String code, String name,boolean active);
}
