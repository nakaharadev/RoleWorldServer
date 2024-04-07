import { AbstractComponent } from "./AbstractComponent.js";

export class SourceComponent extends AbstractComponent {
    tagName = "source";

    constructor() {
        super();

        this.params = [];
    }

    addParameter = function(key, value) {}

    setSrc = function(src) {
        this.params.push({
            key: "src",
            value: src
        });
    }

    addStyle = function(key, value) {}

    addInnerComponent = function(component) {}

    getAsHtml = function() {
        let parameters = "";
        this.params.forEach(element => {
            if (element.value != "") {
                parameters += `${element.key}="${element.value}" `;
            } else {
                parameters += `${element.key} `;
            }
        });

        return `<${this.tagName} ${parameters}" />`
    }
}