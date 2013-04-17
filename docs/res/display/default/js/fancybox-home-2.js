$(document).ready(function() {
	$("a[rel='lightbox']").fancybox();
	$("a[rel='video']").fancybox({ type: 'iframe', width: 640, height: 360, padding: 0, showNavArrows: false });
});