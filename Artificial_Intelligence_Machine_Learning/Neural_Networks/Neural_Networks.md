This is a group assignment. Go into the "People" section and join a group for "Neural Networks"; this may, but need not, be the same group you were in for the previous assignment. Groups may have between 1 and 3 people.

This assignment asks you to build a small neural network, to be trained by backpropagation, from scratch.

The Task
Write a class Network. It needs:

An initializer that takes three arguments: the number of input units, the number of hidden units, and the number of output units.
A method run that takes a list of input values and returns a list of the network's output values.
A method train that takes a list of input values and a list of output values (for one data point) and updates the network.
Given this class, you'll be able to run this method:

def experiment(sizes, inputs, targets):
    net = Network(*sizes)
    print('Before training:')
    for input in inputs:
        print(f'{input} -> {net.run(input)}')
    print('Training...')
    training = list(zip(inputs, targets))
    for epoch in range(10000):
        random.shuffle(training)
        for input, target in training:
            net.train(input, target)
    print('After training:')
    for input in inputs:
        print(f'{input} -> {net.run(input)}')
For example, if you run it as

experiment((2, 2, 1),
           [[0, 0], [0, 1], [1, 0], [1, 1]],
           [[0], [1], [1], [0]])
you are training a network with two inputs, two hidden units, and one output on the classic XOR problem. The output might look like this:

Before training:
[0, 0] -> [0.5029849688633979]
[0, 1] -> [0.5032886301654518]
[1, 0] -> [0.5034411613226247]
[1, 1] -> [0.5037442195609219]
Training...
After training:
[0, 0] -> [0.01087756030966525]
[0, 1] -> [0.9885905127593018]
[1, 0] -> [0.988592864310219]
[1, 1] -> [0.014061246808186934]
but it will vary from one run to the next. Occasionally the training might fail (getting caught in a local minimum), producing output after training like this:

[0, 0] -> [0.013077832863079095]
[0, 1] -> [0.6778207621420899]
[1, 0] -> [0.6778207939081854]
[1, 1] -> [0.6780989368775135]
If that happens, just run it a few more times. Increasing the number of hidden units from 2 to, say, 5 should greatly reduces the frequency of this problem.

Here's a second, more complicated problem: given three inputs, indicate both (a) whether at least two inputs are on and (b) whether an odd number of inputs are on.

experiment((3, 10, 2),
           [[0, 0, 0], [0, 0, 1], [0, 1, 0], [0, 1, 1], [1, 0, 0], [1, 0, 1], [1, 1, 0], [1, 1, 1]],
           [[0, 0], [0, 1], [0, 1], [1, 0], [0, 1], [1, 0], [1, 0], [1, 1]])
Here's typical output:

Before training:
[0, 0, 0] -> [0.48686323181574576, 0.459528820810853]
[0, 0, 1] -> [0.4860380335140693, 0.45957974267495244]
[0, 1, 0] -> [0.4862845790503335, 0.4592343858562806]
[0, 1, 1] -> [0.4854620178509439, 0.45928567164978906]
[1, 0, 0] -> [0.4864542580801006, 0.4581975189205594]
[1, 0, 1] -> [0.48562936200254003, 0.458249278358981]
[1, 1, 0] -> [0.48587549808698943, 0.45790293581452685]
[1, 1, 1] -> [0.48505271281642603, 0.4579550040296144]
Training...
After training:
[0, 0, 0] -> [5.192639305148142e-06, 0.009438941066859805]
[0, 0, 1] -> [0.004240896304127239, 0.9915434134589711]
[0, 1, 0] -> [0.004124662349360806, 0.9913493198316747]
[0, 1, 1] -> [0.9953847929472297, 0.009759604353725857]
[1, 0, 0] -> [0.004155631022139605, 0.9914676573925766]
[1, 0, 1] -> [0.995003520885536, 0.009579535110106981]
[1, 1, 0] -> [0.995147401329774, 0.009674861515105665]
[1, 1, 1] -> [0.999924643077542, 0.9871860649610166]
Hints
This is to be done in raw Python, not using numpy, sklearn, or Keras/TensorFlow.

Set the learning rate to 1. Generate weights uniformly in [-0.1, 0.1].

Don't forget about the bias weight for each unit.

Organization is absolutely crucial here! Constantly ask yourselves:

How can I break the problem into smaller parts?
How would I know if a part was working?
You have considerable freedom in how you approach this. One approach (the one I used) was to define three classes InputNeuron, HiddenNeuron, and OutputNeuron; the Network then contains one list of each type.

Remember to finish updating the activations, then update all of the deltas, then update the weights. If you do try to combine these three steps into one or two loops, the algorithm won't work correctly.

You are not required to "protect" your instance variables with getters and setters; in the name of simplicity for this assignment, leaving them "public" is fine.

What to Hand in
Hand in a single file neural.py containing the definition of your class and any other definitions it uses.
