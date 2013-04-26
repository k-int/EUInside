
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
	Preview {
		baseURL = "http://test.asp.hunteka.hu:11080"
		basePath = "/eck-preview-module"
		fileParameters {
			record = ""
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
	Validate {
		baseURL = "http://test.asp.hunteka.hu:11080"
		basePath = "/eck-validation-module"
		fileParameters {
			record = ""
		}
	}
}
