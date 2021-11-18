import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

});


function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + "/api/weaponTypes/" + 
    getParameterByName("weaponType") + "/weapons", true);

    const request = {
        'name': document.getElementById('name').value,
        'price': document.getElementById('price').value,
        'weight': parseFloat(document.getElementById('weight').value),
        'length': parseFloat(document.getElementById('length').value),
        'material': document.getElementById('material').value,
        'weaponType': getParameterByName("weaponType")
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}

