/**
 * 
 */
function register() {
	var data = {
		"username" : "",
		"firstName" : "Sunny",
		"lastName" : "",
		"email" : "sunnyj@phulmoo.com",
		"password" : "12345"
	};
	$.ajax({
		type : "POST",
		data : JSON.stringify(data),
		url : URL + FN_REGISTER,
		crossDomain : true,
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		async : true,
		success : function(data, textStatus, jqXHR) {
			console.log(data);
			if (data) {
				alert('registration success please veryfy on sent email');
				document.getElementById('id01').style.display = 'none';

				var userModal = data.responseData;
				if (userModal.token) {
					TOKEN = userModal.token;
					var userInfoBtn = document.getElementById('user-info-btn');
					$("#user-info-btn").html(userModal.firstName + ' <span class="caret"></span>');
					userInfoBtn.style.visibility = 'visible';
					document.getElementById('unlogged-div').style.display = 'none';
					document.getElementById('logged-div').style.display = 'block';

					sessionStorage.setItem("usrn", userModal.username);
					sessionStorage.setItem("fname", userModal.firstName);
					sessionStorage.setItem("tkn", userModal.token);
				}
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(data);
			//alert("an unexpected error occured: " + errorThrown);
			//window.location.href = back + '/login_page.html';
			document.getElementById('id01').style.display = 'none';
		}
		
		
		
	});
	/*$http({
		method : 'POST',
		url : URL + FN_PRODUCT_LIST,
		crossDomain : true,
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		data : "10",
		dataType : 'json',
	})
		.then(
			function mySuccess(response) {
				console.log(response);
				
					if (response.data) {
						var data=response.data;
						document.getElementById('id01').style.display = 'none';

						var userModal = data.responseData;
						if (userModal.token) {
							TOKEN = userModal.token;
							var userInfoBtn = document.getElementById('user-info-btn');
							$("#user-info-btn").html(userModal.firstName + ' <span class="caret"></span>');
							userInfoBtn.style.visibility = 'visible';
							document.getElementById('unlogged-div').style.display = 'none';
							document.getElementById('logged-div').style.display = 'block';

							sessionStorage.setItem("usrn", userModal.username);
							sessionStorage.setItem("fname", userModal.firstName);
							sessionStorage.setItem("tkn", userModal.token);
						}
					
				}
				hideLoadMask();
			},
			function myError(response) {
				hideLoadMask();
				showPageRetry();
			});*/
}
function loadUserOnRefresh() {
	if (sessionStorage.getItem("tkn")) { //check token validity
		var userInfoBtn = document.getElementById('user-info-btn');
		$("#user-info-btn").html(sessionStorage.getItem("fname") + ' <span class="caret"></span>');
		userInfoBtn.style.visibility = 'visible';
		document.getElementById('unlogged-div').style.display = 'none';
		document.getElementById('logged-div').style.display = 'block';
	}
}

function logout() {
	sessionStorage.setItem("usrn", "");
	sessionStorage.setItem("fname", "");
	sessionStorage.setItem("tkn", "");
	TOKEN = "";
	document.getElementById('unlogged-div').style.display = 'block';
	document.getElementById('logged-div').style.display = 'none';
	window.location.reload();
}