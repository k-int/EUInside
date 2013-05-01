class UrlMappings {

	static mappings = {
		name definition : "/Definition" {
			controller = "gateway"
			action = [GET : "definitionGetRelay", POST : "definitionPostRelay"]
		}
		"/Definition/$path**" {
			controller = "gateway"
			action = [GET : "definitionGetRelay", POST : "definitionPostRelay"]
		}
		"/Persistence/$path**" {
			controller = "gateway"
			action = [GET : "persistenceGetRelay", POST : "persistencePostRelay"]
		}
		"/Preview/$path**" {
			controller = "gateway"
			action = [GET : "previewGetRelay", POST : "previewPostRelay"]
		}
		name setManager : "/SetManager" {
			controller = "gateway"
			action = [GET : "setManagerGetRelay", POST : "setManagerPostRelay"]
		}
		"/SetManager/$path**" {
			controller = "gateway"
			action = [GET : "setManagerGetRelay", POST : "setManagerPostRelay"]
		}
		"/Validation/$path**" {
			controller = "gateway"
			action = [GET : "validateGetRelay", POST : "validatePostRelay"]
		}
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
