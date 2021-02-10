package mk.ukim.finki.emt.advertisement.adcatalog.application;

import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.Ad;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.Category;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.CategoryId;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.repository.CategoryRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@Transactional
public class CategoryCatalog {

    private final CategoryRepository categoryRepository;

    public CategoryCatalog(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @NonNull
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Set<Ad> findAllAds(CategoryId categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category Not Found!!!"));
        return category.getAds();
    }

    @NonNull
    public Category findById(@NonNull CategoryId categoryId) {
        Objects.requireNonNull(categoryId, "categoryId must not be null");
        return categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category Not Found!!!"));
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(CategoryId categoryId) {
        categoryRepository.deleteById(categoryId);
    }


}
