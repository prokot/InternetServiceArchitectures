package pl.edu.pg.eti.aui.aui.weaponType.event.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.edu.pg.eti.aui.aui.weaponType.entity.WeaponType;
import pl.edu.pg.eti.aui.aui.weaponType.event.dto.CreateWeaponTypeRequest;

@Repository
public class WeaponTypeEventRepository {
    private RestTemplate restTemplate;

    @Autowired
    public WeaponTypeEventRepository(@Value("${aui.weapons.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(String weaponType) {
        restTemplate.delete("/weaponTypes/{weaponTypeName}", weaponType);
    }

    public void create(WeaponType weaponType) {
        restTemplate.postForLocation("/weaponTypes", CreateWeaponTypeRequest.entityToDtoMapper().apply(weaponType));
    }

}
