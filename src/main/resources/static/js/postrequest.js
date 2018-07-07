$(document)
		.ready(
				function() {

					// SUBMIT FORM
					$("#robotPartForm").submit(function(event) {
						// Prevent the form from submitting via the browser.
						event.preventDefault();
						ajaxPost();
					});

					function ajaxPost() {

						// PREPARE FORM DATA
						var formData = {
							name : $("#name").val(),
							manufacturer : $("#manufacturer").val(),
							weight : $("#weight").val(),
						}

						// DO POST
						$
								.ajax({
									type : "POST",
									contentType : "application/json",
									url : window.location + "robot/part/save",
									data : JSON.stringify(formData),
									dataType : 'json',
									success : function(result) {
										if (result.status == "Done") {
											$("#postResultDiv")
													.html(
															"<p style='background-color:#7FA7B0; color:white; padding:20px 20px 20px 20px'>"
																	+ "Post Successfully! <br>"
																	+ "---> Robot part's Info: Name = "
																	+ result.data.name
																	+ " ,Manufacturer = "
																	+ result.data.manufacturer
																	+ ", Weight = "
																	+ result.data.weight
																	+ "</p>");
										} else {
											$("#postResultDiv").html(
													"<strong>Error</strong>");
										}
										console.log(result);
									},
									error : function(e) {
										alert("Error!")
										console.log("ERROR: ", e);
									}
								});

						// Reset FormData after Posting
						resetData();

					}

					function resetData() {
						$("#serialNumber").val("");
						$("#name").val("");
						$("#manufacturer").val("");
						$("#weight").val("");
					}
				})