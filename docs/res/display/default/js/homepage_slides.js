$(document).ready(function() {
	var startslide = $('#slides .slides_container div').get(0);
	$('#ogw_slide_caption').html($(startslide).find('span').html());
	$("#slides").slides({
		play: 6000,
		pause: 6000,
		hoverPause: true,
		effect: 'fade',
		crossfade: true,
		animationStart: function() {
			$('#ogw_slide_caption').fadeOut('fast');
		},
		animationComplete: function() {
			var c = $('#slides .slides_container div').find(':visible').find('span').html();
			$('#ogw_slide_caption').html(c).fadeIn();
		}
	});
});