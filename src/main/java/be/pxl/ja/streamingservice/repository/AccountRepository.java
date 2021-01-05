package be.pxl.ja.streamingservice.repository;

import be.pxl.ja.streamingservice.exception.DuplicateEmailException;
import be.pxl.ja.streamingservice.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountRepository {

    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account newAccount) {
        Account eventueel = accounts.stream()
                .filter(e -> newAccount.getEmail().equals(e.getEmail()))
                .findAny()
                .orElse(null);

        if (eventueel != null) {
            try {
                throw new DuplicateEmailException();
            } catch (DuplicateEmailException e) {
                e.printStackTrace();
            }
        } else {
            accounts.add(newAccount);
        }
    }

    public Account findAccount(String email) {
        return accounts.stream()
                .filter(e -> email.equals(e.getEmail()))
                .findAny()
                .orElse(null);
    }
}
