<!doctype html>
<html>
	<head>
		<!--  This meta line should get ignored, ripped this out from core, which has a master page ... -->
	  	<meta name="layout" content="bootstrap"/>
	  	<title>ECK SetManager - Test Page</title>
	</head>

	<body>
    	<div class="row-fluid">
			<section id="main">
		
		  		<div class="hero-unit row">
		    		<div class="page-header span12">
		      			<h1>Test Page</h1>
		    		</div>
		  		</div>
		  
			  	<div class="row">
			     	<div class="span12">
			       		<form id="testForm" name="testForm" action="update" method="POST" enctype="multipart/form-data">
			           		<table>
			               		<tr>
			                   		<th align="right">CMS ID: </th>
			                   		<td><g:field type="text" name="recordId"/></td>
			               		</tr>
			               		<tr>
			                   		<th align="right">LIDO file: </th>
			                   		<td><g:field type="file" name="record"/></td>
			               		</tr>
			               		<tr>
			                   		<th align="right">LIDO Zip file: </th>
			                   		<td><g:field type="file" name="records"/></td>
			               		</tr>
			               		<tr>
			               			<th align="right">Delete All in set: </th>
			               			<td><g:checkBox name="deleteAll" value="yes"/></td>
			               		</tr>
			               		<tr>
			               			<th align="right">Records to Delete (comma separated): </th>
			               			<td><g:textField name="delete"/></td>
			               		</tr>
			               		<tr>
			               			<th align="right">Number of history items (status only): </th>
			               			<td><g:textField name="historyItems"/></td>
			               		</tr>
			               		<tr>
			                   		<td colspan="2">
			                   			<div class="btn btn-primary">
			                       			<g:field type="button"  name="testCommit"     value="Commit"/>
			                       			<g:field type="button"  name="testList"       value="List Working Set"/>
			                       			<g:field type="button"  name="testRecord"     value="Record"/>
			                       			<g:field type="button"  name="testStatus"     value="Status"/>
				                   			<g:field type="button"  name="testUpdate"     value="Update"/>
			                       			<g:field type="button"  name="testValidation" value="Validation Errors"/>
			                       		</div>
			                   		</td>
			               		</tr>
			           		</table>
			       		</form>
			     	</div>
		  		</div>
      		</section>

    	</div>
    
    	<script type="text/javascript">

	    	$("#testCommit").click(function() {
	        	$("#testForm").attr("action", "commit");
	        	$("#testForm").submit();
	            return false;
	    	});
	
	    	$("#testList").click(function() {
	        	$("#testForm").attr("action", "list");
	        	$("#testForm").submit();
	            return false;
	    	});
	
	    	$("#testRecord").click(function() {
	        	$("#testForm").attr("action", "record");
	        	$("#testForm").submit();
	            return false;
	    	});
	
        	$("#testStatus").click(function() {
            	$("#testForm").attr("action", "status");
            	$("#testForm").submit();
	            return false;
        	});
    
        	$("#testUpdate").click(function() {
            	$("#testForm").attr("action", "update");
            	$("#testForm").submit();
	            return false;
        	});
    
        	$("#testValidation").click(function() {
            	$("#testForm").attr("action", "validate");
            	$("#testForm").submit();
	            return false;
        	});
    
    	</script>
    
  	</body>
</html>
