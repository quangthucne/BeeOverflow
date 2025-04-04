package com.beeoverflow.apibeeoverflow.service;


import com.beeoverflow.apibeeoverflow.beans.AccountBean;
import com.beeoverflow.apibeeoverflow.entities.Account;
import com.beeoverflow.apibeeoverflow.jpas.AccountJPA;
import com.beeoverflow.apibeeoverflow.utils.PasswordHashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final PasswordHashing passwordHashing = new PasswordHashing();

    @Autowired
    AccountJPA accountJPA;

    @Autowired
    ImageService imageService;

    public List<Account> getAccounts() {
        return accountJPA.findAll();
    }

    public Account getAccountByUsername(String username) {
        return accountJPA.findByUsername(username);
    }

    // Create Account
    public Account create(AccountBean accountBean) {
        Account account = new Account();

        String password = passwordHashing.passwordHash(accountBean.getPassword());

        // map accountBean into account
        account.setEmail(accountBean.getEmail());
        account.setUsername(accountBean.getUsername());
        account.setFullname(accountBean.getFullname());
        account.setPassword(password);
        account.setPhone(accountBean.getPhone());
        account.setPoint(0);
        account.setGender(accountBean.getGender());
        account.setIsActive(true);

        // set avatar default if accountBean.getAvatarFile() is null
        if (accountBean.getAvatarFile() != null) {
            account.setAvatar(accountBean.getAvatarFile().getContentType());
            imageService.saveAvatar(accountBean.getAvatarFile());
        } else {
            String avatar = accountBean.getGender() == 1 ? "avatar_male.jpg" : "avatar_female.jpg";
            account.setAvatar(avatar);
        }

        return accountJPA.save(account);

        // chưa batws unique email và username
    }

    public String verifyAccount(AccountBean accountBean) {

        Account accountLogin = accountJPA.findByUsername(accountBean.getUsername());

        PasswordHashing passwordHashing = new PasswordHashing();
//        String password = passwordHashing.passwordHash(accountBean.getPassword());

        if (accountLogin != null) {
            if (passwordHashing.verifyPassword(accountBean.getPassword(), accountLogin.getPassword())) {
                return "success";
            } else {
                return  "username or password incorrect";
            }
        }
        else {
            return "account not found";
        }

    }


}
