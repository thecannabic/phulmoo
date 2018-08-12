angular
		.module('Phulmoo', [])
		.controller(
				'RegistrationLoginController',
				[
						'$scope',
						'$http',
						function($scope, $http) {
							$scope.uname = "";
							$scope.psw = "";

							$scope.login = function() {
								$scope.message = "";
								if (!$scope.uname || $scope.uname.length == 0) {
									$scope.message = "Username is required";
									$('#err-message-div').show();
									return false;
								} else if (!$scope.psw
										&& $scope.psw.length == 0) {
									$scope.message = "Password is required";
									$('#err-message-div').show();
									return false;
								}

								else if ($scope.uname && $scope.psw
										&& $scope.uname.length > 0
										&& $scope.psw.length > 0) {
									$scope.message = "";
									$('#err-message-div').hide();
								} else {
									$scope.message = "Username and password are required";
									$('#err-message-div').show();
									return false;
								}

								var user = $scope.uname;
								var pass = $scope.psw;
								var data = {
									"username" : user,
									"firstName" : "",
									"lastName" : "",
									"email" : "",
									"sex" : "",
									"password" : pass,
									"userId" : "",
									"token" : "",
									"isAdmin" : ""
								};

								$http({
									method : 'POST',
									data : JSON.stringify(data),
									url : URL + FN_TOKEN,
									crossDomain : true,
									headers : {
										'Accept' : 'application/json',
										'Content-Type' : 'application/json'
									},
									dataType : 'json',
								})
										.then(
												function(response) {
													console.log(response);
													var responseCode = response.data.responseCode;
													var responseMessage = response.data.responseMessage;
													if (responseCode == 0) {
														var data = response.data.responseData;
														if (data) {

															if (data.token) {
																TOKEN = data.token;

																sessionStorage
																		.setItem(
																				"usrn",
																				data.username);
																sessionStorage
																		.setItem(
																				"fname",
																				data.firstName);
																sessionStorage
																		.setItem(
																				"tkn",
																				data.token);
																sessionStorage
																		.setItem(
																				"userId",
																				data.userId);

															}
														}
														var vpid = sessionStorage
																.getItem("v-pid");

														if (vpid != null) {
															window.location.href = "catalogDetail.html?pid="
																	+ vpid;
														} else {
															window.location.href = "/Phulmoo";
														}

													} else {
														$scope.message = responseMessage;
														$('#err-message-div')
																.show();
													}
												},
												function(response) {
													console.log(response);
													$scope.message = response;
													$('#err-message-div')
															.show();
												});

							};
							// $scope.getProductDetails();

						} ]);