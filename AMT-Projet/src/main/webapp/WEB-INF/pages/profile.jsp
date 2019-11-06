<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Profile - ${user.fullname}</title>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.5/css/bulma.min.css"
    />
    <script
      defer
      src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"
    ></script>

    <!-- <link rel="stylesheet" href="style.css" /> -->
  </head>
  <body>

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
        <h1 class="title">Profile</h1>
        <h2 class="subtitle">
          Here you can edit your profile
        </h2>
      </div>
    </section>

    <section>
      <div class="container">
        <form
          method="post"
          action="register"
          id="register-form"
          class="content"
        >
          <!-- username-->
          <div class="field">
            <label class="label">Username</label>
            <div class="control has-icons-left has-icons-right Disabled">
              <input
                name="username"
                class="input"
                type="text"
                value="${user.username}"
                disabled
                placeholder="Enter your username here"
              />
              <span class="icon is-small is-left">
                <i class="fas fa-user"></i>
              </span>
              <span class="icon is-small is-right">
                <i class="fas fa-check"></i>
              </span>
            </div>
          </div>

          <!-- fullname -->
          <div class="field">
            <label class="label">Fullname</label>
            <div class="control ">
              <input
                name="fullname"
                class="input"
                type="text"
                value="${user.fullname}"
                placeholder="Enter your full name here"
              />
            </div>
          </div>

          <!-- email -->
          <div class="field">
            <label class="label">Email</label>
            <div class="control has-icons-left has-icons-right">
              <input
                name="email"
                class="input"
                type="email"
                value="${user.email}"
                placeholder="Email input"
              />
              <span class="icon is-small is-left">
                <i class="fas fa-envelope"></i>
              </span>
              <span class="icon is-small is-right">
                <i class="fas fa-exclamation-triangle"></i>
              </span>
            </div>
          </div>

          <!-- button -->
          <div class="control">
            <button class="button is-link">Edit</button>
          </div>
        </form>
      </div>
    </section>

    <script></script>
  </body>
</html>
