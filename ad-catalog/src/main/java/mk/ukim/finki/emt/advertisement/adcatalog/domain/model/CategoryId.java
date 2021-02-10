package mk.ukim.finki.emt.advertisement.adcatalog.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class CategoryId extends DomainObjectId {

    public CategoryId() {
        super(DomainObjectId.randomId(CategoryId.class).toString());
    }

    @JsonCreator
    public CategoryId(String id) {
        super(id);
    }

}
