package mk.ukim.finki.emt.advertisement.sharedkernel.domain.base;

import lombok.Getter;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
public abstract class AbstractEntity<ID extends DomainObjectId> implements IdentifiableDomainObject<ID> {

    @EmbeddedId
    private ID id;

    public AbstractEntity() {

    }

    public AbstractEntity(ID id) {
        this.id=id;
    }

    @Override
    public ID id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity<?> that = (AbstractEntity<?>) o;

        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? super.hashCode() : id.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", getClass().getSimpleName(), id);
    }


}
