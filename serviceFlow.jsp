<%@page info="Release Date Table"
		contentType="text/html;charset=utf8" 
		errorPage="errorPage.jsp"
		
%>
<%!
	String resourceName[] = {"飛機航班","飯店資訊","天氣狀況"};
	String fieldsName[] = {"航空公司","日期","出發地","目的地"};
	String fieldsName2[] = {"飯店名稱","日期","地址","電話"};
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
		$(function() {
			$(".content_top input[type=button]").click(function() {
				$(".content_top input[type=button]").removeClass("resourceButton_click").addClass("resourceButton");
				$(this).removeClass("resourceButton").addClass("resourceButton_click");
				$(".content_bottom table").replaceWith(function(){
					var fieldsName2;					
					switch($(".resourceButton_click").attr("value"))
					{
						case "飛機航班" : fieldsName2 = new Array("航空公司","日期","出發地","目的地");break;
						case "飯店資訊" : fieldsName2 = new Array("飯店名稱","日期","地址","電話");break;
						case "天氣狀況" : fieldsName2 = new Array("地點","天氣狀態","高溫","低溫");break;
					}
					var content="";
					content+="<table class=\"tableWidth\">";
					content+="<tr>";
					content+="<th>欄位名稱</th>";
					content+="<th>設定新名稱</th>";
					content+="</tr>";					
					for(var j = 0 ; j < 4 ; j++)
						{	
							content+="<tr>";
							content+="<th>"+fieldsName2[j]+"</th>";
							content+="<th><input type=\"text\" size=20 name=\"field"+j+"\"></th>";
							content+="</tr>";
						}
					content+="</table>";	
					return content;	
				});
				
				
			});
		});
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
				<img src="./images/fieldsSetup_1.png" class="tagType">
				<img src="./images/flowSetup_0.png">
			</div>
			<div class="content">
				<div class="content_top">
				<%
					
					for(int i = 0 ; i < 3 ; i++)
					{
						out.println("<input type=\"button\" name=\"resource\" value=\""+resourceName[i]+
						"\" class=\"resourceButton\">");
					}
				%>
				</div>				
				<div class="content_bottom">
					<%
					
					
					for(int i = 0 ; i < 1 ; i++)
					{
						out.println("<table class=\"tableWidth\">");
						out.println("<tr>");
						out.println("<th>欄位名稱</th>");
						out.println("<th>設定新名稱</th>");
						out.println("</tr>");
						for(int j = 0 ; j < 4 ; j++)
						{	
							out.println("<tr>");
							out.println("<th>"+fieldsName[j]+"</th>");
							out.println("<th><input type=\"text\" size=20 name=\"field"+j+"\"></th>");
							out.println("</tr>");
						}
						out.println("</table>");
					}
				%>
				<br><br><br>
				<a href="flowSetup.jsp"><img src="./images/nextStep.png" ><a>
				</div>
			</div>
		</div>
	
	
</body>	
</html>