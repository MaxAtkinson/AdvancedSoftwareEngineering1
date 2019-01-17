# AdvancedSoftwareEngineering1
Repository for Stage 1 of the Advanced Software Engineering group coursework.

Group Members:
- Max Atkinson
- Gordon Rennie
- Andy Nowlan
- Connor McGuile

Meeting minutes 14/01/19
Agreed to meet once weekly at least. Not including the meeting in the lab sessions. Soft day on Thursday afternoons 2:30 onwards
Met and introduced ourselves and outlined our knowledge of programming.
Ran through the basics of git hub with Max. Each member altered the 'readme' and added their name.
Gordon was assigned to make CSV files for orders and products.
Andy and Connor were to begin work on the class diagrams from the group plan. 
Max was to start working on the idea behind how the gui would look.

Meeting minutes 17/01/19
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

Group report will contain:
Class Diagram
Use case
Graphical example of the GUI
Data structure reasoning
Version Control 
Time Management Plan
CRC cards (added after lecture on UML)
State machine
gantt chart

