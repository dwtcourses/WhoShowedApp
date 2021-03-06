package uk.ac.bbk.cristinaborri.whoshowedapp.model;

import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Cristina Borri
 * This class represents the event and it's persisted using EventDAO
 */

public class Attendee {
    /**
     * This is the (database) identifier of the attendee
     */
    private long id;
    /**
     * This is the name of the attendee
     */
    private String name;
    /**
     * These is the email of the attendee
     */
    private String email;
    /**
     * These is the unique id of the attendee
     */
    private String uniqueId;
    /**
     * This is the (database) identifier of the event to attend
     */
    private long eventId;
    /**
     * This is the date of the last update
     */
    private Date attendedOn;
    /**
     * This is the field recoding attendance
     */
    private boolean attended;

    /**
     * This constructor will create en empty attendee
     */
    public Attendee() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getUniqueId() { return uniqueId; }

    public void setUniqueId(String uniqueId) { this.uniqueId = uniqueId; }

    public Date getAttendedOn() {
        return attendedOn;
    }

    public void setAttendedOn(Date attendedOn) {
        this.attendedOn = attendedOn;
    }

    public boolean hasAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }

    public void attend() {
        setAttended(true);
        setAttendedOn(new Date());
    }

    private void initUUID()
    {
        this.uniqueId = UUID.randomUUID().toString();
    }

    public String generateUniqueCode(Event event)
    {
        JSONObject json = new JSONObject();
        try {
            json.put("attendee_uid", this.getUniqueId());
            json.put("event", event.jsonSerialize());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return Base64.encodeToString(json.toString().getBytes(Charset.defaultCharset()), Base64.NO_WRAP);
    }

    public void init(long eventId)
    {
        setEventId(eventId);
        initUUID();
        setAttended(false);
    }

    @Override
    public String toString() {
        return "Attendee{" +
                "id=" + id +
                ", eventId='" + eventId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", uniqueId='" + uniqueId + '\'' +
                '}';
    }
}
