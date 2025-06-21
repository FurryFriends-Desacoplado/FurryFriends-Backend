package com.furryfriends.FurryFriends_Backend.services.dao;

import java.util.List;

public interface Idao<E,T>{

    public List<E> findAll();
    public E findById(Long id);
    public Boolean create (E entity);
    public Boolean update (E entity);
    public Boolean delete (E entity);
    public Boolean show (E entity);
}
