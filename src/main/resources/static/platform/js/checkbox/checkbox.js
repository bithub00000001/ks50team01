document.getElementById('checkAll').addEventListener('change', function() {
						        var checkboxes = document.querySelectorAll('[id^=memberCheck]');
						        checkboxes.forEach(function(checkbox) {
						            checkbox.checked = document.getElementById('checkAll').checked;
						        });
						    });
						    
						 

$(document).ready(function() {
    $("#changeGrade, #withdrawal").click(function() {
        const action = $(this).data("action");
        $("#modifyForm").append('<input type="hidden" name="action" value="' + action + '">');
        $("#modifyForm").submit();
    });
    
});



			    $("#searchButton").click(function() {
			        const aa = $("#searchId").val();
			       console.log(aa)
			    });

