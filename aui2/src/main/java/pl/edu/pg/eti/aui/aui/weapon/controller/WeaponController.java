package pl.edu.pg.eti.aui.aui.weapon.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.eti.aui.aui.weapon.dto.CreateWeaponRequest;
import pl.edu.pg.eti.aui.aui.weapon.dto.GetWeaponResponse;
import pl.edu.pg.eti.aui.aui.weapon.dto.GetWeaponsResponse;
import pl.edu.pg.eti.aui.aui.weapon.dto.UpdateWeaponRequest;
import pl.edu.pg.eti.aui.aui.weapon.entity.Weapon;
import pl.edu.pg.eti.aui.aui.weapon.service.WeaponService;

import java.util.Optional;

@RestController
@RequestMapping("api/weapons")
public class WeaponController {


    private WeaponService weaponService;

    

   
    @Autowired
    public WeaponController(WeaponService characterService) {
        this.weaponService = characterService;
    }

   
    @GetMapping
    public ResponseEntity<GetWeaponsResponse> getWeapons() {
        return ResponseEntity
                .ok(GetWeaponsResponse.entityToDtoMapper().apply(weaponService.findAll()));
    }

    
    @GetMapping("{name}")
    public ResponseEntity<GetWeaponResponse> getWeapon(@PathVariable("name") String name) {

        return weaponService.find(name)
                .map(value -> ResponseEntity
                        .ok(GetWeaponResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }

    
    @PostMapping
    public ResponseEntity<Void> createWeapon(@RequestBody CreateWeaponRequest request, UriComponentsBuilder builder) {

        Weapon weapon = CreateWeaponRequest
                .dtoToEntityMapper(name -> weaponService.find(name).orElseThrow(), () -> null)
                .apply(request);
        if(weaponService.find(weapon.getName()).isPresent()){

            return ResponseEntity
                    .badRequest()
                    .build();
        }
        else {
            weapon = weaponService.create(weapon);
            return ResponseEntity
                    .created(builder
                            .pathSegment("api", "weapons", "{name}")
                            .buildAndExpand(weapon.getName()).toUri())
                    .build();
        }
    }

   
    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteWeapon(@PathVariable("name") String name) {
        Optional<Weapon> weapon = weaponService.find(name);
        if (weapon.isPresent()) {
            weaponService.delete(weapon.get().getName());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    
    @PutMapping("{name}")
    public ResponseEntity<Void> updateWeapon(@RequestBody UpdateWeaponRequest request, @PathVariable("name") String name) {
        Optional<Weapon> weapon = weaponService.find(name);
        if (weapon.isPresent()) {
            UpdateWeaponRequest.dtoToEntityUpdater().apply(weapon.get(), request);
            weaponService.update(weapon.get());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }


}
