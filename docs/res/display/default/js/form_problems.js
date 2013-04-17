$(document).ready(function() {
	$('form .f_problem').focusin(function(event){
		$(this).removeClass('f_problem');
	});
	$('form .f_problem input[type="radio"]').click(function(event){
		$(this).parents('.f_problem').first().removeClass('f_problem');
	});
	$('form .f_problem input[type="file"]').click(function(event){
		$(this).parents('.f_problem').first().removeClass('f_problem');
	});
});