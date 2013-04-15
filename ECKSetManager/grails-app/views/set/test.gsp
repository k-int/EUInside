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
			                   		<th>CMS ID:</th>
			                   		<td><g:field type="text" name="cmsId"/></td>
			               		</tr>
			               		<tr>
			                   		<th>LIDO file:</th>
			                   		<td><g:field type="file" name="record"/></td>
			               		</tr>
			               		<tr>
			                   		<th>LIDO Zip file:</th>
			                   		<td><g:field type="file" name="records"/></td>
			               		</tr>
			               		<tr>
			               			<th>Delete All in set</th>
			               			<td><g:checkBox name="deleteAll" value="yes"/></td>
			               		</tr>
			               		<tr>
			               			<th>Records to Delete (comma separated)</th>
			               			<td><g:textField name="delete"/></td>
			               		</tr>
			               		<tr>
			                   		<td colspan="2">
			                   			<div class="btn btn-primary">
			                       			<g:field type="button"  name="testSubmit", value="Update"/>
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

        	$("#testSubmit").click(function() {

            	$("#testForm").attr("action", "update");
            	$("#testForm").submit();

	            return false;
        	});
    
    	</script>
    
  	</body>
</html>
