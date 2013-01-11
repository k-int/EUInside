<!doctype html>
<html>
  <head>
    <meta name="layout" content="bootstrap"/>
    <title>ECKCore - Available function list</title>
  </head>

  <body>
    <div class="row-fluid">

      <section id="main">

        <div class="hero-unit row">
          <div class="page-header span12">
            <h1>Available functions</h1>
          </div>
        </div>
        
        <div class="row">
          <div class="span12">

            <h3>Information</h3>
            <p>The following information is also available as a json response by sending a GET request to <g:link action="list.json">here</g:link> or by requesting this URL with a content type of "application/json" specified.</p>
          
            <h3>Calling available functions</h3>
            <p>TODO</p>
            
            <h3>Available functions</h3>
            <g:each in="${availableCalls}">

                <h4>Module: ${it.name}</h4>
                <table class="table table-striped table-condensed">
                    <thead>
	                <tr>
	                    <th>Method name</th>
	                    <th colspan="3">Arguments</th>
	                </tr>
	                <tr>
	                    <td class="emptyCell"/>
	                    <td colspan="3">
	                       <table class="table table-condensed">
	                           <tr>
			                        <th width="33%">Name</th>
			                        <th width="33%">Type</th>
			                        <th width="33%">Required?</th>
	                           </tr>
	                       </table>
	                    </td>
	                </tr>
                </thead>
                <tbody>
                    <g:each in="${it.methods}" var="aMethod">
                        <tr>
                            <td>${aMethod.methodName}</td>
                            <td colspan="3"/>
                        </tr>
                        <tr>
                            <td/>
                            <td colspan="3">
                                <table class="table table-condensed"> 
                       
                        <g:each in="${aMethod.arguments}" var="anArg">
	                        <tr>
	                            <td width="33%">${anArg.argumentName}</td>
	                            <td width="33%">${anArg.argumentType}</td>
	                            <td width="33%">${anArg.required}</td>
	                        </tr>
                        </g:each>
                                </table>
                                </td>
                                </tr>
                    </g:each>
                </tbody>
                </table>
             </g:each>
            
          </div>
        </div>
          
      </section>

    </div>
  </body>
</html>
