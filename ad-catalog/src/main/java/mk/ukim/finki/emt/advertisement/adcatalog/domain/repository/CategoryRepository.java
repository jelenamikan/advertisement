package mk.ukim.finki.emt.advertisement.adcatalog.domain.repository;

import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.Category;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.CategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, CategoryId> {

    List<Category> findAllByDeletedFalse();

    Optional<Category> findByIdAndDeletedFalse(CategoryId categoryId);

}
