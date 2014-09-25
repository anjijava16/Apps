/**
 * 
 */
$(document).ready(function() {
	
	$("#name").attr('value', "Enter Name").focus(function() {
		if ($(this).val() == "Enter Name") {
			$(this).attr('value', '');
		}
	}).blur(function() {
		if ($(this).val() == '') {
			$(this).attr('value', "Enter Name");
		}
	});
	
	$("#age").attr('value', "Enter Age").focus(function() {
		if ($(this).val() == "Enter Age") {
			$(this).attr('value', '');
		}
	}).blur(function() {
		if ($(this).val() == '') {
			$(this).attr('value', "Enter Age");
		}
	});
	
	$("#email").attr('value', "Enter email").focus(function() {
		if ($(this).val() == "Enter email") {
			$(this).attr('value', '');
		}
	}).blur(function() {
		if ($(this).val() == '') {
			$(this).attr('value', "Enter email");
		}
	});
	
	$("#mobile").attr('value', "Enter Mobile").focus(function() {
		if ($(this).val() == "Enter Mobile") {
			$(this).attr('value', '');
		}
	}).blur(function() {
		if ($(this).val() == '') {
			$(this).attr('value', "Enter Mobile");
		}
	});
	
	$("#cardno").attr('value', "Enter Credit card number").focus(function() {
		if ($(this).val() == "Enter Credit card number") {
			$(this).attr('value', '');
		}
	}).blur(function() {
		if ($(this).val() == '') {
			$(this).attr('value', "Enter Credit card number");
		}
	});
	
	$("#userName").attr('value', "User Name").focus(function() {
		if ($(this).val() == "User Name") {
			$(this).attr('value', '');
		}
	}).blur(function() {
		if ($(this).val() == '') {
			$(this).attr('value', "User Name");
		}
	});
	
	$("#pwd").attr('value', "Password").focus(function() {
		if ($(this).val() == "Password") {
			$(this).attr('value', '');
		}
	}).blur(function() {
		if ($(this).val() == '') {
			$(this).attr('value', "Password");
		}
	});

});
