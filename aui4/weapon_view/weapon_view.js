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
    fetchAndDisplayWeapon();
});


function fetchAndDisplayWeapon() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayWeapon(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/weaponTypes/' + getParameterByName('weaponType') 
    + "/weapons/" + getParameterByName("weapon"), true);
    xhttp.send();
}


function displayWeapon(weapon) {
    setTextNode('weaponName', weapon.name);
    setTextNode('name', weapon.name);
    setTextNode('price', weapon.price);
    setTextNode('weight', weapon.weight);
    setTextNode('length', weapon.length);
    setTextNode('weaponType', weapon.weaponType);
}
