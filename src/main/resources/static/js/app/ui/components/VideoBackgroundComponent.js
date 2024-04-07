import { AbstractComponent } from "./AbstractComponent.js";
import { SourceComponent } from "./SourceComponent.js";

export class VideoBackgroundComponent extends AbstractComponent {
    tagName = "video";
    defaultStyle = "position: fixed;top: 0;left: 0;width: 100%;height: 100%;z-index: -1;object-fit: cover;";
    defaultParams = "autoplay muted loop ";

    constructor() {
        super();

        this.styles = [];
        this.params = [];
        this.innerComponents = []
    }

    addParameter = function(key, value) {
        this.params.push({
            key: key,
            value: value
        })
    }

    addStyle = function(key, value) {
        this.styles.push({
            key: key,
            value: value
        })
    }

    setSource(source) {
        if (typeof source == "string") {
            let component = new SourceComponent();
            component.setSrc(source)
            this.addInnerComponent(component);
        } else {
            this.addInnerComponent(source);
        }
    }

    addInnerComponent = function(component) {
        this.innerComponents.push(component)
    }

    getAsHtml = function() {
        let parameters = this.defaultParams;
        this.params.forEach(element => {
            if (element.value != "") {
                parameters += `${element.key}="${element.value}" `;
            } else {
                parameters += `${element.key} `;
            }
        });

        let stylesStr = this.defaultStyle;
        this.styles.forEach(element => {
            stylesStr += `${element.key}: ${element.value};`;
        });

        let innerElements = ""
        this.innerComponents.forEach(element => {
            innerElements += `${element.getAsHtml()}\n`;
        });

        return `<${this.tagName} style="${stylesStr}" ${parameters}>${innerElements}</${this.tagName}>`;
    }
}