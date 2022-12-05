package univrouen.full_stack_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univrouen.full_stack_back.Repository.ProductRepository;
import univrouen.full_stack_back.model.Product;

import java.util.HashMap;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Optional<Product> findById(long id){
        return productRepository.findById(id);
    }


    public Product addDescription(long id, HashMap<String, String> description){
        return productRepository.findById(id)
                .map(product -> {
                    product.setDescription(description);
                    return productRepository.save(product);
                }).orElseThrow(()->new RuntimeException("product does not exist!"));
    }


    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }

}
