 var cardCompany = function() {
 	//var number =document.getElementById('cardNumber').value;
 	var number =document.getElementById('cardNumber').value;
 	var re = new RegExp("^4");
            
  if (number.match(re) != null) {
    document.getElementById('message').style.color = 'green';
    document.getElementById('message').innerHTML = 'VISA';
  } else {
    document.getElementById('message').style.color = 'red';
    document.getElementById('message').innerHTML = 'MasterCard';
  }
}