<% def ModulesService = application.getAttribute("org.codehaus.groovy.grails.APPLICATION_CONTEXT").getBean("modulesService") %>
<!doctype html>
<html>
  	<head>
    	<meta name="layout" content="bootstrap"/>
    	<title>ECKCore home</title>
  	</head>

  	<body>
    	<div class="row-fluid">

	      	<section id="main">
	
	        	<div class="hero-unit row">
	          		<div class="page-header span12">
	            		<h1>Welcome to the ECK Core (iteration 2) prototype system</h1>
	        	  	</div>
	          		<p>The gateway for the modules used by ECK</p>
	        	</div>
	        
	        	<div class="row">
	          		<div class="span12">
	            		<h2>Available Modules</h2>
	            		<ul>
	                		<li><a href="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/${ModulesService.getDefinitionModuleCode()}/">Metadata Definition</a></li>
	                		<li><a href="http://euinside.semantika.si/pid/Generate">PID Generation</a></li>
	                		<li><a href="http://app.asp.hunteka.hu:5080/eck-preview-servlet/index.html">Preview</a></li>
	                		<li><a href="${ModulesService.getModuleExternalPath(ModulesService.getCoreModuleCode())}/${ModulesService.getSetManagerModuleCode()}/">Set Manager</a></li>
	                		<li><a href="http://app.asp.hunteka.hu:5080/eck-validation-servlet/index.html">Validation (monguz)</a></li>
	                		<li><a href="http://euinside.semantika.si/">Validation (semantika)</a></li>
	            		</ul>
	          		</div>
	
		        </div>
	    	</section>
    	</div>
  	</body>
</html>
