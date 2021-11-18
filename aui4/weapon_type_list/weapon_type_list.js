import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayWeaponTypes();
});


function fetchAndDisplayWeaponTypes() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayWeaponTypes(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/weaponTypes', true);
    xhttp.send();
}


function displayWeaponTypes(weaponTypes) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    weaponTypes.weaponTypes.forEach(weaponType => {
        tableBody.appendChild(createTableRow(weaponType));
    })
}

function createTableRow(weaponType) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(weaponType));
    tr.appendChild(createLinkCell('view', '../weapon_type_view/weapon_type_view.html?weaponType=' 
    + weaponType));
    tr.appendChild(createLinkCell('edit', '../weapon_type_edit/weapon_type_edit.html?weaponType='
    + weaponType));
    tr.appendChild(createButtonCell('delete', () => deleteWeaponTypes(weaponType)));
    return tr;
}


function deleteWeaponTypes(weaponType) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayWeaponTypes();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/weaponTypes/' + weaponType, true);
    xhttp.send();
}
