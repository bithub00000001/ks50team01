document.getElementById('checkAll').addEventListener('change', function() {
						        var checkboxes = document.querySelectorAll('[id^=memberCheck]');
						        checkboxes.forEach(function(checkbox) {
						            checkbox.checked = document.getElementById('checkAll').checked;
						        });
						    });
						    
						 

	$("#test").click(function(){
    const chk = $('input[type="checkbox"]:checked').closest('tr').find('td:eq(1)').text();
    console.log(chk);
	}); 