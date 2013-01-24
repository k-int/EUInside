import grails.converters.JSON

import com.k_int.euinside.shared.model.functions.ArgumentDefinition;
import com.k_int.euinside.shared.model.functions.MethodDefinition;
import com.k_int.euinside.shared.model.functions.ModuleDefinition;

class BootStrap {

    def init = { servletContext ->
		
		
		// Register JSON marshaller for the ModuleDefinition
		JSON.registerObjectMarshaller(ModuleDefinition) {
			def returnArray = [:]
			returnArray['name'] = it.name
			returnArray['moduleType'] = it.moduleType

			// Construct the list of methods
			def methodArray = [];
			it.methodDefinitions.each { aMethod ->
				def methodData = [:];
				
				methodData.methodName = aMethod.methodName;
				methodData.returnType = aMethod.returnType;
				methodData.description = aMethod.description;
				
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
		
		// Register the JSON marshaller for a record to ensure that the byte[] record contents
		// are converted back into a string
//		JSON.registerObjectMarshaller(Record) {
//			def returnArray = [:]
//			
//			returnArray['cmsId'] = it.cmsId;
//			returnArray['persistentId'] = it.persistentId;
//			returnArray['id'] = it.id;
//			returnArray['deleted'] = it.deleted;
//			
//			String tempString = new String(it.recordContents);
//			returnArray['recordContents'] = tempString;
//			
//			return returnArray;
//		}
		
    }
    def destroy = {
    }
	
	

}
