function displayCountries() {
    const mainContainerElem = document.getElementById('main-container');

    if (countriesList.length < 1) {
      mainContainerElem.innerHTML =
        '<h1 class="title has-text-centered">No current trips</h1>\
        <h2 class="subtitle has-text-centered">Don\'t be shy, create a new trip with the green button</h2>';
      return;
    }

    let count = 1;
    let todisplay = countriesList.reduce((acc, obj) => {
      let cardDivs = `<div class="column is-3">
              <div class="card">
                <header class="card-header">
                  <p class="card-header-title">
                    ${obj.countryName}
                  </p>
                  <a href="#" class="card-header-icon" aria-label="more options">
                    <span class="icon">
                    </span>
                  </a>
                  <div class="card-header-icon">
                    <button class="button is-small is-danger is-outlined" onclick="deleteOfList(${
                      obj.idTrip
                    })">

                    <span class="icon is-small">
                      <i class="fas fa-times"></i>
                    </span>
                  </button>
                </header>
                <div class="card-content">
                  <div class="content">
                    <label class="label"> Date trip</label>

                    <input
                    id="input-date-${obj.idTrip}"
                    class="input" type="date"
                    onchange="editDate(${obj.idTrip})"
                    value=${obj.date} />
                  </div>
                  <div class="content">
                    <button class="button is-link ${
                      obj.visited === false ? 'is-outlined' : ''
                    }" onclick="toggleVisited(${obj.idTrip})">
                      <span class="icon is-small">
                        <i class="fas fa-check"></i>
                      </span>
                      <span>Visited</span>
                    </button>
                  </div>
                </div>
              </div>
              </div>`;
      let separator = '';
      if (count % 4 === 0) {
        separator = '</div><div class="columns is-desktop">';
      }
      count++;

      return acc + cardDivs + separator;
    }, '<div class="columns is-desktop">');
    mainContainerElem.innerHTML = todisplay;
  }

  displayCountries();

  function modalSwitch() {
    if (modalState) {
      modalState = false;
      modalelem.classList.remove('is-active');
      // htmlElem.classList.remove("is-clipped");
    } else {
      modalState = true;
      modalelem.classList.add('is-active');
      // htmlElem.classList.add("is-clipped");
    }
  }

  /* Adds a new country to list */
  function addToList() {
    const e = document.getElementById('country-select');
    const countryChoice = e.options[e.selectedIndex].text;
    const dateChoice = document.getElementById('input-date').value;

    countriesList.unshift({
      "idTrip": 99,
      "countryName": countryChoice,
      "date": dateChoice,
      "visited": false
    });

    console.log(countriesList);

    displayCountries();
    modalSwitch();
  }

  function deleteOfList(id) {
    countriesList = countriesList.filter(obj => obj.idTrip !== id);
    displayCountries();
  }

  function editDate(id) {
    const dateChoice = document.getElementById(`input-date-${id}`).value;

    countriesList.map(obj => {
      if (obj.idTrip === id) {
        obj.date = dateChoice;
      }
    });
    displayCountries();
  }

  function toggleVisited(id) {
    countriesList.map(obj => {
      if (obj.idTrip === id) {
        obj.visited = !obj.visited;
      }
    });
    displayCountries();
  }