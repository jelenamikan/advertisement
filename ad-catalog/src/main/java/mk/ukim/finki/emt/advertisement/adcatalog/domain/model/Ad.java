package mk.ukim.finki.emt.advertisement.adcatalog.domain.model;

import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "ads")
public class Ad extends AbstractEntity<AdId> {

    @Version
    private Long version;

    @Column(name = "title", nullable = false)
    private String title;

    private String description;

    private String creatorId;

    @ElementCollection(targetClass = Type.class)
    @CollectionTable(name="ad_types")
    @Column(name = "types")
    @Enumerated(EnumType.STRING)
    private Set<Type> types = new HashSet<>();

    @Embedded
    private Money money;

    private int quantity;

    private Boolean isProduct;

    private String imgUrl;

    private Boolean deleted;

    @ManyToMany(mappedBy = "ads", fetch = FetchType.EAGER)
    private Set<Category> categories = new HashSet<>();

    public Ad(AdId id, String title, String description, String creatorId, Set<Type> types, Money money, int quantity,
              boolean isProduct, String imgUrl) {
        super(id);
        this.title = title;
        this.description = description;
        this.creatorId = creatorId;
        this.types = types;
        this.money = money;
        this.quantity = quantity;
        this.isProduct = isProduct;
        this.imgUrl = imgUrl;
        this.categories = new HashSet<>();
        deleted = false;
    }

    public Ad(String title, String description, String creatorId, Money money, int quantity, boolean isProduct, String imgUrl) {
        this.title = title;
        this.description = description;
        this.creatorId = creatorId;
        this.money = money;
        this.quantity = quantity;
        this.isProduct = isProduct;
        this.imgUrl = imgUrl;
        this.categories = new HashSet<>();
        deleted = false;
    }

    public Ad() {
        deleted = false;
    }

    public void subtractQuantity(int qnt) {
        if (qnt>this.quantity) {
            throw new RuntimeException("unsupported quantity");
        }
        this.quantity -= qnt;
    }

    public void addQuantity(int qnt) {
        this.quantity +=qnt;
    }

}
