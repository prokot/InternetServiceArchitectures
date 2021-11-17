/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pg.eti.aui.aui.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.edu.pg.eti.aui.aui.weapon.entity.Weapon;
import pl.edu.pg.eti.aui.aui.weapon.service.WeaponService;
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
    
    private final WeaponService weaponService;
    
    private final WeaponTypeService weaponTypeService;
    
    @Autowired
    public DataInitializer(WeaponService weaponService, WeaponTypeService weaponTypeService) {
        this.weaponService = weaponService;
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
        
        
        weaponTypeService.create(Mace);
        weaponTypeService.create(Katana);
        weaponTypeService.create(Zweihander);
        
        
        Weapon Skull_Crusher = Weapon.builder()
                .name("Skull Crusher")
                .price(4211.34)
                .length(1.4)
                .weight(19.8)
                .material("Iron")
                .weaponType(Mace)
                .build();
        Weapon Death_Bringer = Weapon.builder()
                .name("Death Bringer")
                .price(6941.12)
                .length(2.0)
                .weight(24.1)
                .material("Brass")
                .weaponType(Mace)
                .build();
        Weapon Kaminishi_No_Yari = Weapon.builder()
                .name("Kaminishi No Yari")
                .price(16224.95)
                .length(2.1)
                .weight(12.3)
                .material("Steel")
                .weaponType(Katana)
                .build();
        Weapon Zangetsu = Weapon.builder()
                .name("Zangetsu")
                .price(23261.24)
                .length(1.5)
                .weight(14.5)
                .material("Carbon Steel")
                .weaponType(Katana)
                .build();
        Weapon Flamenwerfer = Weapon.builder()
                .name("Flamenwerfer")
                .price(6113.44)
                .length(2.3)
                .weight(21.2)
                .material("Damascus Steel")
                .weaponType(Zweihander)
                .build();
        Weapon Chaos_Blade = Weapon.builder()
                .name("Chaos Blade")
                .price(12214.82)
                .length(2.2)
                .weight(20.6)
                .material("Steel")
                .weaponType(Zweihander)
                .build();
        
        weaponService.create(Skull_Crusher);
        weaponService.create(Death_Bringer);
        weaponService.create(Kaminishi_No_Yari);
        weaponService.create(Zangetsu);
        weaponService.create(Flamenwerfer);
        weaponService.create(Chaos_Blade);
        
    }
}

