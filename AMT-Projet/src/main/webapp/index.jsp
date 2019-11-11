<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>AMT Project One - Countries todo</title>
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
<body>
<section
        class="section hero is-primary"
        style="background-image: url('resources/img/background.jpg');background-repeat: no-repeat; background-size: cover;"
>
  <div class="hero-body">
    <div class="container">
      <h1 class="title is-size-1"><span class="icon is-large"><i class="fas fa-map-marked-alt"></i></span>
        Countries todo</h1>
      <h2 class="subtitle is-size-2 is-uppercase has-text-black-bis	">
        Teaching-HEIGVD-AMT-2019-Project-One
      </h2>
    </div>
  </div>
</section>
<section class="section has-text-centered" style="min-height: calc(51vh - 67px);">
  <div class="container">
    <div class="content columns is-centered">
      <div class="column is-half has-text-justified	">
        <p>
          Welcome to our AMT Project One.
        </p>
        <p>Do you feel the need to travel abroad? Countriestodo allows you to plan
          you trips over the world. Feel free to pin every idea you have, even if it's not for tomorrow.
          Maybe you want to work in London? Or you need some boost in german, why not a linguistic stay?
          Subscribe now and plan it in a few seconds.</p>
      </div>
    </div>
  </div>

  <div class="section">
    <div class="container has-text-centered">
      <a href="${pageContext.request.contextPath}/register" class="button is-primary">
          <span class="icon">
            <i class="fas fa-user-plus"></i>
          </span>
        <span>Register</span>
      </a>

      <a href="${pageContext.request.contextPath}/signin" class="button is-primary is-outlined">
          <span class="icon">
            <i class="fas fa-sign-in-alt"></i>
          </span>
        <span>Sign in</span>
      </a>
    </div>
  </div>
</section>

<footer class="footer" style=" width:100%">
  <div class="content has-text-centered">
    <p>
      <strong>Countries todo</strong> by
      <a href="https://github.com/francoisburgener">Francois Burgener</a>
      and
      <a href="https://github.com/tiagoquin">Tiago Povoa</a>
    </p>
    <p>
      Photography:
      <a
              style='background-color:black;color:white;text-decoration:none;padding:4px 6px;font-family:-apple-system, BlinkMacSystemFont, "San Francisco", "Helvetica Neue", Helvetica, Ubuntu, Roboto, Noto, "Segoe UI", Arial, sans-serif;font-size:12px;font-weight:bold;line-height:1.2;display:inline-block;border-radius:3px'
              href="https://unsplash.com/@anniespratt?utm_medium=referral&amp;utm_campaign=photographer-credit&amp;utm_content=creditBadge"
              target="_blank"
              rel="noopener noreferrer"
              title="Download free do whatever you want high-resolution photos from Annie Spratt"
      ><span style="display:inline-block;padding:2px 3px"
      ><svg
              xmlns="http://www.w3.org/2000/svg"
              style="height:12px;width:auto;position:relative;vertical-align:middle;top:-2px;fill:white"
              viewBox="0 0 32 32"
      >
                <title>unsplash-logo</title>
                <path
                        d="M10 9V0h12v9H10zm12 5h10v18H0V14h10v9h12v-9z"
                ></path></svg></span
      ><span style="display:inline-block;padding:2px 3px"
      >Annie Spratt</span
      ></a
      >
      (free photo from some website, thank you)
    </p>
  </div>
</footer>
</body>
</html>
