package mk.ukim.finki.emt.advertisement.ordermanagement.domain.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class AdId extends DomainObjectId {

    private AdId() {
        super(DomainObjectId.randomId(AdId.class).toString());
    }

    @JsonCreator
    public AdId(String id) {
        super(id);
    }

}
