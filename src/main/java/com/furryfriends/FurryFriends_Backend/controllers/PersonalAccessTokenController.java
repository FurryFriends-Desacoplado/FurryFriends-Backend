package com.furryfriends.FurryFriends_Backend.controllers;

import com.furryfriends.FurryFriends_Backend.entities.PersonalAccessToken;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IPersonalAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personal-access-tokens")
@CrossOrigin(origins = "*")
public class PersonalAccessTokenController {

    @Autowired
    private IPersonalAccessTokenService personalAccessTokenService;

    @GetMapping
    public ResponseEntity<List<PersonalAccessToken>> getAllPersonalAccessTokens() {
        List<PersonalAccessToken> tokens = personalAccessTokenService.findAll();
        return new ResponseEntity<>(tokens, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalAccessToken> getPersonalAccessTokenById(@PathVariable Long id) {
        PersonalAccessToken token = personalAccessTokenService.findById(id);
        if (token != null) {
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createPersonalAccessToken(@RequestBody PersonalAccessToken token) {
        Boolean result = personalAccessTokenService.create(token);
        if (result) {
            return new ResponseEntity<>("Token created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create token", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePersonalAccessToken(@PathVariable Long id, @RequestBody PersonalAccessToken token) {
        PersonalAccessToken existingToken = personalAccessTokenService.findById(id);
        if (existingToken == null) {
            return new ResponseEntity<>("Token not found", HttpStatus.NOT_FOUND);
        }

        token.setId(id);
        Boolean result = personalAccessTokenService.update(token);

        if (result) {
            return new ResponseEntity<>("Token updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update token", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersonalAccessToken(@PathVariable Long id) {
        PersonalAccessToken existingToken = personalAccessTokenService.findById(id);
        if (existingToken == null) {
            return new ResponseEntity<>("Token not found", HttpStatus.NOT_FOUND);
        }

        Boolean result = personalAccessTokenService.delete(existingToken);

        if (result) {
            return new ResponseEntity<>("Token deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete token", HttpStatus.BAD_REQUEST);
        }
    }
}
