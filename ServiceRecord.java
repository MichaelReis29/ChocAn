package cs200.team16.chocan;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Represents a transaction between a ChocAn provider and member.
 *
 * @author Faruq Yusuff
 */
public class ServiceRecord {
    private final LocalDateTime timeFiled;
    private final LocalDateTime timeProvided;
    private final ProviderRecord provider;
    private final MemberRecord member;
    private final ProviderDirectoryEntry serviceProvided;
    private final String comments;
    private final String id;

    public ServiceRecord(
            LocalDateTime timeFilled,
            LocalDateTime timeProvided,
            ProviderRecord provider,
            MemberRecord member,
            ProviderDirectoryEntry serviceProvided,
            String comments
    ) {
        this.timeFiled = timeFilled;
        this.timeProvided = timeProvided;
        this.provider = provider;
        this.member = member;
        this.serviceProvided = serviceProvided;
        this.comments = comments;
        this.id = Long.toString(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
    }
    
    /**
     * Return the id of the service record was created.
     *
     * @return The id of service record was created
     */
    public String getID() {
    	return id;
    }

    /**
     * Return the time service record was created.
     *
     * @return the time this service record was created
     */
    public LocalDateTime getTimeFiled() {
        return timeFiled;
    }

    /**
     * Return the time service was provided.
     *
     * @return LocalDateTime representing the time this service was provided
     */
    public LocalDateTime getTimeProvided() {
        return timeProvided;
    }

    /**
     * Return the ProviderRecord object representing provider in the transaction.
     *
     * @return ProviderRecord object representing provider in the transaction
     */
    public ProviderRecord getProvider() {
        return provider;
    }

    /**
     * Return the MemberRecord object representing member in the transaction.
     *
     * @return MemberRecord object representing member in the transaction
     */
    public MemberRecord getMember() {
        return member;
    }

    /**
     * Return a ProviderDirectoryEntry object representing the service that was provided.
     *
     * @return ProviderDirectoryEntry object representing service that was provided
     */
    public ProviderDirectoryEntry getServiceProvided() {
        return serviceProvided;
    }

    /**
     * Return the comments attached to this service record.
     *
     * @return comments attached to service record
     */
    public String getComments() {
        return comments;
    }
}
