let currentAuthMode = "sign_in";

function sendAuthMessage(request, callback) {
    return fetch("http://localhost:8080/app/auth/sign_in", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json; charset=utf-8'
        },
        body: JSON.stringify(request)
    })
        .then(response => response.json())
        .then(json => callback(json));
}

export function auth(callback) {
    document.querySelectorAll(".text_input").forEach(elem => {
        elem.addEventListener('focus', function() {
            let id = elem.id;
            if (id.includes("up")) {
                currentAuthMode = "sign_up";
            } else if (id.includes("in")) {
                currentAuthMode = "sign_in";
            }
        });
    });

    document.querySelector("#auth_done").addEventListener("click", function(event) {
        if (currentAuthMode === "sign_in") {
            let request = {
                email: document.querySelector("#sign_in_email").value,
                password: document.querySelector("#sign_in_password").value
            };

            sendAuthMessage(request, callback);

            return true;
        } else if (currentAuthMode === "sign_up") {
            let password = document.querySelector("#sign_up_password").value;
            let repeatPassword = document.querySelector("#sign_up_repeat_password").value;

            if (password === repeatPassword) {
                let request = {
                    email: document.querySelector("#sign_up_email").value,
                    nickname: document.querySelector("#sign_up_nickname").value,
                    password: password
                };

                sendAuthMessage(request, callback);

                return true;
            }
         }

         return false;
    });
}