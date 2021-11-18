import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplayWeapon();
});


function fetchAndDisplayWeapon() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/weaponTypes/' + getParameterByName('weaponType') + '/weapons/'
        + getParameterByName('weapon'), true);
    xhttp.send();
}


function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayWeapon();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/weaponTypes/' + getParameterByName('weaponType') + '/weapons/'
        + getParameterByName('weapon'), true);

    const request = {
        'price': document.getElementById('price').value,
        'weight': parseFloat(document.getElementById('weight').value),
        'length': parseFloat(document.getElementById('length').value),
        'material': document.getElementById('material').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}

