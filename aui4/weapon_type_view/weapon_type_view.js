import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode,
    createLink
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayWeaponType();
    fetchAndDisplayWeapons();
});


function fetchAndDisplayWeapons() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayWeapons(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/weaponTypes/' + getParameterByName('weaponType') + '/weapons', true);
    xhttp.send();
}


function displayWeapons(weapons) {
    let newWeapon = document.getElementById("newWeapon");
    clearElementChildren(newWeapon);
    newWeapon.appendChild(createLink("Create new weapon.","../weapon_add/weapon_add.html?weaponType=" 
    + getParameterByName("weaponType")));
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    weapons.weapons.forEach(weapon => {
        tableBody.appendChild(createTableRow(weapon));
    })
}


function createTableRow(weapon) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(weapon.name));
    tr.appendChild(createLinkCell('view', '../weapon_view/weapon_view.html?weaponType='
        + getParameterByName('weaponType') + '&weapon=' + weapon.name));
    tr.appendChild(createLinkCell('edit', '../weapon_edit/weapon_edit.html?weaponType='
        + getParameterByName('weaponType') + '&weapon=' + weapon.name));
    tr.appendChild(createButtonCell('delete', () => deleteWeapon(weapon.name)));
    return tr;
}


function deleteWeapon(weapon) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayWeapons();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/weaponTypes/' + getParameterByName('weaponType')
        + '/weapons/' + weapon, true);
    xhttp.send();
}


function fetchAndDisplayWeaponType() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayWeaponType(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/weaponTypes/' + getParameterByName('weaponType'), true);
    xhttp.send();
}


function displayWeaponType(weaponType) {
    setTextNode('weaponTypeName', weaponType.name);
    setTextNode('name', weaponType.name);
    setTextNode('attackType', weaponType.attackType);
    setTextNode('inventionYear', weaponType.inventionYear);

}
