$(function() {
	$(".logoutButton").click(function() {
		var url = modelPath + "/doLogout";
		$.ajax({
			url : url,
			success : function(d) {
				window.location.reload();
			}
		});
	});
	
	function init(){
		$(".menusClass").find("li a").live("click",function(){
			var url = $(this).attr("url");
			url = modelPath + "/" + url;
			$(".contentDiv").load(url);
		});
	}
	
	init();
});