package de.zeilfelder.tc.courtbooking.entities;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany
    private Set<User> associatedUsers;

    @ManyToOne
    private Court court;

    private LocalDateTime startTime;

    private Duration duration;

    public Booking() {}

    public Booking(User user, Set<User> associatedUsers, Court court, LocalDateTime startTime, Duration duration) {
        this.user = user;
        this.associatedUsers = associatedUsers;
        this.court = court;
        this.startTime = startTime;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<User> getAssociatedUsers() {
        return associatedUsers;
    }

    public void setAssociatedUsers(Set<User> associatedUsers) {
        this.associatedUsers = associatedUsers;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
