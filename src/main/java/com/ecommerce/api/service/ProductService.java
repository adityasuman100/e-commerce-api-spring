package com.ecommerce.api.service;

import com.ecommerce.api.constant.enums.ProductSize;
import com.ecommerce.api.dto.request.CreateProductReqDto;
import com.ecommerce.api.model.Brand;
import com.ecommerce.api.model.Category;
import com.ecommerce.api.model.Product;
import com.ecommerce.api.repository.BrandRepository;
import com.ecommerce.api.repository.CategoryRepository;
import com.ecommerce.api.repository.ProductRepository;
import com.ecommerce.api.utils.cloudinary.CloudinaryService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProductService {
    final ProductRepository productRepository;
    final BrandRepository brandRepository;
    final CategoryRepository categoryRepository;
    final ModelMapper modelMapper;
    final MongoTemplate mongoTemplate;
    final CloudinaryService cloudinaryService;

    private Optional<Product> getProductByName(String name) {
        List<Product> existingProducts = productRepository.findByName(name);
        if (existingProducts.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(existingProducts.get(0));
    }

    public Product createProduct(CreateProductReqDto createProductReqDto,
                                 List<MultipartFile> files) throws Exception {

        // Product exists
        Optional<Product> existingProduct =
            getProductByName(createProductReqDto.getName());
        if (existingProduct.isPresent()) {
            throw new Exception("porduct alreadhy exist");
        }

        // find the brand
        Optional<Brand> existingBrand =
            brandRepository.findFirstByName(createProductReqDto.getBrand());
        if (existingBrand.isEmpty()) {
            throw new Exception(
                "Brand not found, please create brand first or check brand name");
        }

        // find the category
        Optional<Category> existingCategory =
            categoryRepository.findFirstByName(
                createProductReqDto.getCategory());
        if (existingCategory.isEmpty()) {
            throw new Exception(
                "Category not found, please create category first or check category name");
        }

        // uploading images to cloudinary
        List<String> imgUrls = new ArrayList<>();
        files.stream().forEach(file -> {
            imgUrls.add(cloudinaryService.uploadFile(file, "products"));
        });

        // create the product
        Product product = modelMapper.map(createProductReqDto, Product.class);
        product.setImages(imgUrls);
        Product createdProduct = productRepository.insert(product);

        // update brand
        mongoTemplate.update(Brand.class)
            .matching(Criteria.where("_id").is(existingBrand.get().get_id()))
            .apply(new Update().push("products").value(createdProduct))
            .first();

        // update category
        mongoTemplate.update(Category.class)
            .matching(Criteria.where("_id").is(existingCategory.get().get_id()))
            .apply(new Update().push("products").value(createdProduct))
            .first();

        return createdProduct;
    }

    Product getProduct(String _id) throws Exception{
        Optional<Product> existingProduct= productRepository.findById(_id);
        if(existingProduct.isPresent()){
            return existingProduct.get();
        }
        throw new Exception("No product exist.");
    }

    // Product updateProduct(String _id);

}
