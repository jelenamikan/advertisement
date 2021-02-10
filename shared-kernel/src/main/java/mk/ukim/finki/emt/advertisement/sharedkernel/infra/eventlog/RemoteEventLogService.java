package mk.ukim.finki.emt.advertisement.sharedkernel.infra.eventlog;


import mk.ukim.finki.emt.advertisement.sharedkernel.domain.base.RemoteEventLog;

public interface RemoteEventLogService {

    String source();

    RemoteEventLog currentLog(long lastProcessedId);

}
