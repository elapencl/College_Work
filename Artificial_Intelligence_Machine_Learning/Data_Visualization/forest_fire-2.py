import pandas as pd
import matplotlib.pyplot as plt

# This is a meteorological dataset mainly used to predict forest fires in the northeast regions of Portugal.
# Found on the Machine Learning Repository website.

plt.style.use('seaborn')

# read in the csv file
forest_data = pd.read_csv('forestfires.csv')

# decide on specific data from the csv file to look at; create variables where each will represent a column in the file
temperature = forest_data['temp']
relative_humidity = forest_data['RH']
wind_speed = forest_data['wind']
rain = forest_data['rain']
burned_area = forest_data['area']

# these tzo lines of code are used for aesthetic reasons: giving the background a greenish color (it's a forest)
ax = plt.axes()
ax.set_facecolor("honeydew")

# now let's plot temp, wind, RH data on the x-axis, while the area data will be for the y-axis
# personalize them by giving each a different color, marker, transparency..
plt.scatter(temperature, burned_area, s=18, alpha=0.9, edgecolor='forestgreen', linewidth=0.16, marker='d', c='r')
plt.scatter(wind_speed, burned_area, s=26, alpha=0.9, edgecolor='chocolate', linewidth=0.4, marker='*', c='b')
plt.scatter(relative_humidity, burned_area, s=20, alpha=0.9, edgecolor='darkmagenta', linewidth=1, marker='3', c='c')

# now add a legend explaining what each marker is
plt.legend(['temperature', 'wind speed', 'relative humidity'])

# give the plot a title and label the x and y axes
plt.title('Forest Fires in Montesinho Park')
plt.xlabel('Temperature (°C), Wind Speed (km/h), Relative Humidity (%)')
plt.ylabel('The Burned Area of the Forest')

# set a limit on the spanning of the axes
plt.xlim([2, 50])
plt.ylim([2, 11])

# leave in the grid in the background but personalize it
plt.grid(color='thistle', linestyle='-.', linewidth=0.3)

# decide on type of layout
plt.tight_layout()
plt.figure(num=None, figsize=(10, 15), dpi=50, facecolor='w', edgecolor='k')

# plot!
plt.show()
