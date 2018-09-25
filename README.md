***README modified after deployment.***


Author : Ki Min Lee 

PEDAL WORLD: first element in the menu bar
	- This is also the main welcome page for the website. It does not have anything else other than menu bar and brand logo. 
	- Users are directed to this page when they enter the URL

Product: second element in the menu bar
	- This page contains the list of products. If you move your cursor on to the image, it will zoom in a little, allowing you to see it better. 
	- The list has three columns. One for the image, one for name and product type, and one for product price.
	- If the user wishes to purchase or have better look at the item, clicking on the image will direct you to corresponding product page
	- No zoom in feature for image in the product page. Already displayed enlarged. Users can select different images provided to see the product in different view.
	- If the user wishes to purchase the item, click on the "Add to Cart" button located below the description.
	- Upon clicking on the button, user will be redirected to Check Out page. If the user wishes to purchase a number of same product, simply go back to product detail page and click add to cart again.
	- Entries involving only numbers (e.g phone number, card number), only requires number.
	- Recently viewed items are available at the bottom of the page. Most recent product is the rightmost product. Clicking on the image will redirect the user to corresponding product.
	- Recently viewed products are not unique, so it will have duplicates in user visited same product detail page multiple times.
	- Recently viewed products will only display five most recent products.

About Us: third element in the menu bar
	- This page contains brief information and overview about the company. 
	- It explains the type of product we sell and what we offer offline
	- Contact Us is included in the bottom.

Check Out: fourth element in the menu bar.
	- This hyperlink/button is located in the right side of the menu bar.
	- When user clicks on this link, user will be redirected to a check out page.
	- If user had not added a product yet, it will display as empty.
	- If user had added a product, it will show the list of added product along with quantity, price, and option to remove button.
	- Remove button will only remove selected product, regardless of added quantity.
	- Clear button provided below product list will remove all the products from cart.
	- If user wishes to process with the order, fill out the check out form and click on Order.
	- If successful, user will be redirected to Order Summary page.


***Requirements*** 
	1. Database
		- Product list in "Product" is generated dynamically using the already initialized database, using RESTful API which connects to JDBC. 
		- For product detail page, thumbnail images(path, not the actual file) are accessed from the database and rendered dynamically.
	2. Product Page
		- Products page is reimplemented into .jsp instead of servlet. The page also invokes REST API to acquire necessary information from database.
	3. REST Service
		- the "rest" pacakge in the src folder contains the REST service implementation. It has four verbs - GET, POST, PUT, DELETE
		- Although all four verbs have been implemented and tested, only GET and POST was actually used in the client program. (PUT and DELETE's functionality can be tested from Postman)
		- REST Service was derived from the Jersey framework.
		- Attempting to access invalid files with return 404 Error.
	4. REST Service Documentation
		
		baseURL = http://centaurus-3.ics.uci.edu:2069/PA4/products

		@GET
			/ : returns all the products in the database.
				(Postman) GET http://centaurus-3.ics.uci.edu:2069/PA4/products
				Result (in JSON) :

				[{"id":1,"name":"MXR Dyna Comp Mini","type":"Compressor","price":"99.00","description":null,"imageCount":0,"imagePath":"static/asset/item1/Dyna_Comp_"},{"id":2,"name":"Digitech DOD 280 Compressor","type":"Compressor","price":"79.00","description":null,"imageCount":0,"imagePath":"static/asset/item2/DOD_Comp_"},{"id":3,"name":"Fulltone Fulldrive 3","type":"Dual Overdrive/Boost","price":"139.00","description":null,"imageCount":0,"imagePath":"static/asset/item3/Fulldrive3_"},{"id":4,"name":"Fulltone Fulldrive 2","type":"Dual Stage Overdrive","price":"129.00","description":null,"imageCount":0,"imagePath":"static/asset/item4/Fulldrive2_"},{"id":5,"name":"Ibanez Tube Screamer 9","type":"Overdrive","price":"99.00","description":null,"imageCount":0,"imagePath":"static/asset/item5/TS9_"},{"id":6,"name":"Ernie Ball Volume Pedal","type":"Volume Pedal","price":"99.00","description":null,"imageCount":0,"imagePath":"static/asset/item6/EB_Volume_"},{"id":7,"name":"ZOOM MS50G Multistomp","type":"Multistomp Pedal/Amp Modeler","price":"99.00","description":null,"imageCount":0,"imagePath":"static/asset/item7/ZOOM_MS_"},{"id":8,"name":"Boss DD-500 Delay","type":"Multi-Digital Delay","price":"349.00","description":null,"imageCount":0,"imagePath":"static/asset/item8/DD500_"},{"id":9,"name":"Boss RV-6 Reverb","type":"Multi-reverb","price":"129.00","description":null,"imageCount":0,"imagePath":"static/asset/item9/RV6_"},{"id":10,"name":"EHX Soul Food","type":"Overdrive/Fuzz","price":"99.00","description":null,"imageCount":0,"imagePath":"static/asset/item10/SF_"}]

			/{id} : returns a specific product with given id
				(Postman) GET http://centaurus-3.ics.uci.edu:2069/PA4/products/1
				Result (in JSON) :

				{"id":1,"name":"MXR Dyna Comp Mini","type":"Compressor","price":"99.00","description":"The Dyna Comp Compressor is one of the most popular compressors of all time.\r\n\t\t\t\t\t   Whether you want to tighten up your signal, add rich sustain, \r\n\t\t\t\t\t   or create the percussive and clicky sound heard on numerous hit records, \r\n\t\t\t\t\t   this pedal is straightforward and easy to use. \r\n\t\t\t\t\t   For these reasons, the Dyna Comp Compressor has been the secret weapon on countless pedalboards for years.","imageCount":3,"imagePath":"static/asset/item1/Dyna_Comp_"}

		@POST
			/ : used for submitting order information to the database.
				(Postman) POST http://centaurus-3.ics.uci.edu:2069/PA4/products
					consumes JSON (order information) in the format:

					{"firstName":"{name}","lastName":"{name}","product":"{product}","cardNum":"{card number}","quantity":"{quantity}","address":"{address}","shipping":"{shipping}","phoneNum":"{phone number}","email":"{email}"}

				Returns "Order Successfully Added"

		@PUT
			/update/order_id={id} : updates a specific order detail in the database
				(Postman) PUT http://centaurus-3.ics.uci.edu:2069/PA4/products/update/order_id=1
					consumes JSON (order information) in the format:

					{"firstName":"{name}","lastName":"{name}","product":"{product}","cardNum":"{card number}","quantity":"{quantity}","address":"{address}","shipping":"{shipping}","phoneNum":"{phone number}","email":"{email}"}

				Returns "Order successfully updated"

		@DELETE
			/del/order_id={id} : deletes a specific order detail from the database
				(Postman) DELETE 

				Returns "Order successfully deleted"

		@GET, @PUT, @DELETE returns HTTP STATUS 404 if data does not exist.
	

