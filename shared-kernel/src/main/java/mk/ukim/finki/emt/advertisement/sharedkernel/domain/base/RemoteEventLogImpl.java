package mk.ukim.finki.emt.advertisement.sharedkernel.domain.base;

import mk.ukim.finki.emt.advertisement.sharedkernel.infra.eventlog.StoredDomainEvent;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class RemoteEventLogImpl implements RemoteEventLog {

    private final List<StoredDomainEvent> events;

    public RemoteEventLogImpl(ResponseEntity<List<StoredDomainEvent>> events) {
        this.events = events.getBody();
    }

    @Override
    public List<StoredDomainEvent> events() {
        return events;
    }
}
