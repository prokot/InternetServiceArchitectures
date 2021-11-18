/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pg.eti.aui.aui.weapon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.pg.eti.aui.aui.weapon.repository.WeaponRepository;
import pl.edu.pg.eti.aui.aui.weapon.entity.Weapon;


import java.util.List;
import java.util.Optional;

/**
 *
 * @author harmi
 */
@Service
public class WeaponService {
    private WeaponRepository repository;
    
    @Autowired
    public WeaponService(WeaponRepository repository) {
        this.repository = repository;
    }

    public Optional<Weapon> find(String name) {
        return repository.find(name);
    }

    
    public List<Weapon> findAll() {
        return repository.findAll();
    }

    
    public void create(Weapon weapon) {
        repository.create(weapon);
    }

    
    public void delete(String weapon) {
        repository.delete(repository.find(weapon).orElseThrow());
    }

}
