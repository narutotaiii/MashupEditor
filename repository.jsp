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
	
	<title>Restful Service Mashup</title>
	<!-- javascript -->
	<script>
		
	</script>
	<!-- CSS -->
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Molengo" type="text/css"/>
	<link rel="stylesheet" href="css/ui-lightness/jquery-ui-1.8.4.custom.css" type="text/css" />	
	<link rel="stylesheet" href="css/ui-lightness/jquery.ui.all.css" type="text/css" />	
	<link rel="stylesheet" href="css/styles.css" type="text/css" />
	
	<style type="text/css">
		
	.repository_face{
			background-image:url('./images/repository_face.png');
			background-repeat:no-repeat;
			width:1056px;
			height:608px;
			margin:auto;
			margin-top:-44%;			
		}
		
	.content{
				width:85%;
				font-size:25px;
				font-weight: bold;
				margin:auto;
				margin-top:50px;
			}
	.content2{
				width:90%;
				font-size:25px;
				font-weight: bold;
				margin:auto;
				margin-top:20px;
			}		
	.radioText{
				
				font-size: 25px;
				font-weight: bold;
				}
	.titleText{
				
				font-size: 25px;
				font-weight: bold;
				border-width: 0px;
				}
	.titleText th,.titleText tr {
		
		
	}
	.titleText  {
		border-spacing: 0px;
	}
	.repository_face select {
						  }		
	#querry{
			border-style:inset;
			border-width:5px;
			margin:auto;			
			font-size: 20px;
			font-weight: bold;
		}
	#repository_search{				
				border-style::groove;
				border-width:2px;				
				font-size: 25px;				
				font-weight: bold;
			}
	#repository_add{				
				border-style::groove;
				border-width:2px;			
				font-size: 25px;			
				font-weight: bold;
			}
	#repository_select{				
				width:940px;
				height:380px;
				border-style:outset;
				border-width:4px;				
				font-size: 20px;			
				font-weight: bold;
			}
	#repository_submit{					
				border-style:outset;
				border-width:2px;				
				font-size: 20px;			
				font-weight: bold;
				margin-left:48%;
				margin-top:1%;
			}		
	</style>

</head>
<body>
	<%@include file="inc/header.inc"%>	
		<div class="editFace">
		<%@include file="inc/functionList_repository.inc"%>
			<div class = "repository_face"><br />
			<form method="post" action="systemFlow.do">
				<div class = "content">
					<input type = "text" size ="30" name = "query" id = "querry"></input>
					<input type = "button"  name = "search" value = "搜尋" id = "repository_search"></input>					
					<input type = "radio"  name = "type" /> ALL
					<input type = "radio"  name = "type" /> RSS
					<input type = "radio"  name = "type" /> Restful
					<input type = "button"  name = "add" value = "手動新增" id = "repository_add"></input>
				</div>
				<div class = "content2">
				<table class = "titleText">
					<tr>
						<th>資源名稱</th>
						<th>類型</th>
						<th>欄位數</th>
						<th>描述</th>
					</tr>
				</table>
				<%
				
				%>
				
				<select size = "10" multiple id = "repository_select">
					<option>test1</option>
					<option>test2</option>
					<option>test3</option>
					<option>test4</option>
					<option>test5</option>
				</select>
				<input type = submit name = "submeit" value = "Go混搭" id = "repository_submit">
				</div>
			</form>	
			</div>
		</div>
	
	
</body>	
</html>