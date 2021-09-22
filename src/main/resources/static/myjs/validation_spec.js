$(document).ready(function() {
	$("#specCodeError").hide();
	$("#specNameError").hide();
	$("#specNoteError").hide();

	var specCodeError = false;
	var specNameError = false;
	var specNoteError = false;

	function validat_specCode() {
		var val = $("#specCode").val();
		var exp = /^[A-Z]{4,10}$/;
		if (val == "") {
			$("#specCodeError").show();
			$("#specCodeError").html("*<b>Code</b> can not be empty");
			$("#specCodeError").css("color", "red");
			return specCodeError = false;
		} else if (!exp.test(val)) {
			$("#specCodeError").show();
			$("#specCodeError").html(
				"*<b>Code</b> must be 4 to 10 character only"
			);
			$("#specCodeError").css("color", "red");
			return specCodeError = false;
		} else {
			var id = 0; //for register
			if ($("#id").val() != undefined) { //edit page
				specCodeError = true;
				id = $("#id").val();
			}
			$.ajax({
				url: 'checkCode',
				data: { "code": val },
				success: function(resTxt) {
					if (resTxt != '') {
						$("#specCodeError").show();
						$("#specCodeError").html(resTxt);
						$("#specCodeError").css('color', 'red');
						specCodeError = false;
					} else {
						$("#specCodeError").hide();
						specCodeError = true;
					}
				}
			});
		}
		return specCodeError;

	}

	function validat_specName() {
		var val = $("#specName").val();
		var exp = /^[A-Za-z\s]{4,60}$/;
		if (val == "") {
			$("#specNameError").show();
			$("#specNameError").html("*<b>Name</b> must be enter");
			$("#specNameError").css("color", "red");
			return specNameError = false;
		} else if (!exp.test(val)) {
			$("#specNameError").show();
			$("#specNameError").html("*<b>Name</b> must be 4 to 60 character");
			$("#specNameError").css("color", "red");
			return specNameError = false;
		} else {
			$("#specNameError").hide();
			specNameError = true;
		}
		/*else {
			$.ajax({
				url: 'checkName',
				data: { "name": val },
				success: function(resTxt) {
					if (resTxt != '') {
						$("#specNameError").show();
						$("#specNameError").html(resTxt);
						$("#specNameError").css('color', 'red');
						specNameError = false;
					} else {
						$("#specNameError").hide();
						specNameError = true;
					}
				}
			});
		}*/
		return specNameError;
	}
	function validat_specNote() {
		var val = $("#specNote").val();
		var exp = /^[A-Za-z\.\,\'\s\-]{4,250}$/;
		if (val == "") {
			$("#specNoteError").show();
			$("#specNoteError").html("*<b>Note</b> must be enter");
			$("#specNoteError").css("color", "red");
			return specNoteError = false;
		} else if (!exp.test(val)) {
			$("#specNoteError").show();
			$("#specNoteError").html("*<b>Note</b> can have 10 to 150 chars");
			$("#specNoteError").css("color", "red");
			return specNoteError = false;
		} else {
			$("#specNoteError").hide();
			return specNoteError = true;
		}
		return specNoteError;
	}

	$("#specCode").keyup(function() {
		$(this).val($(this).val().toUpperCase());
		validat_specCode();
	});

	$("#specName").keyup(function() {
		validat_specName();
	});

	$("#specNote").keyup(function() {
		validat_specNote();
	});

	$("#mySpecs").submit(function() {
		validat_specCode();
		validat_specName();
		validat_specNote();
		if (specCodeError && specNameError && specNoteError) return true;
		else return false;
	});
});