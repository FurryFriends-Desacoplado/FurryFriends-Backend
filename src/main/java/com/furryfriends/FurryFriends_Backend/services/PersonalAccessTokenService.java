package com.furryfriends.FurryFriends_Backend.services;

import com.furryfriends.FurryFriends_Backend.entities.PersonalAccessToken;
import com.furryfriends.FurryFriends_Backend.repositories.PersonalAccessTokenRepository;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IPersonalAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalAccessTokenService implements IPersonalAccessTokenService {

    @Autowired
    private PersonalAccessTokenRepository personalAccessTokenRepository;

    @Override
    public List<PersonalAccessToken> findAll() {
        return personalAccessTokenRepository.findAll();
    }

    @Override
    public PersonalAccessToken findById(Long id) {
        Optional<PersonalAccessToken> token = personalAccessTokenRepository.findById(id);
        return token.orElse(null);
    }

    @Override
    public Boolean create(PersonalAccessToken personalAccessToken) {
        try {
            personalAccessTokenRepository.save(personalAccessToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean update(PersonalAccessToken personalAccessToken) {
        try {
            personalAccessTokenRepository.save(personalAccessToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean delete(PersonalAccessToken personalAccessToken) {
        try {
            personalAccessTokenRepository.delete(personalAccessToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
