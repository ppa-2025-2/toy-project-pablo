package com.example.msticket;

import java.util.List;

import com.example.msticket.repository.entity.Profile;

public record NewUser(
        String name,
        String handle,
        String email,
        String password,
        String company,
        Profile.AccountType type,
        List<String> roles
)  {

}
