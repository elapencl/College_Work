This is a team project. Only one member of your team has to turn it in.

Assignment
A web browser maintains a list of known malicious domains so that it can warn users before they visit a site that might infect their computer with malware. New sites are being added to the list on a daily basis.

One simple approach would be to store all of the malicious domains in a hash table. That would make it simple to check if a given domain is on the list. The problem is that the list is too large: it would take up space on users' computers and require constantly pushing huge updates.

Google Chrome resolves this problem using a data structure called a Bloom filter. This structure is similar to a hash table, but uses far less space. Two prices are paid for this advantage:

The Bloom filter does not support deletion. This is not a problem in this application.
The Bloom filter is not entirely accurate. When asked if some domain is on the malicious list, it can't say "yes" or "no", but rather "maybe" or "no". In the extremely common case where the answer is "no", there's no need to do anything else. If the answer is "maybe", the browser remotely checks the master database on Google's servers. The point of using the Bloom filter is to avoid this expensive check in most cases.
How does the Bloom filter work? Read about it on Wikipedia. (Don't worry about the Alternatives section.) Part of the point of this assignment is giving you practice reading about and implementing new data structures and algorithms.

Your task is to implement a Bloom filter as a class BloomFilter.java that passes the tests in BloomFilterTest.java  download. To run all of these, you will need two additional files, which should be in your resources folder:

bad.txt  download is a list of bad domains from https://www.malwaredomainlist.com/hostslist/hosts.txt (Links to an external site.). Whatever you do, don't go to any of these sites! Also, some of these site names may be offensive, but so is malware.
good.txt  download is a list of good domains derived as described here: http://www.commandlinefu.com/commands/view/6934/get-a-list-of-top-1000-sites-from-alexa.
Hints
You're probably going to spend a lot more time thinking about this one than coding it. You're only writing four methods and none of them are very long.
You will need a table of 65536 bits. Use an array of longs -- how many will you need?
You will use two hash functions. One uses the 16 low-order bits of the item's hash code, the other the 16 high-order bits. This means you'll need to do some bit-twiddling.
What to Hand In
Submit your file BloomFilter.java and, in a separate document, your answers to the following questions:

What difficulties did you encounter and how did you overcome them?
What is the order of the worst-case running time of each of the operations (add, mightContain, and trueBits)? Give your answers in terms of n (the number of items stored) and m (the number of bits in the table).
How did your team work together to solve this problem? How can you improve this process next time?
