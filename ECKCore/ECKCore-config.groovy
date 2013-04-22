
modules {
	Definition {
		baseURL = "http://localhost:28081"
		basePath = "/ECKDefinition"
	}
	Persistence {
		baseURL = "http://localhost:28080"
		basePath = "/KIPersistence"
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
	SetManager {
		baseURL = "http://localhost:28082"
		basePath = "/ECKSetManager"
		parameters {
			delete = ""
			deleteAll = ""
			historyItems = ""
			recordId = ""
			setDescription = ""
			statisticsDetails = ""
		}
		fileParameters {
			record = ""
			records = ""
		}

	}
	Validation {
		baseURL = "http://localhost:28081"
		basePath = "/ECKValidation"
	}
}
