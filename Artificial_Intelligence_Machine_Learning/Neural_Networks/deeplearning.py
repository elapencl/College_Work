import keras
from keras.datasets import cifar10
from keras.models import Sequential
from keras.layers import Dense, Dropout, Flatten, Conv2D, MaxPooling2D

# Here we built the deep convolutional network, step 5. in the assignment

# load data into arrays
(train_images, train_labels), (test_images, test_labels) = cifar10.load_data()

# change labels to numbers that will tell us whether a class is in it or not
# aka one hot encoding
train_labels_one = keras.utils.to_categorical(train_labels, 10)
test_labels_one = keras.utils.to_categorical(test_labels, 10)

# as values are represented from 0 to 1 then we need float
train_images = train_images.astype('float32')
test_images = test_images.astype('float32')
# as pixels are presented from 0 to 255 just divide by that value
train_images = train_images/255
test_images = test_images/255

# we will use Sequential model aka the empty one for building the layers
model = Sequential()
# build conv layer one with the corresponding pool layer
model.add(Conv2D(32, (3, 3), activation='relu', padding='same', input_shape=(32, 32, 3)))
model.add(MaxPooling2D(pool_size=(2, 2)))
# now a second one
model.add(Conv2D(32, (3, 3), activation='relu', padding='same'))
model.add(MaxPooling2D(pool_size=(2, 2)))
# now a third one
model.add(Conv2D(64, (3, 3), activation='relu', padding='same'))
model.add(MaxPooling2D(pool_size=(2, 2)))
# add flatten layer to flatten the cube looking shape of neurons
model.add(Flatten())
# add two dense layers with activations relu and softmax
model.add(Dense(512, activation='relu'))
model.add(Dense(10, activation='softmax'))
# check summary
model.summary()

# now compile and train model (remember to use 50 epochs)
model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['accuracy'])

model.fit(train_images, train_labels_one, batch_size=32, epochs=50, validation_split=0.2)

# and get accuracy
model.evaluate(test_images, test_labels_one)