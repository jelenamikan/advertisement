package mk.finki.ukim.das.accountservice.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class UserId extends DomainObjectId {

    public UserId() {
        super(DomainObjectId.randomId(UserId.class).toString());
    }

    @JsonCreator
    public UserId(String id) {
        super(id);
    }

}
