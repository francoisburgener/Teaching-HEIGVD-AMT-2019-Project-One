function toasty(status) {
// Get the snackbar DIV
    const x = document.getElementById("toasty");

// Add the "show" class to DIV

    switch (status) {
        case "created":
            x.className = "show has-background-success";
            x.innerText = "New trip created";
            break;
        case "notcreated":
            x.className = "show has-background-danger";
            x.innerText = "Creation failed: fill all the fields";
            break;
        case "deleted":
            x.className = "show has-background-info";
            x.innerText = "Deleted";
            break;
        case "edited":
            x.className = "show has-background-info";
            x.innerText = "Saved";
            break;
    }

// After 3 seconds, remove the show class from DIV
    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}

