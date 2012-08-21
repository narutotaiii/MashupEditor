<%@page info="Release Date Table"
		contentType="text/html;charset=utf8" 
		errorPage="errorPage.jsp"
		
%>
<%!
	
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-TW">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<title>影JOY - Enjoy Movies</title>
	<!-- javascript -->
	<script src="js/jquery-1.4.2.min.js" type="text/javascript"></script>
	<script src="js/jquery-ui-1.8.4.custom.min.js" type="text/javascript" ></script>
	<script src="js/jquery.ui.draggable.js" type="text/javascript"></script>
	<script src="js/advacedSearchWindow.js" type="text/javascript"></script>
	<script src="js/ui.tabs.paging.js" type="text/javascript"></script>
	<script src="js/jquery.svTruncate.js" type="text/javascript"></script>
	<script src="js/jquery.ui.position.js" type="text/javascript"></script>
	<script src="js/jquery.ui.widget.js" type="text/javascript"></script>
	<script src="js/jquery.ui.core.js" type="text/javascript"></script>
	<script src="js/jquery.ui.autocomplete.js" type="text/javascript"></script>
	<script type="text/javascript">
	
	</script>
	<!-- CSS -->
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Molengo" type="text/css"/>
	<link rel="stylesheet" href="css/ui-lightness/jquery-ui-1.8.4.custom.css" type="text/css" />
	<link rel="stylesheet" href="css/styles.css" type="text/css" />
	<link rel="stylesheet" href="css/ui-lightness/jquery.ui.all.css" type="text/css" />
	<style type="text/css">
		.dvd {
			cursor: pointer;
		}
		.rankPoster ul{
			list-style: none;
			margin: 0;
			padding: 0;
		}
		
		.rankPoster ul li{
			margin: 0 auto;
			padding: 0;
			display: none;
			text-align: center;
		}
		
		.rankTable {
			border-width: 0px;
		}
		.tableTitle {
			font-size: 16px;
		}
		
		#dateInterval {
			float: center;
			color: #fff;
			font-size: 10px;
			font-style: italic;
		}
		
		#nextWeek, #thisWeek, #prevWeek {
			float: right;
			cursor: pointer;
		}
	
		
		//paging tab
		
		.ui-tabs-paging-next { 
			float: right !important;
		}
		.ui-tabs-paging-prev,
		.ui-tabs-paging-next {
			background: transparent !important;
			border: 0 !important;
			margin-bottom: 1px !important;
		}

		.ui-tabs-paging-prev a,
		.ui-tabs-paging-next a {
			display: block; 
			position: right; 
			top: 1px; 
			border: 0;
			z-index: 2; 
			padding: 0;
			/* color: #444; */ 
			text-decoration: none;
			background: transparent !important; 
			cursor: pointer;
		}
		.ui-tabs-paging-next a:hover,
		.ui-tabs-paging-next a:focus,
		.ui-tabs-paging-next a:active,
		.ui-tabs-paging-prev a:hover,
		.ui-tabs-paging-prev a:focus,
		.ui-tabs-paging-prev a:active { 
			background: transparent; 
		}
		.ui-tabs-paging-disabled {
			visibility: hidden;
		}
	</style>

</head>
<body>
	<h1>Restful Service Mashup</h1>
	
</body>	
</html>