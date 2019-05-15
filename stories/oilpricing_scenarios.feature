Feature: Get list of oils
  Scenario: the client makes a call to GET /list/oil
	When the client calls /list/oil	
	Then client receives status code of 200