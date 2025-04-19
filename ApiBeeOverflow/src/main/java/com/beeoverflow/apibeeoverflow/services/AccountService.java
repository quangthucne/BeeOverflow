package com.beeoverflow.apibeeoverflow.services;


import com.beeoverflow.apibeeoverflow.beans.AccountBean;
import com.beeoverflow.apibeeoverflow.dto.AccountDTO;
import com.beeoverflow.apibeeoverflow.entities.Account;
import com.beeoverflow.apibeeoverflow.jpas.AccountJPA;
import com.beeoverflow.apibeeoverflow.mappers.AccountMapper;
import com.beeoverflow.apibeeoverflow.service.ImageService;
import com.beeoverflow.apibeeoverflow.utils.CookiesUtil;
import com.beeoverflow.apibeeoverflow.utils.PasswordHashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    PasswordHashing passwordHashing;

    @Autowired
    AccountJPA accountJPA;

    @Autowired
    ImageService imageService;

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    CookiesUtil cookiesUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    public List<AccountDTO> getAccounts() {
        return accountMapper.accountsToAccountDTOs(accountJPA.findAll());
    }

    public Account getAccountByUsername(String username) {
        return accountJPA.findByUsername(username).get();
    }

    public Account getAccountById(int accountId) {
        return accountJPA.findById(accountId);
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
        account.setGender(accountBean.getGender());
        account.setIsActive(true);
        account.setRole(1);

        // set avatar default if accountBean.getAvatarFile() is null
        if (accountBean.getAvatarFile() != null) {
            account.setAvatar(imageService.saveAvatar(accountBean.getAvatarFile()));
        } else {
            String avatar = accountBean.getGender() == 1 ? "avatar_male.jpg" : "avatar_female.jpg";
            account.setAvatar(avatar);
        }

        Account savedAccount = accountJPA.save(account);

        return savedAccount;

        // chưa batws unique email và username
    }

    public String verifyAccount(AccountBean accountBean) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(accountBean.getUsername(), accountBean.getPassword())
            );

            Account account = accountJPA.getByUsername(accountBean.getUsername());

            // Cast principal to UserDetails if needed
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Generate JWT token
            String token = cookiesUtil.setToken(userDetails.getUsername(), String.valueOf(account.getId()),  authentication.getAuthorities().iterator().next().getAuthority()); // Or add role info here

            return token;

        } catch (AuthenticationException e) {
            return "username or password incorrect";
        }

    }


}
