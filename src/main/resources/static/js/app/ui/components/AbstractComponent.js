export class AbstractComponent {
    styles = null;
    params = null;
    innerComponents = null;

    constructor() {
        if (this.constructor === AbstractComponent) {
            throw new Error(`Class ${this.constructor.name} is abstract`);
        }
    }

    addParameter = function(key, value) {
        throw new Error("Not yet implemented");
    }

    addStyle = function(key, value) {
        throw new Error("Not yet implemented");
    }

    addInnerComponent = function(component) {
        throw new Error("Not yet implemented");
    }

    getAsHtml = function() {
        throw new Error("Not yet implemented");
    }
} 