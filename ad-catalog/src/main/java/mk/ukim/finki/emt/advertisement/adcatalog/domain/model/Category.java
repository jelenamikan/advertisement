package mk.ukim.finki.emt.advertisement.adcatalog.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "categories")
public class Category extends AbstractEntity<CategoryId> {

    @Version
    private Long version;

    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ads_categories",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "ad_id"))
    public Set<Ad> ads = new HashSet<>();

    public Category(CategoryId categoryId, String name){
        super(categoryId);
        this.name = name;
        this.ads = new HashSet<>();
    }

}
