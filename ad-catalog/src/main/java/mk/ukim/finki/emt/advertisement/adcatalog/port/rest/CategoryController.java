package mk.ukim.finki.emt.advertisement.adcatalog.port.rest;

import mk.ukim.finki.emt.advertisement.adcatalog.application.CategoryCatalog;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.Ad;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.Category;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.CategoryId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryCatalog categoryCatalog;

    public CategoryController(CategoryCatalog categoryCatalog) {
        this.categoryCatalog = categoryCatalog;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAllCategories(){
        return categoryCatalog.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Set<Ad> getAdsByCategoryId(@PathVariable CategoryId id){
        return categoryCatalog.findAllAds(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@RequestBody Category category){
        return categoryCatalog.saveCategory(category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable CategoryId id){
        categoryCatalog.deleteById(id);
    }

}
