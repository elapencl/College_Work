from collections import defaultdict
from random import choices
# I've started late with doing this assignment so I haven't gotten the correct result (sorry!);
# When you try to run it multiple times and using different lengths, you'll notice uninterrupted/wrong sequences
# I'm pretty sure it's in the babble method
# Try running for length 25 and it gives a whole sequence not split into words..
# Even when you start with 1 there's a variety of numbers of starting letters it picks (if it makes sense), anyway this
# is visible after running the code

# use this for Window's users
with open("moby.txt", encoding='utf-8') as file:
    text = file.read().lower().replace('\n', '')

# a bit more of Peter's code


def distribution():
    result = defaultdict(int)
    for i in range(26):
        result[chr(i + ord('a'))] = 0.0001
    return result


class Babbler:

    def __init__(self, n, text):
        self.n = n
        self.text = text
        self.counts = defaultdict(distribution)
        # Let's create a dictionary that stores n-grams and letters that come after it
        # note: Need to add - self.n in the loop to not get out of range
        for i in range(len(self.text) - self.n):
            # start with your n_gram
            n_gram_number = self.text[i:i + self.n]
            # now look for the upcoming letter
            # I think I'm making a mistake here, because I think I didn't make a clear difference between n_gram_number
            # and upcoming_letter
            upcoming_letter = self.text[i + self.n]
            self.counts[n_gram_number][upcoming_letter] += 2
            # print(self.counts)

    # text2 is the starting text we use
    # length_of_sequence is going to be the length I wish my text to have
    def babble(self, text2, length_of_sequence):

        n_gram_letter = text2[:self.n].lower()
        # NOTE: I didn't see a big difference after I made it lower case
        # sequence is the sequence I'm trying to generate
        sequence = n_gram_letter

        for i in range(length_of_sequence):
            # now let's generate next letter after our n_gram_letter using choices()
            # we will figure out which letter occurred the most within this function call
            # by looking in our dictionary self.counts, looking at its keys and values (of n_gram_letter) (for this we
            # also use weights that gives higher weight to the letter that occurred most frequently)
            generate_next_letter = choices(list(self.counts[n_gram_letter].keys()), weights = list(self.counts[n_gram_letter].values()), k = 1)[0]
            # now bump up sequence with the generated letter
            sequence += generate_next_letter
            # now choose new n_gram_letter to be the new letter added, so you exclude self.n
            n_gram_letter = sequence[-self.n:]
        print(sequence)


b = Babbler(2, text)
# The length is not 11 but 1579
# print(len(b.counts))
b.babble('This is babbler.. kinda', 200)
