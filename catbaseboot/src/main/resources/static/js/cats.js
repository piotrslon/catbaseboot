$(document).ready(function () {

	setTimeout(function() {
		$( ".alert" ).remove();
	}, 3000);
	
	$(".add_toy").click(function() {
		if ($(".add_toy_form").hasClass( "hide" )) {
			$(".add_toy_form").removeClass("hide");
		} else {
			$(".add_toy_form").toggle();
		}

    });

});