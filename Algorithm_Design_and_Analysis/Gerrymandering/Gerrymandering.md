This is a team project. Only one member of your team has to turn it in.

Assignment
Gerrymandering (Links to an external site.) is drawing political district boundaries to favor one party or group. It can result in a considerable difference between the amount of support a party has and the number of seats that party wins in the legislature -- even allowing the minority party to control the legislature. With the increasing availability of detailed data and geographic information systems, gerrymandering is becoming increasingly sophisticated.

This assignment asks you to devise an algorithm to gerrymander a two-party electorate to favor a particular party. For simplicity, the voters are placed in a square grid. There will be d contiguous districts, each containing d voters.

Ethical Note
Gerrymandering is antithetical to the central principle of electoral democracy: that every voter has equal power. This simulation is meant to explore the algorithmic issues related to gerrymandering and, tangentially, the surprisingly strong effect of gerrymandering. Computer scientists should not participate in actual efforts to corrupt the democratic process.

Files
You are provided with the following files:

Gerrymanderer.java  download, an interface that you should implement.
Striper.java  download, a simple implementation of the interface.
Electorate.java  download, a class representing the collection of voters to be gerrymandered.
ElectorateTest.java  download, a JUnit test class for Electorate.java.
ElectorateDrawer.java  download, a class for graphically drawing a gerrymandered Electorate.
GerrymandererMeasurer.java  download, a class that measures the effectiveness of your algorithm over many random Electorates.
Hints
Feel free to make use of the classes from the Sedgewick & Wayne Algorithms library (Links to an external site.).
You have considerable room for creativity and ingenuity on this assignment. I don't have a specific solution in mind. In fact, to avoid the temptation to steer everyone toward the same answer, I have deliberately avoided writing a solution. Show me what you can do!
That said, don't hesitate to ask if you need ways to do various things as parts of your algorithm. A TA or I may be able to steer you toward an algorithm or even a library method that could save you hours of work.
I'm not asking you to formally analyze any algorithms involved, but analysis may be helpful in selecting algorithms for various parts of your solution. Brute force is not going to work here.
What to Hand In
Submit the .java file for your class that implements the Gerrymanderer interface and, in a separate document, your answers to the following questions:

How does your algorithm work? Explain clearly, using diagrams if they help.
How effective is your algorithm on Electorates of various sizes (9, 19, 29)?
What difficulties did you encounter and how did you overcome them?
How did your team work together to solve this problem? How can you improve this process next time?
