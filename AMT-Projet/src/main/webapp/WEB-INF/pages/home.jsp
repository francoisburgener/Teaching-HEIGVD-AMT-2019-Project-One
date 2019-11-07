<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Countries Todo</title>
  <link
          rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.5/css/bulma.min.css"
  />
  <script src="<c:url value="/resources/js/home.js" />" defer></script>
  <script
          defer
          src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"
  ></script>

  <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.19.0/axios.js" defer></script>

  <!-- <link rel="stylesheet" href="style.css" /> -->
</head>
<body>
<!-- MODAL -->
<div id="modal-create" class="modal">
  <div class="modal-background" onclick="modalSwitch()"></div>
    <div class="modal-card">
      <header class="modal-card-head">
        <p class="modal-card-title">Plan a new trip!</p>
        <button
                class="delete"
                aria-label="close"
                onclick="modalSwitch()"
        ></button>
      </header>
      <section class="modal-card-body">
        <div class="field">
          <label class="label">Country</label>
          <div class="field">
            <div class="control has-icons-left">
              <div class="select" style="width: 100%;">
                <select id="country-select" style="width: 100%;">
                </select>
              </div>
              <div class="icon is-small is-left">
                <i class="fas fa-globe"></i>
              </div>
              <p class="help">Choose a country you wish to visit.</p>
            </div>
          </div>
        </div>

        <div class="field">
          <label class="label">Date</label>
          <div class="control">
            <input id="input-date" class="input" type="date" />
          </div>
          <p class="help">Pick some date for your travel</p>
        </div>
      </section>
      <footer class="modal-card-foot">
        <button class="button is-success" onclick="addToList()">
          Create
        </button>
        <button class="button" onclick="modalSwitch()">Cancel</button>
      </footer>
    </div>
</div>

<!-- TOP -->
<section class="section">
  <div class="container">
    <nav class="level">
      <!-- Left side -->
      <div class="level-left">
        <div class="level-item">
          <p class="subtitle is-5"><strong>Countries</strong> todos</p>
        </div>
      </div>

      <div class="field has-addons">
        <p class="control">
          <input
                  class="input"
                  type="text"
                  placeholder="Find an exciting trip"
          />
        </p>
        <p class="control">
          <button class="button">
            <span>Search</span>
            <span class="icon is-small">
                  <i class="fas fa-search"></i>
                </span>
          </button>
        </p>
      </div>

      <!-- Right side -->
      <div class="level-right">
        <p class="level-item">Connected as: <a href="home/profile"> ${user.fullname}</a></p>
        <p class="level-item">
          <a class="button is-danger is-outlined" href="signout">Sign out</a>
        </p>
      </div>
    </nav>
  </div>
</section>

<!-- TITLE -->
<section>
  <div class="container">
    <h1 class="title">Your travel plans</h1>
    <h2 class="subtitle">
      Here are all <strong>amazing</strong> countries you plan to visit:
    </h2>
  </div>
</section>

<!-- MENU -->
<section>
  <div class="container">
    <nav class="level">
      <!-- Left side -->
      <div class="level-left">
        <div class="level-item"></div>
      </div>

      <!-- Right side -->
      <div class="level-right">
        <p class="level-item">
          <button class="button is-success" onClick="modalSwitch()"
          >Plan an awesome new trip!</button
          >
        </p>
      </div>
    </nav>
  </div>
</section>

<section class="section">
  <!-- Main -->
  <div id="main-container" class="container"></div>
</section>
<section>
  <!-- PAGINATION -->
  <div class="container">
    <nav class="pagination" role="navigation" aria-label="pagination">
      <a class="pagination-previous" title="This is the first page"
         href="?page=${page - 1}"
      ${page <= 1 ? "disabled" : ""}
      >Previous</a
      >
      <a class="pagination-next" href="?page=${page + 1}">Next page</a>
      <ul class="pagination-list">
        <li>
          <a
                  class="pagination-link is-current"
                  aria-label="Page 1"
                  aria-current="page"
          >${page}</a
          >
        </li>
      </ul>
    </nav>
  </div>

</section>

<script>
  const modalelem = document.getElementById('modal-create');
  const htmlElem = document.getElementsByTagName('html');

  let modalState = false;
  /*let countriesList = [
    { id: 1, name: 'Deutschland', date: '2011-09-29', visited: true },
    { id: 2, name: 'France', date: '2011-09-29', visited: false },
    { id: 3, name: 'Netherlands', date: '2011-09-29', visited: false },
    { id: 4, name: 'China', date: '2011-09-29', visited: true },
    { id: 5, name: 'United States', date: '2011-09-29', visited: false },
    { id: 6, name: 'Brasil', date: '2011-09-29', visited: false },
    { id: 7, name: 'Argentina', date: '2011-09-29', visited: false }
  ];*/

  let tripsList = ${trips} /*[
        { "idTrip": 1, "countryName": 'Deutschland', "date": '2011-09-29', "visited": true },
        { "idTrip": 2, "countryName": 'France', "date": '2011-09-29', "visited": false },
        { "idTrip": 3, "countryName": 'Netherlands', "date": '2011-09-29', "visited": false },
        { "idTrip": 4, "countryName": 'China', "date": '2011-09-29', "visited": true },
        { "idTrip": 5, "countryName": 'United States', "date": '2011-09-29', "visited": false },
        { "idTrip": 6, "countryName": 'Brasil', "date": '2011-09-29', "visited": false },
        { "idTrip": 7, "countryName": 'Argentina', "date": '2011-09-29', "visited": false }
      ];*/
  let countriesList = ${countries}

  function selectValue() {
    var selectList = document.getElementById("country-select");

    for(var i = 0; i < countriesList.length; ++i){
      let option = document.createElement("option");
      option.value = countriesList[i].id;
      option.text = countriesList[i].name;
      selectList.appendChild(option);
    }
  }

  selectValue();
</script>
<script>

</script>
</body>
</html>
