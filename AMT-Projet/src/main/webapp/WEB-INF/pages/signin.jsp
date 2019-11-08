<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Countries todos</title>
  <link
          rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.5/css/bulma.min.css"
  />
  <script
          defer
          src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"
  ></script>

  <link rel="stylesheet" href="resources/css/style.css" />
</head>

<body style="background-image: url('https://source.unsplash.com/featured/1600x900/?travel?asia?holidays'); background-size:cover;"
      class="has-background-grey"
>
<!-- TOP -->
<section class="section">
  <div class="container">
    <nav class="level">
      <!-- Left side -->
      <div class="level-left">
        <div class="level-item">
          <p class="subtitle is-5 has-text-white">
            <span class="icon is-large"><i class="fas fa-map-marked-alt"></i></span>
            <strong class="has-text-white">Countries</strong> todos</p>
        </div>
      </div>
    </nav>
  </div>
</section>

<!-- FORM -->

<section class="section">
  <div class="container">
    <h1 class="title has-text-white" style="font-size:3.2em">
      Sign in or register and start your journey
    </h1>
    <h2 class="subtitle has-text-white" style="font-size:2em">
      Plan your travels to countries you wish to visit with simplicity.
    </h2>
    <div class="card" style="border-radius: 0.3em">
      <header class="card-header">
        <div class="tabs">
          <ul>
            <li id="tab-sign-in" class="is-active">
              <a onClick="tabSwitch(true)">Sign in</a>
            </li>
            <li id="tab-register">
              <a onClick="tabSwitch(false)">Register</a>
            </li>
          </ul>
        </div>
      </header>
      <div class="card-content">
        <!-- sign in form -->
        <form
                method="post"
                action="signin"
                id="sign-in-form"
                class="content"
        >
          <!-- username-->
          <div class="field">
            <label class="label">Username</label>
            <div class="control has-icons-left has-icons-right">
              <input
                      name="usernameS"
                      class="input"
                      type="text"
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

          <!-- password -->
          <div class="field">
            <label class="label">Password</label>
            <p class="control has-icons-left">
              <input
                      name="passwordS"
                      class="input"
                      type="password"
                      placeholder="Password"
              />
              <span class="icon is-small is-left">
                    <i class="fas fa-lock"></i>
                  </span>
            </p>
            <p class="help is-danger">${error}</p>
          </div>

          <!-- button -->
          <div class="control">
            <button class="button is-link">Sign in</button>
          </div>
        </form>

        <!-- register form -->
        <form
                method="post"
                action="register"
                id="register-form"
                class="content"
                style="display: none"
        >
          <!-- username-->
          <div class="field">
            <label class="label">Username</label>
            <div class="control has-icons-left has-icons-right">
              <input
                      name="username"
                      class="input"
                      type="text"
                      placeholder="Enter your username here"
              />
              <span class="icon is-small is-left">
                    <i class="fas fa-user"></i>
                  </span>
            </div>
            <p class="help is-danger">${errors['username']}</p>
          </div>

          <!-- fullname -->
          <div class="field">
            <label class="label">Fullname</label>
            <div class="control ">
              <input
                      name="fullname"
                      class="input"
                      type="text"
                      placeholder="Enter your full name here"
              />
            </div>
            <p class="help is-danger">${errors['fullname']}</p>
          </div>

          <!-- email -->
          <div class="field">
            <label class="label">Email</label>
            <div class="control has-icons-left has-icons-right">
              <input
                      name="email"
                      class="input"
                      type="email"
                      placeholder="Email input"
              />
              <span class="icon is-small is-left">
                    <i class="fas fa-envelope"></i>
                  </span>
              <span class="icon is-small is-right">
                    <i class="fas fa-exclamation-triangle"></i>
                  </span>
            </div>
            <p class="help is-danger">${errors['email']}</p>
          </div>

          <!-- password -->
          <div class="field">
            <label class="label">Password</label>
            <p class="control has-icons-left">
              <input
                      name="password"
                      class="input"
                      type="password"
                      placeholder="Enter your password here"
              />
              <span class="icon is-small is-left">
                    <i class="fas fa-lock"></i>
                  </span>
            </p>
          </div>

          <div class="field">
            <label class="label">Confirm password</label>
            <p class="control has-icons-left">
              <input
                      name="confirm-password"
                      class="input"
                      type="password"
                      placeholder="Confirm your password"
              />
              <span class="icon is-small is-left">
                    <i class="fas fa-lock"></i>
                  </span>
            </p>
          </div>
          <p class="help is-danger">${errors['password']}</p>
          <!-- button -->
          <div class="control">
            <button class="button is-link">Submit</button>
          </div>
          <p class="help is-danger">${sqlError}</p>
        </form>
      </div>
    </div>
  </div>
</section>
<script>
  const tabsign = document.getElementById('tab-sign-in');
  const tabregister = document.getElementById('tab-register');
  const register = document.getElementById('register-form');
  const signin = document.getElementById('sign-in-form');
  let signinActive = true;

  window.onload = function () {
    tabSwitch(${tabSelect});
  };

  function tabSwitch(choice) {
    if (choice) {
      if (signinActive === false) {
        tabsign.classList.add('is-active');
        tabregister.classList.remove('is-active');

        register.style.display = 'none';
        signin.style.display = 'block';

        signinActive = true;
      }
    } else {
      if (signinActive === true) {
        tabregister.classList.add('is-active');
        tabsign.classList.remove('is-active');

        register.style.display = 'block';
        signin.style.display = 'none';

        signinActive = false;
      }
    }
  }
</script>
</body>
</html>
