class UrlMappings {

	static mappings = {
		"/Definition/$path**" {
			controller = "gateway"
			action = [GET : "definitionGetRelay", POST : "definitionPostRelay"]
		}
		"/Persistence/$path**" {
			controller = "gateway"
			action = [GET : "persistenceGetRelay", POST : "persistencePostRelay"]
		}
		"/SetManager/$path**" {
			controller = "gateway"
			action = [GET : "setManagerGetRelay", POST : "setManagerPostRelay"]
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
