/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pg.eti.aui.aui.weaponType.service;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pg.eti.aui.aui.weaponType.entity.WeaponType;
import pl.edu.pg.eti.aui.aui.weaponType.repository.WeaponTypeRepository;

import javax.transaction.Transactional;

/**
 *
 * @author harmi
 */
@Service
public class WeaponTypeService {
    private WeaponTypeRepository repository;
    
    @Autowired
    public WeaponTypeService(WeaponTypeRepository repository) {
        this.repository = repository;
    }

    public Optional<WeaponType> find(String name) {
        return repository.findById(name);
    }

    
    public List<WeaponType> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void create(WeaponType weaponType) {
        repository.save(weaponType);
    }

    @Transactional
    public void update(WeaponType weaponType) {
        repository.save(weaponType);
    }

    @Transactional
    public void delete(String weaponType) {
        repository.deleteById(weaponType);
    }
}
