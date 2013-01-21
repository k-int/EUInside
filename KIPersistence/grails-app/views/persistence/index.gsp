<!doctype html>
<html>
  <head>
    <meta name="layout" content="bootstrap"/>
    <title>KI Temp persistence module - persistence index</title>
  </head>

  <body>
    <div class="row-fluid">

      <section id="main">

        <div class="hero-unit row">
          <div class="page-header span12">
            <h1>Temp persistence module - Persistence index</h1>
          </div>
          
        </div>
        
        <div class="row">
          <div class="span12">

            <h4>Introduction</h4>
            <p>This page is simply a placeholder for the persistence index method. No persistence operation is called by accessing this URL.</p>
            
            <h4>Function calls</h4>
            
            <h5>Available functions</h5>
            <p>
                In order to make use of a function provided by this module access the URL specified by the 
                <g:link controller="identify" action="index">identification</g:link> response with the appropriate parameters.
            </p>
            
            <h5>Response formats</h5>
            <p>
                The default response format for these calls is HTML. However, setting the content type of the request to "application/json" will result in JSON
                being returned from any call.
            </p>
          </div>

        </div>
          
      </section>

    </div>
  </body>
</html>
