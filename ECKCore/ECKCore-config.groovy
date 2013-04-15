
modules {
	Definition {
		baseURL = "http://localhost:28081/ECKDefinition"
	}
	Persistence {
		baseURL = "http://localhost:28080/KIPersistence"
		parameters {
			eckId = ""
			cmsId = ""
			persistentId = ""
			id = ""
			idType = ""
			deleted = ""
		}
		fileParameters {
			recordContents = ""
		}
	}
	Validation {
		baseURL = "http://localhost:28081/ECKDefinition"
	}
}
