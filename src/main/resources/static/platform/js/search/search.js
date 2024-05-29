$(document).ready(function(){
	$('#searchItem').on('keyup', function(){
		const searchValue = $(this).val().toLowerCase();
		$('#datatable-responsive tbody tr').filter(function(){
			$(this).toggle($(this).text().toLowerCase().indexOf(searchValue) > -1);
		});
	});
});