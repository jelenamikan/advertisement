package mk.ukim.finki.emt.advertisement.adcatalog.domain.repository;

import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.Category;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.CategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, CategoryId> {
}
