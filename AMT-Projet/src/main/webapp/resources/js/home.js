function displayCountries() {
  const mainContainerElem = document.getElementById('main-container');

  if (tripsList.length < 1) {
    mainContainerElem.innerHTML =
        '<h1 class="title has-text-centered">No current trips</h1>\
        <h2 class="subtitle has-text-centered">Don\'t be shy, create a new trip with the green button</h2>';
    return;
  }
  let count = 1;
  let todisplay = tripsList.reduce((acc, obj) => {
    let cardDivs = `<div class="column is-3">
              <div class="card">
                <header class="card-header">
                  <p class="card-header-title">
                    ${countriesList[obj.idCountry - 1].name} 
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
                    onblur="editDate(${obj.idTrip})"
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
                    <footer class="card-footer">
                    <p class="card-footer-item">${reasonsList[obj.idReason - 1].name}</p>
                    </footer>
              </div>
              </div>`;
    let separator = '';
    if (count % 4 === 0) {
      separator = '</div><div class="columns">';
    }
    count++;

    return acc + cardDivs + separator;
  }, '<div class="columns">');
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
async function addToList() {
  const e = document.getElementById('country-select');
  const countryChoice = e.options[e.selectedIndex].value;

  const el = document.getElementById('reason-select');
  const reasonChoice = el.options[el.selectedIndex].value;

  const dateChoice = document.getElementById('input-date').value;

  let params = "idCountry=" + countryChoice + "&idReason="+ reasonChoice + "&date=" + dateChoice + "&visited=" + false + "&action=POST";

  try {
    const resp = await axios({
      method: "POST",
      url: "home",
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      data : params
    });

    toasty("created");

    console.log(resp);
    tripsList.unshift({
      "idTrip": resp.data,
      "idCountry": countryChoice,
      "idReason": reasonChoice,
      "date": dateChoice,
      "visited": false
    });
    displayCountries();
    modalSwitch();
  } catch (e) {

    toasty("notcreated");
    console.error(e);
  }
}

async function deleteOfList(id) {

  let trip = tripsList.find(obj => obj.idTrip === id);

  let params = "idTrip="+trip.idTrip+"&idCountry=" + trip.idCountry + "&date=" + trip.date + "&visited=" + trip.visited + "&action=DELETE";
  try {
    const resp = await axios({
      method: "POST",
      url: "home",
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      data : params
    });

    toasty("deleted");
    tripsList = tripsList.filter(obj => obj.idTrip !== id);
    displayCountries();
  } catch (e) {
    console.error(e);
  }
}

async function editDate(id) {
  const dateChoice = document.getElementById(`input-date-${id}`).value;

  let trip = tripsList.find(obj => obj.idTrip === id);

  let params = "idTrip="+trip.idTrip+"&idCountry=" + trip.idCountry + "&date=" + dateChoice + "&visited=" + trip.visited + "&action=UPDATE";
  try {
    const resp = await axios({
      method: "POST",
      url: "home",
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      data : params
    });

    toasty("edited");

    console.log(resp);

    tripsList.map(obj => {
      if (obj.idTrip === id) {
        obj.date = dateChoice;
      }
    });
    displayCountries();

  } catch (e) {
    console.error(e);
  }
}

async function toggleVisited(id) {

  let trip = tripsList.find(obj => obj.idTrip === id);

  let params = "idTrip="+trip.idTrip+"&idCountry=" + trip.idCountry + "&date=" + trip.date + "&visited=" + !trip.visited + "&action=UPDATE";
  try {
    const resp = await axios({
      method: "POST",
      url: "home",
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      data : params
    });

    console.log(resp);

    tripsList.map(obj => {
      if (obj.idTrip === id) {
        obj.visited = !obj.visited;
      }
    });

    toasty("edited");

    displayCountries();
  } catch (e) {
    console.error(e);
  }
}