$(document).ready(
		function() {

			// GET REQUEST
			$("#getAllRobotPartsId").click(function(event) {
				event.preventDefault();
				ajaxGet();
			});

			// DO GET
			function ajaxGet() {
				$.ajax({
					type : "GET",
					url : window.location + "robot/part/listall",
					success : function(result) {
						if (result.status == "Done") {
							$('#getResultDiv ul').empty();
							var custList = "";
							$.each(result.data, function(i, robot) {
								var robot = "- Robot with serialNumber = " + i
										+ ", name = " + robot.name
										+ ", manufacturer = " + robot.manufacturer
										+ ", weight = " + robot.weight
										+ "<br>";
								$('#getResultDiv .list-group').append(robot)
							});
							console.log("Success: ", result);
						} else {
							$("#getResultDiv").html("<strong>Error</strong>");
							console.log("Fail: ", result);
						}
					},
					error : function(e) {
						$("#getResultDiv").html("<strong>Error</strong>");
						console.log("ERROR: ", e);
					}
				});
			}
		})