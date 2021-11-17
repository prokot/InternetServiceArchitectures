/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pg.eti.aui.aui.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.edu.pg.eti.aui.aui.weaponType.entity.WeaponType;
import pl.edu.pg.eti.aui.aui.weaponType.service.WeaponTypeService;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 *
 * @author harmi
 */
@Component
public class DataInitializer {
    

    private final WeaponTypeService weaponTypeService;
    
    @Autowired
    public DataInitializer( WeaponTypeService weaponTypeService) {
        this.weaponTypeService = weaponTypeService;
    }

    @PostConstruct
    public synchronized void init(){
        WeaponType Mace = WeaponType.builder()
                .name("Mace")
                .attackType("blunt")
                .inventionYear(345)
                .build();
        
        WeaponType Katana = WeaponType.builder()
                .name("Katana")
                .attackType("piercing")
                .inventionYear(457)
                .build();
        
        WeaponType Zweihander = WeaponType.builder()
                .name("Zweihander")
                .attackType("cutting")
                .inventionYear(1236)
                .build();
        
        
        weaponTypeService.createLocal(Mace);
        weaponTypeService.createLocal(Katana);
        weaponTypeService.createLocal(Zweihander);
        
    }
}

