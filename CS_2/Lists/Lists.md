This assignment is meant to give you practice implementing some standard data structures and their associated algorithms.

You will define two classes, ArrayBasedList and LinkedList. Each of these must implement the provided List  downloadinterface. Your classes must pass the tests in the corresponding test classes ArrayBasedListTest.java  downloadand LinkedListTest.java  download.

The orders of the amortized running times of your methods must be as follows:

Method	ArrayBasedList	LinkedList
add	Θ(1)	Θ(n)
clear	Θ(1)	Θ(1)
contains	Θ(n)	Θ(n)
equals	Θ(n)	Θ(n)
get	Θ(1)	Θ(n)
indexOf	Θ(n)	Θ(n)
iterator	Θ(1)	Θ(1)
removeAt	Θ(n)	Θ(n)
set	Θ(1)	Θ(n)
size	Θ(1)	Θ(n)
toString	Θ(n)	Θ(n)
There is no main program to run here. You are simply implementing data structures that could be used in other programs.

Hints
You are meant to write these classes yourself, not copy them from the internet or delegate the work to an existing class.

You don't have to write Javadoc comments; your methods inherit comments from the List interface.

Your classes must be generic and implement the List interface. The first line of ArrayBasedList (after any iterators) is therefore:

public class ArrayBasedList implements List
Each class will need an inner class for its Iterator. LinkedList will also need an inner class for a Node.

The add method adds an item to the end of a list. It would be possible to get the LinkedList version down to constant time by maintaining a reference to the last node, but this would make the other methods more complicated, so we'll skip that.

The automatically-generated equals methods don't work for these classes! (Those just compare the instance variables, but for these classes we must go inside the array or down the chain of nodes.) Correct solutions are tricky, so I'm providing them. Here's the one for ArrayBasedList:

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayBasedList<?> that = (ArrayBasedList<?>) o;
        if (size != that.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!Objects.equals(data[i], that.data[i])) {
                return false;
            }
        }
        return true;
    }
Here's the one for LinkedList:

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LinkedList that = (LinkedList) o;
        Node thatN = that.front;
        for (Node n = front; n != null || thatN != null; n = n.next) {
            if (((n == null) != (thatN == null))
                    || (!Objects.equals(n.item, thatN.item))) {
                return false;
            }
            thatN = thatN.next;
        }
        return true;
    }
What To Hand In
Hand in your two classes ArrayBasedList.java and LinkedList.java.
