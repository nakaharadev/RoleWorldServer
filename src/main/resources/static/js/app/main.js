import { auth } from "./controllers/authController.js"
import * as app from "./controllers/appController.js"

function getMainPage(userData) {
    let buttonContainer = document.querySelector("#done_button_container");


    return fetch("http://localhost:8080/web/main_page")
            .then(response => response.text())
            .then(mainHtml => app.initAfterAuth(mainHtml, userData));
}

function main() {
    let userData = localStorage.getItem("userData");
    if (userData != null) {
        getMainPage(JSON.parse(userData));
    } else {
        auth(getMainPage);
    }
}

window.onload = function() {
    main();
}