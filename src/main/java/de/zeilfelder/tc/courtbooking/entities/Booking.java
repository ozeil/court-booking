package de.zeilfelder.tc.courtbooking.entities;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
public class Booking {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany
    private Set<User> otherPlayers;

    @Enumerated
    private Court court;

    private LocalDate date;

    private LocalTime startTime;

    private Duration duration;

    public Booking() {
    }

    public Booking(User user, Set<User> otherPlayers, Court court, LocalDate date, LocalTime startTime, Duration duration) {
        this.user = user;
        this.otherPlayers = otherPlayers;
        this.court = court;
        this.date = date;
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

    public Set<User> getOtherPlayers() {
        return otherPlayers;
    }

    public void setOtherPlayers(Set<User> associatedUsers) {
        this.otherPlayers = associatedUsers;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
