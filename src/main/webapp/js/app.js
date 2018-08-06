var app = angular.module('Phulmoo', [ 'ngRoute', 'ui.bootstrap' ]);
// app.constant("moment", moment);
app
	.config(function($routeProvider) {
		$routeProvider

			.when('/home',
				{
					templateUrl : 'pages/home.html',
					controller : 'HomeController'
				})

			.when('/catalogs',
				{
					templateUrl : 'pages/catalogs.html',
					controller : 'CatalogsController'
				})

			.when('/aboutus',
				{
					templateUrl : 'pages/aboutUs.html',
					controller : 'AboutUsController'
				})
				.when('/signUp',
				{
					templateUrl : 'pages/signUp.html',
					controller : 'RegisterController'
				})
			.otherwise({
				redirectTo : '/home'
			});
	});

$.validator
	.addMethod(
		'ipChecking',
		function(value) {
			if (value && value.length > 0) {
				var ipformat = /^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$/;
				return value.match(ipformat);
			}
			else
				return true;

		}, 'Enter a valid IP address');