$(document).ready(function() {
	$('div.og_widecontent').mouseenter(function() {
		$(this).addClass('lit');
		$(this).find('div.og_overlay').animate({ width: '0' }, 160);
	}).mouseleave(function() {
		$(this).removeClass('lit');
		$(this).find('div.og_overlay').animate({ width: '328px' }, 160);
	}).append('<div class="og_overlay"></div>');
});