function validateDate() {
 let checkIn = document.forms["bookingForm"]["dateCheckIn"].value;
    let checkOut = document.forms["bookingForm"]["dateCheckOut"].value;
    let resultCheck_in = document.getElementById("warnCheck_in");
    let resultCheck_out = document.getElementById("warnCheck_out");
    let inDate = Date.parse(checkIn);
    let outDate = Date.parse(checkOut);
    let currentDate = Date.now();
    if (inDate <= currentDate) {
        resultCheck_in.style.visibility = "visible" ;
        return false;
    } else if (inDate >= outDate) {
        resultCheck_in.style.visibility = "hidden" ;
        resultCheck_out.style.visibility = "visible" ;
        return false;
    }

}