<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <link rel="stylesheet" href="../css/bootstrap.min.css">   		
        <script src="../js/bootstrap.min.js"></script>     
    </head>
    <body>
        <div class="container">
            <form action="/testmate" method="post"  role="form" data-toggle="validator" >
                <c:if test ="${empty action}">                        	
                    <c:set var="action" value="add"/>
                </c:if>
                <input type="hidden" id="action" name="action" value="${action}">
                <input type="hidden" id="idtestModule" name="idtestModule" value="${testModule.name}">
                <h2>Creating a new Test Module</h2>
                <div class="form-group col-xs-4">
                    <label for="name" class="control-label col-xs-4">Name:</label>
                    <input type="text" name="name" id="name" class="form-control" value="${testModule.name}" required="true" placeholder="test module names are unique" />                                   

                    <label for="executableFile" class="control-label col-xs-4">
                    Executable file path</label>                   
                    <input type="text" name="executableFile" id="executableFile" class="form-control" value="${testModule.executableFile}" placeholder="/full/path/to/file" required="true"/> 

                    <label for="testFile" class="control-label col-xs-4">
                    Test file path</label>                   
                    <input type="text" name="testFile" id="testFile" class="form-control" value="${testModule.testFile}" placeholder="/full/path/to/file" required="true"/>

                    <label for="scriptFile" class="control-label col-xs-4">
                    Script file path</label>                   
                    <input type="text" name="scriptFile" id="scriptFile" class="form-control" value="${testModule.scriptFile}" placeholder="/full/path/to/file" required="true"/> 

                    <br></br>
                    <button type="submit" class="btn btn-primary  btn-md">Create</button> 
                </div>                                                      
            </form>
        </div>
    </body>
</html>