This assignment will give you a taste of a very simple form of machine learning. You will create a system that, trained on a body of text, produces similar but completely new text. A number controls the "intelligence" of your system.

The system can be trained on the text of the novel Moby Dick:

b = Babbler(1, 'moby.txt')
If you now feed it the beginning of the novel and tell it to continue,

print(b.babble('call me ishmael. some years ago', 200)).  # Produce 200 characters
it produces gibberish:

crer. thar ope catwe co sur se hars; ont wacefre cthen—wanthan borif ser evexcre. s jupiplyestot on by flum thyene oweef ba d ps askit yothaplle s h h?’mid w, iunguped. folly uer m f s ays te oetom a
If you turn the "intelligence" from 1 up to 2, the gibberish becomes largely pronounceable:

carld the rairm of theselign ing ity taboats, and of routhermly ther, ruiparte im ound not nowake firy nio. be whoat’s ofor comen to to pood’wountab, bill flught froute as ing a norale!” noatmeary ir
At 3, it's definitely trying for English:

call my conted bruteners of he crase or whalessed him, but nost deliviate oth hard onlight sake thought he hailizations the of the have? if withdrawere and ship! teen and bration tap!” crew zeales, m
At 4, it's almost all real words:

callibly behind in sense has refers of the statistinctly, spout, cindeed, which herefore he break a peddlessly my can’t your down a few dark at those of that the porthy contrap.” “starticular away of
At 5, you start to get phrases:

call the sky free whales, and all his body investigating to the pacific lowering the mizen rising that are: among those no key too richer. starbuck for my oath which the bows; till between to solemn
Unfortunately, it doesn't get vastly more intelligent than this. If you turn the number way up to 25, it reliably reproduces the original text.

How It Works
The babbler reads through the original text and notes which character comes after each "n-gram", that is, sequence of n consecutive characters. (Other systems look at words instead of letters.) These are stored in a dictionary so that the babbler knows, for example, how likely each different letter is to appear after the 4-gram 'whal'; probably 'e' is most likely in this case, but 'i' (as in 'whaling') is also a possibility.

To generate text, the system repeatedly uses this distribution to choose the next character. If we start it with 'whal' above and it chooses 'e', it will then look at the 4-gram 'hale' to choose the next character, and so on.

The "intelligence" rating above is simply the value of n: how much history the system looks at when choosing the next character.

Your Job
Define the class Babbler that has:

an initializer which takes n and the name of the training text file, and
a method babble which takes the starting text and the total number of character to generate (including the first n characters from the starting text); this method returns the generated string
If the starting text is more than n characters long, babble throws away all but the first n characters before generating additional ones.

Files
Put these files in the same directory as your program.

short.txt  download, a very small piece of text for testing
moby.txt  download, the entire text of Moby Dick (via Project Gutenberg (Links to an external site.) https://www.gutenberg.org/) You will have to download the book and put it in a .txt file!
After your program is working, try it on your own texts. If you're sleep-deprived enough and read the output aloud, this will be hilarious.

Hints
This is a relatively short program (my class is about 20 lines; yours will probably be longer) that does something neat. You are going to spend a long time getting it to work.

Don't try to write the entire program at once -- I didn't! Develop and test each piece before moving on to something more complicated.

You will probably want these imports:

from collections import defaultdict
from random import choices
You'll need to do some searching to find out how these things work.

Your primary data structure will be a dictionary (specifically a defaultdict) that maps n-grams (strings) to distributions (defaultdicts). For example, if n = 2, the string 'wh' will be mapped to a dictionary that maps each character that might come after 'wh' to a count of the number of times this occurred. Now, assuming you stored your data structure in self.counts, self.counts['wh']['e'] is the number of times 'wh' was followed by 'e'.

Define this function before defining your class:

def distribution():
    result = defaultdict(int)
    for i in range(26):
        result[chr(i + ord('a'))] = 0.0001
    return result
This creates a distribution that has a (tiny) chance of selecting each letter. As a result, even if you try to generate a successor to an n-gram that was never seen in the training text, it will be able to come up with something.

You can then create the main dictionary (inside your initializer) as:

self.counts = defaultdict(distribution)
To read the text:

with open(filename, encoding='utf-8') as file:
    text = file.read().lower().replace('\n', ' ')
To see if your data structure is set up properly, try creating

b = Babbler(1, 'short.txt')
This trains the system on the very short file short.txt. Now,

b.counts['e']
should be:

defaultdict(<class 'int'>, {'a': 0.0001, 'b': 0.0001, 'c': 0.0001, 'd': 0.0001, 'e': 0.0001, 'f': 0.0001, 'g': 0.0001, 'h': 0.0001, 'i': 0.0001, 'j': 0.0001, 'k': 0.0001, 'l': 0.0001, 'm': 0.0001, 'n': 2.0000999999999998, 'o': 0.0001, 'p': 0.0001, 'q': 0.0001, 'r': 0.0001, 's': 0.0001, 't': 0.0001, 'u': 0.0001, 'v': 0.0001, 'w': 0.0001, 'x': 0.0001, 'y': 0.0001, 'z': 0.0001, ' ': 1})
Also,

len(b.counts)
should be 11.

If you now

print(b.babble('s', 200))
you should get something like this:

s s se se ise shis s a s se isencence is s s short ishis a s ise a s s shishortencententhis sencenthorte is a s a s a is a sencentencenthorthortence s a sent is senthis ishishishort s s is shishortent
Since there is randomness, you won't get exactly the same output, here or in the examples at the beginning of this page.

What to Hand in
Hand in a single file babbler.py containing a definition of your class.
