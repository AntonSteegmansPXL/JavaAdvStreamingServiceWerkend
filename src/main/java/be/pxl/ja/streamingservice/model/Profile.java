package be.pxl.ja.streamingservice.model;

import be.pxl.ja.streamingservice.exception.InvalidDateException;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Profile {

    private String name;
    private LocalDate dateOfBirth;

    private List<Content> recentlyWatched = new ArrayList<>();
    private List<Content> currentlyWatching = new ArrayList<>();
    private List<Content> myList = new ArrayList<>();

    private String avatar;


    public Profile(String name) {
        this(name, "profile1");
    }

    public Profile(String name, String avatar ) {
        this.name = name;
        this.avatar = avatar;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth.isAfter(LocalDate.now())) {
            throw new InvalidDateException(dateOfBirth, " birthDate", "Birthdate can't be in the future");
        } else {
            this.dateOfBirth = dateOfBirth;
        }
    }

    public String getAvatar() {
        return this.avatar;
    }

    public int getAge() {
        if (dateOfBirth == null) {
            return 0;
        } else {
            return Period.between(dateOfBirth, LocalDate.now()).getYears();
        }
    }

    public void startWatching(Content content) {
        if (!currentlyWatching.contains(content)) {
            currentlyWatching.add(0, content);
        } else {
            throw new IllegalArgumentException("Deze content ben je al aan het kijken");
        }
    }

    public void finishWatching(Content content) {
        currentlyWatching.remove(content);
        recentlyWatched.add(0, content);
    }

    public List<Content> getRecentlyWatched() {
        return this.recentlyWatched;
    }

    public List<Content> getCurrentlyWatching() {
        return this.currentlyWatching;
    }

    public List<Content> getMyList() {
        return this.myList;
    }

    public void addToMyList(Content content) {
        if (!myList.contains(content)) {
            myList.add(content);
        } else {
            throw new IllegalArgumentException("Deze content zit al in je lijst");
        }
    }

    public boolean isInMyList(Content content) {
        return myList.contains(content);
    }

    public boolean allowedToWatch(Content content) {
        if (getAge() == 0) {
            return false;
        } else {
            return content.getMaturityRating().getMinimumAge() <= getAge();
        }
    }

    public void removeFromMyList(Content content) {
        myList.remove(content);
    }
}