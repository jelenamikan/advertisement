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
        return categoryRepository.findAllByDeletedFalse();
    }

    public Set<Ad> findAllAds(CategoryId categoryId) {
        Category category = categoryRepository.findByIdAndDeletedFalse(categoryId)
                .orElseThrow(() -> new RuntimeException("Category Not Found!!!"));
        return category.getAds();
    }

    @NonNull
    public Category findById(@NonNull CategoryId categoryId) {
        Objects.requireNonNull(categoryId, "categoryId must not be null");
        return categoryRepository.findByIdAndDeletedFalse(categoryId)
                .orElseThrow(() -> new RuntimeException("Category Not Found!!!"));
    }

    public Category saveCategory(Category category) {
        Category cat = new Category(new CategoryId(), category.getName());
        return categoryRepository.save(cat);
    }

    public void deleteById(CategoryId categoryId) {
        categoryRepository.findById(categoryId).get().setDeleted(true);
    }

}
