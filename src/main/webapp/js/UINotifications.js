/**
 * @author Chandershekhar
 * 
 * 
 * UINotifications.js is used for showing progress bar and alert notifications
 */

$(document).ready(
	// This is used for marking the current menu selection and close the menu on
	// navigation selected when in reduced window mode or mobile device
	function() {
		loadUserOnRefresh();

		$('.nav li a').click(function(e) {

			$('.nav li.active').removeClass('active');

			var $parent = $(this).parent();
			$parent.addClass('active');
		});

		$("body").click(
			function(event) {
				// only do this if navigation is visible, otherwise you see jump in navigation
				// while collapse() is called
				if ($(".navbar-collapse").is(":visible")
					&& $(".navbar-toggle").is(":visible")) {
					$('.navbar-collapse').collapse('toggle');
				}
			});
	});

// $('.navbar-collapse a').click(function (e) {
// $('.navbar-collapse').collapse('toggle');
// });
function showLoadMask() {
	/*$('#ng-view').hide();
	$('#ng-load').show();
	$('#ng-reload').hide();*/
}

function hideLoadMask() {
	/*$('#ng-view').show();
	$('#ng-load').hide();
	$('#ng-reload').hide();*/
}

function showPageRetry() {
	/*$('#ng-view').hide();
	$('#ng-load').hide();
	$('#ng-reload').show();*/
}

function setDefaults() {
	/*$('#ng-view').hide();
	$('#ng-load').hide();
	$('#ng-reload').hide();*/
}
var TIME = 5000;
var timeOutSuccess = setTimeout(dismissSuccessAlerts, TIME);
var timeOutInfo = setTimeout(dismissInfoAlerts, TIME);
var timeOutWarning = setTimeout(dismissWarningAlerts, TIME);
var timeOutError = setTimeout(dismissErrorAlerts, TIME);
function showSuccessAlert(message) {
	clearTimeout(timeOutSuccess);
	var alertDiv = $('.gpalerts').find('.alert-success');
	alertDiv.find('.message').html("<strong>Success!</strong> " + message);
	alertDiv.show();
	timeOutSuccess = setTimeout(dismissSuccessAlerts, TIME);
}
function showInformationAlert(message) {
	clearTimeout(timeOutInfo);
	var alertDiv = $('.gpalerts').find('.alert-info');
	alertDiv.find('.message').html("<strong>Information!</strong> " + message);
	alertDiv.show();
	timeOutInfo = setTimeout(dismissInfoAlerts, TIME);
}
function showWarningAlert(message) {
	clearTimeout(timeOutWarning);
	var alertDiv = $('.gpalerts').find('.alert-warning');
	alertDiv.find('.message').html("<strong>Warning!</strong> " + message);
	alertDiv.show();
	timeOutWarning = setTimeout(dismissWarningAlerts, TIME);
}
function showErrorAlert(message) {
	clearTimeout(timeOutError);
	var alertDiv = $('.gpalerts').find('.alert-danger');
	alertDiv.find('.message').html("<strong>Error!</strong> " + message);
	alertDiv.show();
	timeOutError = setTimeout(dismissErrorAlerts, TIME);
}
function dismissSuccessAlerts() {
	var alertDiv = $('.gpalerts').find('.alert-success');
	alertDiv.hide();
	clearTimeout(timeOutSuccess);
}
function dismissInfoAlerts() {
	var alertDiv = $('.gpalerts').find('.alert-information');
	alertDiv.hide();
	clearTimeout(timeOutInfo);
}
function dismissWarningAlerts() {
	var alertDiv = $('.gpalerts').find('.alert-warning');
	alertDiv.hide();
	clearTimeout(timeOutWarning);
}
function dismissErrorAlerts() {
	var alertDiv = $('.gpalerts').find('.alert-danger');
	alertDiv.hide();
	clearTimeout(timeOutError);
}