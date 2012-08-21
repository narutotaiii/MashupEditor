<%@page info="Release Date Table"
		contentType="text/html;charset=utf8" 
		errorPage="errorPage.jsp"
		
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-TW">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<title>Restful Service Mashup</title>
	<!-- javascript -->
	<script src="js/jquery-1.4.2.min.js" type="text/javascript"></script>
	<script src="js/jquery-ui-1.8.4.custom.min.js" type="text/javascript" ></script>
	<script type="text/javascript" >
		
	</script>
	<!-- CSS -->
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Molengo" type="text/css"/>
	<link rel="stylesheet" href="css/ui-lightness/jquery-ui-1.8.4.custom.css" type="text/css" />	
	<link rel="stylesheet" href="css/ui-lightness/jquery.ui.all.css" type="text/css" />	
	<link rel="stylesheet" href="css/styles.css" type="text/css" />
	
	<style type="text/css">
	.content{
				background-image:url('./images/mashup_face.png');
				width:998px;
				height:570px;
				margin:auto;
				margin-top:-1%;
			}
	.content_top{
					margin:auto;
					text-align:center;
					width:970px;
					height:50px;
					padding:50px;
				}
	.content_bottom{					
					margin:auto;
					text-align:center;
					width:900px;
					height:500px;
					padding:50px;
				}			
	.tagType{
				margin-left:50px;
				margin-top:-5%;
			}
	.tableWidth{
					margin:auto;
					text-align:center;
					width:80%;
				}
	.button{
				background-color:#AAF0CC;
				font-size:30px;
				font-weight:bold;
				bolder-style:groove;
				border-width:4px;
			}
	.flowFace{
				background-image:url('./images/flowFace.png');
				width:866px;
				height:393px;
				margin-top:-10%;
				margin-left:2%;
			}
	.outputPoint{
					margin-top:30%;
					
				}
	
	</style>

</head>
<body>
	<%@include file="inc/header.inc"%>
	
		<div class="editFace">
			<%@include file="inc/functionList_mashup.inc"%>
			<div class="tagList">
				<img src="./images/tag_serviceFlow_1.png">
				<img src="./images/tag_dataComposition_0.png">
				<img src="./images/tag_widgetDesign_0.png" >
			</div>
			<div>
				<img src="./images/fieldsSetup_0.png" class="tagType">
				<img src="./images/flowSetup_1.png" >
			</div>
			<div class="content">
				<div class="content_top">
					<input type="button" name="system" value="系統推薦" class="button">
					
					<input type="button" name="user" value="使用者自訂" class="button">
				</div>				
				<div class="content_bottom">
					<div class="flowFace">
						<div>
							<img src="./images/inputPoint.png" >
						</div>
						<div>
							<img src="./images/outputPoint.png" class="outputPoint">
						</div>
				
					</div>
					<a href="flowSetup.jsp"><img src="./images/nextStep.png" ><a>
				</div>
			</div>
		</div>
	
	
</body>	
</html>	