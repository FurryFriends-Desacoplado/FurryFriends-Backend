package com.furryfriends.FurryFriends_Backend.services.interfaces;

import com.furryfriends.FurryFriends_Backend.entities.PersonalAccessToken;

import java.util.List;

public interface IPersonalAccessTokenService {

    List<PersonalAccessToken> findAll();

    PersonalAccessToken findById(Long id);

    Boolean create(PersonalAccessToken personalAccessToken);

    Boolean update(PersonalAccessToken personalAccessToken);

    Boolean delete(PersonalAccessToken personalAccessToken);
}
