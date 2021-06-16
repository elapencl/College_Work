This is a pair programming project. Work with your assigned partner. Only one of you has to turn it in, but make sure you don't both think the other one was doing it.

Overview
Write a program to count the distribution of letters in a text file. The letter counts should be printed out and displayed as a bar chart. For example, when run on the first paragraph of Herman Melville's novel Moby Dick, it should print

a: 57
b: 9
c: 18
d: 21
e: 107
f: 22
g: 24
h: 51
i: 80
j: 0
k: 4
l: 45
m: 30
n: 62
o: 62
p: 25
q: 2
r: 56
s: 53
t: 76
u: 26
v: 13
w: 17
x: 0
y: 22
z: 2
and draw precisely this diagram:



The program should work on any plain text file, with changing the filename being the only modification needed to the code. You can test it on these two files:

para1.txt  download (the first paragraph of Moby Dick, which should produce exactly the output above)
2701-0.txt (Links to an external site.) (the entire text of the novel)
Hints
You can do arithmetic on chars. Specifically, if the ch is a char, then ch - 'a' is 0 if ch is 'a', 1 if ch is 'b', and so on.

You can save yourself a lot of math by adjusting the scale of StdDraw with these two lines:

StdDraw.setXscale(-5, 27);
StdDraw.setYscale(-0.1, 1.1);

This way, you can give 'a' an x coordinate of 0, 'b' an x coordinate of  1, and so on. You will still need to scale the counts into the 0.0 to 1.0 range by dividing by the maximum count.

To draw a red rectangle outlined in red, first draw a red filled rectangle and then draw a black non-filled rectangle.

What To Hand In
Hand in your one file LetterCounter.java.
