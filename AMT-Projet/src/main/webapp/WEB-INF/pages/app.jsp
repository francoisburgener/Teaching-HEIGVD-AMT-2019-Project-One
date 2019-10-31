<html>
  <body>
    <h2>Connected with : ${user.username}</h2>
    <h2>Trips of ${user.username}</h2>
    <table>
      <c:forEach items="${trips}" var="trip">
        <tr>
          <td>${trip.idCountry} ${trip.countryName}</td>
          <td>${trip.visited}</td>
          <td>${trip.date}</td>
        </tr>
      </c:forEach>
    </table>
  </body>
</html>
