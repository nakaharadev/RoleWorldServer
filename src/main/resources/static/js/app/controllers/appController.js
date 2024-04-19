import * as userData from "../data/userData.js"

function saveUserData(data) {
    userData.setAllValues(data);
    localStorage.setItem("userData", JSON.stringify(data));
}

function initAfterAuth(htmlData, data) {
    document.querySelector("#main_app_block").innerHTML = htmlData;
    saveUserData(data);
}

function init(htmlData, data) {
    document.querySelector("#main_app_block").innerHTML = htmlData;
    userData.setAllValues(data);
}

export { init, initAfterAuth }