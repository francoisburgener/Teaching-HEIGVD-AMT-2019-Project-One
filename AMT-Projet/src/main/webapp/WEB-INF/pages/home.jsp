<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Hello Bulma!</title>
  <link
          rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.5/css/bulma.min.css"
  />
  <script src="<c:url value="/resources/js/home.js" />" defer></script>
  <script
          defer
          src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"
  ></script>

  <!-- <link rel="stylesheet" href="style.css" /> -->
</head>
<body>
<!-- MODAL -->
<div id="modal-create" class="modal">
  <div class="modal-background" onclick="modalSwitch()"></div>
  <form method="" action="">
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
                  <option value=1>Japan</option>
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
  </form>
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
        <p class="level-item">Connected as: <a> ${user.fullname}</a></p>
        <p class="level-item">
          <a class="button is-danger is-outlined">Sign out</a>
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
          <a class="button is-success" onClick="modalSwitch()"
          >Plan an awesome new trip!</a
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
      <a class="pagination-previous" title="This is the first page" disabled
      >Previous</a
      >
      <a class="pagination-next">Next page</a>
      <ul class="pagination-list">
        <li>
          <a
                  class="pagination-link is-current"
                  aria-label="Page 1"
                  aria-current="page"
          >1</a
          >
        </li>
        <li>
          <a class="pagination-link" aria-label="Goto page 2">2</a>
        </li>
        <li>
          <a class="pagination-link" aria-label="Goto page 3">3</a>
        </li>
      </ul>
    </nav>
  </div>

  <p>${trips}</p>
  </br>
  <p>${countries}</p>


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
</script>
</body>
</html>
