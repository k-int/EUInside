class UrlMappings {

	static mappings = {
		"/Set/$provider/$setname/$action/$recordId?" {
			controller = "set"
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
