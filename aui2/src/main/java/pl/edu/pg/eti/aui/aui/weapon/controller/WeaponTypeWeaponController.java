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
import pl.edu.pg.eti.aui.aui.weaponType.entity.WeaponType;
import pl.edu.pg.eti.aui.aui.weaponType.service.WeaponTypeService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/weaponTypes/{weaponTypeName}/weapons")
public class WeaponTypeWeaponController {
    private WeaponService weaponService;

    private WeaponTypeService weaponTypeService;


    @Autowired
    public WeaponTypeWeaponController(WeaponService weaponService, WeaponTypeService weaponTypeService){
        this.weaponService = weaponService;
        this.weaponTypeService = weaponTypeService;
    }

    @GetMapping()
    public ResponseEntity<GetWeaponsResponse> getWeapons(@PathVariable("weaponTypeName") String weaponTypeName){
        Optional<WeaponType> weaponType = weaponTypeService.find(weaponTypeName);

        if (weaponType.isPresent()) {
        List<Weapon> all = weaponService.findByWeaponType(weaponType.get());
        Function<Collection<Weapon>, GetWeaponsResponse> mapper = GetWeaponsResponse.entityToDtoMapper();
        GetWeaponsResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{name}")
    public ResponseEntity<GetWeaponResponse> getWeapon(@PathVariable("name") String name){
        return weaponService.find(name)
                .map(value -> ResponseEntity.ok(GetWeaponResponse.entityToDtoMapper().apply(value)))
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createWeapon(@PathVariable("weaponTypeName") String weaponTypeName,
                                                @RequestBody CreateWeaponRequest request,
                                                UriComponentsBuilder builder) {
        Optional<WeaponType> weaponType = weaponTypeService.find(weaponTypeName);
        if (weaponType.isPresent()) {
            Weapon weapon = CreateWeaponRequest
                    .dtoToEntityMapper(name -> weaponService.find(name).orElseThrow(), weaponType::get)
                    .apply(request);
            if (weaponService.find(weapon.getName()).isPresent()){
                return ResponseEntity.badRequest().build();
            }
            else{
            weapon = weaponService.create(weapon);
            return ResponseEntity.created(builder.pathSegment("api", "weaponTypes", "{weaponTypeName}", "weapons", "{name}")
                    .buildAndExpand(weaponType.get().getName(), weapon.getName()).toUri()).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteWeapon(@PathVariable("weaponTypeName") String weaponTypeName,
                                                @PathVariable("name") String name) {
        Optional<Weapon> weapon = weaponService.find(weaponTypeName, name);
        if (weapon.isPresent()) {
            weaponService.delete(weapon.get().getName());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{name}")
    public ResponseEntity<Void> updateWeapon(@PathVariable("weaponTypeName") String weaponTypeName,
                                                @RequestBody UpdateWeaponRequest request,
                                                @PathVariable("name") String name) {
        Optional<Weapon> weapon = weaponService.find(weaponTypeName, name);
        if (weapon.isPresent()) {
            UpdateWeaponRequest.dtoToEntityUpdater().apply(weapon.get(), request);
            weaponService.update(weapon.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}


