import grails.converters.JSON

import com.k_int.euinside.core.functions.ModuleDefinition;
import com.k_int.euinside.core.functions.MethodDefinition;
import com.k_int.euinside.core.functions.ArgumentDefinition;

class BootStrap {

    def init = { servletContext ->
		
		
		// Register JSON marshaller for the RepositoryConnector
		JSON.registerObjectMarshaller(ModuleDefinition) {
			def returnArray = [:]
			returnArray['name'] = it.name

			// Construct the list of methods
			def methodArray = [];
			it.methodDefinitions.each { aMethod ->
				def methodData = [:];
				
				methodData.methodName = aMethod.methodName;
				// Construct the list of arguments
				def argArray = [];
				aMethod.arguments.each { anArg ->
					def argData = [:];
					argData.argumentName = anArg.argName;
					argData.argumentType = anArg.argType;
					argData.required = anArg.required;
					
					// Add this argument to the array
					argArray.add(argData);
				}
				// Add the array of arguments to the method
				methodData.arguments = argArray;
				
				// Add this method to the containing array
				methodArray.add(methodData);
			}
			
			returnArray.methods = methodArray;
			
			return returnArray
		}
		
    }
    def destroy = {
    }
	
	

}
