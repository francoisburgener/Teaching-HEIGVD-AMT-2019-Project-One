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

    <script src="<c:url value="/resources/js/toasty.js" />" defer></script>
    <script src="<c:url value="/resources/js/home.js" />" defer></script>

    <link
            rel="stylesheet"
            href="<c:url value="/resources/css/autocomplete.css" />"
    />

    <link
            rel="stylesheet"
            href="<c:url value="/resources/css/toast.css" />"
    />

    <script
            defer
            src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"
    ></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.19.0/axios.js" defer></script>

    <!-- <link rel="stylesheet" href="style.css" /> -->
</head>
<body style="background-color: hsl(0, 0%, 99%)">
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

            <div class="field">
                <label class="label">Your objective</label>
                <div class="field">
                    <div class="control has-icons-left">
                        <div class="select" style="width: 100%;">
                            <select id="reason-select" style="width: 100%;">
                            </select>
                        </div>
                        <div class="icon is-small is-left">
                            <i class="fas fa-globe"></i>
                        </div>
                        <p class="help">What is the purpose of this trip.</p>
                    </div>
                </div>
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
            <!-- Left side -->
            <div class="level-left">
                <div class="level-item">
                    <p class="subtitle is-5">
                        <span class="icon is-large"><i class="fas fa-map-marked-alt"></i></span>
                        <strong>Countries</strong> todos</p>
                </div>
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
<section style="padding: 1em">
    <div class="container">
        <nav class="level">
            <!-- Left side -->
            <div class="level-left">
                <div class="level-item">
                    <a class="button is-primary is-outlined"
                       href="${pageContext.request.contextPath}/top"
                    >
                <span class="icon">
<i class="fas fa-lightbulb"></i>
                </span>
                        <span>Inspiration</span></a>
                </div>
            </div>

            <form autocomplete="off" method="get" name="search-form" action="${pageContext.request.contextPath}/home">
                <div class="field has-addons">

                    <p class="control autocomplete">
                        <input
                                id="search-input"
                                class="input"
                                type="text"
                                placeholder="Find an exciting trip"
                                name="country-search"
                        />
                    </p>
                    <p class="control">
                        <button class="button" type="submit">
                            <span>Search</span>
                            <span class="icon is-small">
                  <i class="fas fa-search"></i>
                </span>
                        </button>
                    </p>
                </div>
            </form>

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

<section class="section" style="min-height: 450px">
    <!-- Main -->
    <div id="main-container" class="container"></div>
</section>
<section>
    <!-- PAGINATION -->
    <div class="container">
        <nav class="pagination" role="navigation" aria-label="pagination">
            <a class="pagination-previous" title="This is the first page"
               href="?page=${page - 1}${countryName}"
            ${page <= 1 ? "disabled" : ""}
            >Previous</a
            >
            <a class="pagination-next" href="?page=${page + 1}${countryName}">Next page</a>
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
<script src="<c:url value="/resources/js/autocomplete.js" />"></script>

<script>
    const modalelem = document.getElementById('modal-create');
    const htmlElem = document.getElementsByTagName('html');

    let modalState = false;


    let tripsList = ${trips};


    let countriesList = ${countries};
    let reasonsList = ${reasons};


    function selectValue(fieldName, list) {
        const selectList = document.getElementById(fieldName);

        for(var i = 0; i < list.length; ++i){
            let option = document.createElement("option");
            option.value = list[i].id;
            option.text = list[i].name;
            selectList.appendChild(option);
        }
    }

    selectValue("country-select", countriesList);
    selectValue("reason-select", reasonsList);
    autocomplete(document.getElementById("search-input"), countriesList.map(obj => obj.name));

</script>

<!-- toast -->
<div id="toasty">Some text some message..</div>


</body>
</html>
