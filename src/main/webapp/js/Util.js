/**
 * 
 */
TOKEN = "";
//URL = "http://172.20.10.4:8080/PhulmooServer/rest/phulmooUser/";
URL="rest/phulmooUser/";
//URL2="http://localhost:8080/PhulmooServer/rest/phulmooTransaction/";

FN_TOKEN = "token";
FN_PRODUCT_LIST = "productList";
FN_REGISTER="register";
FN_PRODUCT_DETAIL="productDetail";
USERNAME="Chandershekhar";
FN_INSERT_ORDER = "insertOrder";
FN_CONFIRM_ORDER="confirmOrder";
FN_ORDER_LIST="orderList";
FN_ORDER_DETAIL="orderDetail";
FN_BILLING_INFO="generateBillingInfo";

function checkUser(){
	/*if (sessionStorage.getItem("tkn")) { //check token validity
		var userInfoBtn = document.getElementById('user-info-btn');
		$("#user-info-btn").html(sessionStorage.getItem("fname") + ' <span class="caret"></span>');
		userInfoBtn.style.visibility = 'visible';
		document.getElementById('unlogged-div').style.display = 'none';
		document.getElementById('logged-div').style.display = 'block';
	} else {*/
		window.location.href = 'login.html';
	//}
}
function loadUserOnRefresh() {
	if (sessionStorage.getItem("tkn")) { //check token validity
		if(document.getElementById('unlogged-div')!=null){
		var userInfoBtn = document.getElementById('user-info-btn');
		$("#user-info-btn").html(sessionStorage.getItem("fname") + ' <span class="caret"></span>');
		userInfoBtn.style.visibility = 'visible';
		document.getElementById('unlogged-div').style.display = 'none';
		document.getElementById('logged-div').style.display = 'block';
		}
	}
}
function parseQueryString() {

    var str = window.location.search;
    var objURL = {};

    str.replace(
        new RegExp( "([^?=&]+)(=([^&]*))?", "g" ),
        function( $0, $1, $2, $3 ){
            objURL[ $1 ] = $3;
        }
    );
    return objURL;
}