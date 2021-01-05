package be.pxl.ja.streamingservice.model;

import be.pxl.ja.streamingservice.exception.TooManyProfilesException;
import be.pxl.ja.streamingservice.util.PasswordUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Account {

    private static final int MINIMUM_PASSWORD_STRENGTH = 5;

    private String email;
    private String password;
    private PaymentInfo paymentInfo;
    private StreamingPlan streamingPlan;
    private List<Profile> profiles = new ArrayList<>();

    public Account(String email, String password) {
        if (email == null || email == "") {
            throw new IllegalArgumentException("Geef een waarde op voor email");
        } else if (password == null || password == "") {
            throw new IllegalArgumentException("Geef een waarde op voor passwoord");
        }

        this.email = email;

        Profile profile1 = new Profile("Profile1");
        profiles.add(profile1);

        setStreamingPlan(StreamingPlan.BASIC);

        setPassword(password);

    }

    public void setStreamingPlan(StreamingPlan streamingPlan) {
        this.streamingPlan = streamingPlan;
    }

    public void addProfile(Profile profile) throws TooManyProfilesException {
        if (streamingPlan.getNumberOfScreens() > profiles.size()) {
            throw new TooManyProfilesException();
        }
        this.profiles.add(profile);
    }

    public String getEmail() {
        return this.email;
    }

    public boolean verifyPassword(String password) {
        return PasswordUtil.isValid(password, this.password);
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public PaymentInfo getPaymentInfo() {
        return this.paymentInfo;
    }

    public void setPassword(String password) {
        if (PasswordUtil.calculateStrength(password) >= 5) {
            this.password = PasswordUtil.encodePassword(password);
        } else {
            throw new IllegalArgumentException("Passwoord is niet sterk genoeg");
        }
    }

    public int getNumberOfProfiles() {
        return profiles.size();
    }

    public List<Profile> getProfiles() {
        return profiles;
    }
}