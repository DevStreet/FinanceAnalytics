$(document).ready(function() {
	$('li.og_nav_menu').mouseenter(function() {
		$(this).addClass('lit');
		$(this).find('ul').fadeIn(60);
	});
	$('li.og_nav_menu').mouseleave(function() {
		$(this).removeClass('lit');
		$(this).find('ul').fadeOut(60);
	});
});