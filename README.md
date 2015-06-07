#A supermarket pricing example.

To run execute `gradle runScript` and access `http://localhost:4567`.

To test execute `gradle test`.

##Front end:

### Item form
*Name - mandatory
*Price - mandatory
*Weight - optional 

Using weight will indicate that the Price is for a particular weight.

### Deal form
*Items - mandatory
*Quantity needed - mandatory
*Deal type - mandatory

*Items free - if deal is Buy X Get Y free
*Discount price - if deal is Buy X For Y


##Backend
The class Service.java is the entry point to the system.

We have 2 main types of Items and Deals.

