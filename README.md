# ShoppingWebAppSpring

#### Shopping web app is made using Java and SpringBoot Framework in which users can perform the following actions:
* Enter the quantity of products the user wants to order
* Check if the product is available in the desired quantity
* Check available coupons and apply one, if valid.
* Make payment for the order
* View the status of all orders

#### To run the application follow the below steps 
1. make a DB schema of name "shoppingdb" in MySql Workbench.
2. run the ShoppingApplication as java application on server(use eclipse IDE with embedded tomcat server).
3. go to postman and hit the urls to first add the product and coupons 
4. then hit the other urls provided 
   
#### Features and its url for postman (port = 8081, For fetch use GET , for add use POST)
1. To add product -> http://localhost:8081/product/add 
2. To fetch inventory ->http://localhost:8081/product/inventory
3. To add Coupons -> http://localhost:8081/coupons/add
4. To fetch Coupons -> http://localhost:8081/coupons/fetchCoupons
5. To add Order for userId=1, quantity=10 and couponcode = OFF5-> http://localhost:8081/Odd/1/orders?qty=10&couponcode=OFF5
6. To fetch all orders of userId=1 -> http://localhost:8081/Odd/1/allorders
7. To fetch order of userId=1 and OrderId=1 -> http://localhost:8081/Odd/1/1
8. To add payment of userID=1, orderId=1,amoount =950-> http://localhost:8081/Odd/1/0/pay?amount=950

Note : POST api's of adding product and coupon write the name & value of members of class in specific JSON fromat in body of Post request . 
       for example , add coupon JSON format will be :
       {
          "code":"OFF5",
          "discount":5
       }
         add product JSON format will be :
       { 
           "name":"bottle",
           "availableQuantity":100,
           "price":100.0
        }

Assumption : It is assumed that application has only one product with productId=1 an one user with userId=1  
