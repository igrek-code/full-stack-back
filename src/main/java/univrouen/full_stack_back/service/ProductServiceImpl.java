package univrouen.full_stack_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univrouen.full_stack_back.Repository.ProductRepository;
import univrouen.full_stack_back.model.Product;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product){
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(long id){
        return productRepository.findById(id);
    }

    @Override
    public Product update(long id, Product newProduct) {
        //updating a record
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setPrice(newProduct.getPrice());
                    return productRepository.save(product);
                }).orElseThrow(()->new RuntimeException("Product does not exist!"));
    }


}
