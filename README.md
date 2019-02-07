# AdvancedSoftwareEngineering1
Repository for Stage 1 of the Advanced Software Engineering group coursework.

Group Members:
- Max Atkinson
- Gordon Rennie
- Andy Nowlan
- Connor McGuile

## Meeting minutes 14/01/19

Agreed to meet once weekly at least. Not including the meeting in the lab sessions. Soft day on Thursday afternoons 2:30 onwards
Met and introduced ourselves and outlined our knowledge of programming.
Ran through the basics of git hub with Max. Each member altered the 'readme' and added their name.
Gordon was assigned to make CSV files for orders and products.
Andy and Connor were to begin work on the class diagrams from the group plan. 
Max was to start working on the idea behind how the gui would look.

## Meeting minutes 17/01/19

CSV files were created. Feedback was to change the Orders file so that rather than a 'name' column the 
unique ID would be used for whatever product was being bought.
Connor and Andy both started work on the class diagram and basic user experience for the making of a use case diagram.
Ambiguities from the spec were discussed and solutions found.

The idea of using a 'quantity' variable was discussed. This was in relation to using a 'set' data structure to hold
the orders however as the spec describes multiple lines for the same order an array list was decided which would allow 
duplicates. This was used in case of the same customer ordering 2x coffees in the same order. 
The discussion of where data would be stored and how it would be passed between classes was discussed to allow building
of the class diagram. For example, the discounts and running total of the 'placeNewOrderGUI' would be calculated within the 
gui class and the final total/discount would be passed back to the file I/O class at the end. 

It was also discussed the option of adding inheritance to the products. The idea of food being split into main, snack,
take-away ect. This would help with scalability as new products may have new variables. Difficulties and solutions were 
discussed.

### Development Plan will contain:
- Class Diagram
- Use case
- Graphical example of the GUI
- Data structure reasoning
- Gantt chart
- Sequence Diagram
- Activity Diagrams

### Work for next time
- Andy - Class Diagram, CRC cards (done)
- Connor - GUI example, State Machine (done)
- Gordon - Use Case Diagram (not complete)
- Max - Gantt Chart (done)

## Meeting Minutes 24/01/19
Final changes to the class diagram were agreed. This was settled with Connor's GUI example to decide final methods 
in the GUI class. Settled on using inheritance for products with two levels to ensure scalability. Product as top level and then food, beverage,
and memorabilia as secondary. Also decided to add a 'basket' class which represents the shopping trolley. This was done to 
seperate calculations out of the GUI class. 

Meeting for the following week was pulled forward considering the deadline on Thursday. The group agreed to meet on 
Tuesday during the usual lab time as well as the Thursday meeting. 

### Splitting code work:
- Connor: Product and inherited classes + Order class
- Gordon: FileManager Class + Exceptions 
- Max: Basket Class + discounts
- Andy: GUI Class

### Git workflow:
- We each make a branch and write our classes
- Someone else pulls it down and writes some unit tests around the functionality
- They then create the pull request
- Upon reviewal, the assigned reviewer merges the pull request into master
 
link to sequence diagram:
https://drive.google.com/file/d/1NemeT9SRTJzqbscYMZrF3kblUC0EFEI5/view?usp=sharing

## Meeting Minutes 31/01/19
Final meeting pulling everything together and sorting out the overleaf doc. Made some final changes to our diagrams as we thought more about the flow of the program. Report handed in at 16:50 roughly. Decided on Google Java Style Guide for our code style. Off to pub.

## Meeting Minutes 07/02/19
Reviewed and discussed comments and feedback relating our development plan submission.  Some changes to class diagram are outlined below:

	- addOrder(order:Order): void -  removed (this was included in error)
	- Will require getters for basket
	- Write report parameters removed  
	- Associative line from Order to Product to be included  

We further discussed our current progress and will continue to implement the first iteration as planned (minor alterations may be necessary). We plan to revisit the design upon completion of the first iteration. 