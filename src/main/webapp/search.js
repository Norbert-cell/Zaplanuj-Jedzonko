const API_URL = 'http://localhost:8080/jsonRecipe';
const searchElement = document.querySelector("#inputRecipe")
const tBody = document.querySelector("tbody");
const tr = tBody.querySelector("tr");

function apiList() {
    return fetch(API_URL)
        .then(
        function (resp) {
            if(!resp.ok) {
                alert('Wystąpił błąd! Otwórz devtools i zakładkę Sieć/Network, i poszukaj przyczyny');
            }
            return resp.json();
        }
    )
}

searchElement.addEventListener("keyup", function (e) {
    let userInput = e.target.value.toLowerCase();
    let trElm = document.querySelectorAll(".table tbody tr");


    trElm.forEach(function (tr) {
        if (tr.textContent.toLowerCase().replace(" ","").indexOf(userInput.replace(" ", "")) != -1) {
            tr.closest("tr").style.display = 'block';
            tr.closest("tr").className = 'd-flex';
        } else {
            tr.closest("tr").style.display = 'none';
            tr.closest("tr").className = '';

        }
    })
});

function renderRecipe(id, name, description) {
    const trElm = document.createElement("tr");
    trElm.className = "d-flex";
    trElm.id = "recipeBody";
    trElm.style.display = 'block';
    tBody.appendChild(trElm);
    trElm.addEventListener("mouseover", function (e) {
        e.preventDefault();
        trElm.style.background = '#f9f9f9';
    })
    trElm.addEventListener("mouseout", function (e) {
        e.preventDefault();
        trElm.style.background = '';
    })

    const thElm = document.createElement("th");
    thElm.scope = 'row';
    thElm.className ='col-1'
    thElm.innerText = id;
    trElm.appendChild(thElm);

    const tdElm = document.createElement("td");
    tdElm.className ='col-5';
    tdElm.name = "nameId";
    tdElm.innerText = name;
    trElm.appendChild(tdElm);

    const tdElm2 = document.createElement("td");
    tdElm2.className = 'col-5';
    tdElm2.innerText = description;
    trElm.appendChild(tdElm2);

    const tdElm3 = document.createElement("td");
    tdElm3.className= 'col-1';
    trElm.appendChild(tdElm3);

    const a = document.createElement('a');
    a.href = '/recipes/details?recipeId=' + id;
    a.className = 'btn btn-info rounded-0 text-light';
    a.innerText = 'Szczegóły';
    tdElm3.appendChild(a);

    a.addEventListener("mouseover", function (e) {
        e.preventDefault();
        a.style.height = '42px';
        a.style.width = '99px';
    })
    a.addEventListener('mouseout', function (e) {
        e.preventDefault();
        a.style.height = '38px';
        a.style.width = '97.02px';
    })

}

document.addEventListener('DOMContentLoaded', function() {
    apiList().then(
        function (response) {
            response.forEach(
                function (response) {
                    renderRecipe(response.id, response.name, response.description);
                }
            )
        })

});




