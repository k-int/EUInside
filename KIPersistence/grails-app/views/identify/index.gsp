<!doctype html>
<html>
  <head>
    <meta name="layout" content="bootstrap"/>
    <title>KI Temp persistence identify response</title>
  </head>

  <body>
    <div class="row-fluid">

      <section id="main">

        <div class="hero-unit row">
          <div class="page-header span12">
            <h1>Temp persistence module - Identify response</h1>
          </div>
          
        </div>
        
        <div class="row">
          <div class="span12">

                <h4>Module: ${availableCalls.name}</h4>
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
                    <g:each in="${availableCalls.methods}" var="aMethod">
                        <tr>
                            <td colspan="4">
                                <h5><a href="${availableCalls.name}/${aMethod.methodName}.json?">${aMethod.methodName}</a></h5>
                                <ul>
                                    <li><b>Return type: </b>${aMethod.returnType}</li>
                                    <li><b>Description: </b>${aMethod.description}</li>
                                </ul>
                            </td>
                        </tr>
                        <tr>
                            <td>
                            </td>
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
            
          </div>

        </div>
          
      </section>

    </div>
  </body>
</html>
