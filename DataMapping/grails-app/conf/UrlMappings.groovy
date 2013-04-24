class UrlMappings {

	static mappings = {

        //TODO change mappings to conform with /DataMapping/<provider>/<batch>/$action?

		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
