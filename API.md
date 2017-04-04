*API*

*COMMON INFO*

URL:    https://localhost:8443...

STATUS 
200 - OK
401 - UNAUTHORIZED - WRONG PASSWORD
404 - NOT FOUND


**LOGIN** 

...URL | METHOD | INPUT | OUTPUT 
--- | --- | --- | --- 
/api/logIn | ¿? | None | ResponseEntity<User> 
/api/logOut | ¿? | HttpSession | ResponseEntity<Boolean>

**PRODUCTS**

...URL | METHOD | INPUT | OUTPUT 
--- | --- | --- | --- 
/api/products/ | GET | None | List<Product>
/api/products/{id} | GET | Product.Id | ResponseEntity<Product>
/api/products/ | POST | Product | Product 
/api/products/{id} | PUT | Product.Id, Product | ResponseEntity<Product> 
/api/products/{id} | DELETE | Product.Id | ResponseEntity<Product> 


**USERS**

...URL | METHOD | INPUT | OUTPUT 
--- | --- | --- | --- 
/api/users/ | GET | None | List<User> 
/api/users/{id} | GET | User.Id | ResponseEntity<User>
/api/users/ | POST | User | User
/api/users/{id} | PUT | User.Id, Product | ResponseEntity<User> 
/api/users/{id} | DELETE | User.Id | ResponseEntity<User> 

**ORDERS**  [Order = OrderSummary]

...URL              | METHOD    | INPUT                   | OUTPUT 
---                 | ---       | ---                     | --- 
/api/orders/        | GET       | None                    | List<Order>               
/api/orders/{id}    | GET       | Order.Id                | ResponseEntity<Order>     
/api/orders/        | POST      | Order                   | Order                     
/api/orders/{id}    | PUT       | Order.Id, Order         | ResponseEntity<Order>    
/api/orders/{id}    | DELETE    | Order.Id                | ResponseEntity<Order>    
