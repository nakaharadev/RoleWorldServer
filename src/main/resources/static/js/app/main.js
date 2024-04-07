import { SourceComponent } from "./ui/components/SourceComponent.js";
import { VideoBackgroundComponent } from "./ui/components/VideoBackgroundComponent.js"

function initBackground() {
    let background = new VideoBackgroundComponent();
    background.setSource("https://6bb8b6dc-uiuiui.s3.timeweb.cloud/files/wally/1058100.mp4")

    document.body.innerHTML = background.getAsHtml();
}

function main() {
    initBackground();
}

main()