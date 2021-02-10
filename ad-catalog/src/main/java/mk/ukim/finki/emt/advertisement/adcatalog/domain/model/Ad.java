package mk.ukim.finki.emt.advertisement.adcatalog.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name = "ads")
public class Ad extends AbstractEntity<AdId> {

    @Version
    private Long version;

    @Column(name = "title", nullable = false)
    private String title;

    private String description;

    @Embedded
    private Money money;

    private int quantity;

    private boolean isProduct;

    @ManyToMany(mappedBy = "ads", fetch = FetchType.EAGER)
    private Set<Category> categories = new HashSet<>();

    public Ad(AdId id, String title, String description, Money money, int quantity, boolean isProduct) {
        super(id);
        this.title = title;
        this.description = description;
        this.money = money;
        this.quantity = quantity;
        this.isProduct = isProduct;
        this.categories = new HashSet<>();
    }

    public Ad(String title, String description, Money money, int quantity, boolean isProduct) {
        this.title = title;
        this.description = description;
        this.money = money;
        this.quantity = quantity;
        this.isProduct = isProduct;
        this.categories = new HashSet<>();
    }

    public Ad() {
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
