let json = {}

function setValue(key, value) {
    json[key] = value;
}

function setAllValues(jsonValues) {
    for (let key in jsonValues) {
        json[key] = jsonValues[key];
    }
}

function getJson() {
    return json;
}

function getValue(key) {
    return json[key];
}

export { setValue, setAllValues, getJson, getValue }