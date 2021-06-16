import numpy as np
import matplotlib.pyplot as plt

"""
    Returns an array of five numbers, to be used as coefficients of a polynomial. Each number is chosen uniformly from the
    interval [-0.5, 0.5).
 """


def generate_coefficients():
    coef_of_polynomial = np.random.uniform(-0.5, 0.5, 5)
    return coef_of_polynomial


"""
    Returns two arrays, X and y, each of which is m by 1. The values of X are evenly spaced across the interval
    [-5.0, 5.0]. For each x, the corresponding y value is

    a + b * X + c * X**2 + d * X**3 + e * X**4 + <noise>

    where coefficients is (a, b, c, d, e) and the noise for each point is normally distributed with mean 0 and
    standard deviation 1.
    """


def generate_data(m, coefficients):
    x_array = np.linspace(-5.0, 5.0, m).reshape(m, 1)
    y_array = []
    # noise = np.random.randn(0, 1, 1)
    for i in x_array:
        noise = np.random.randn(1)
        y_array.append(
            coefficients[0] + coefficients[1] * i + coefficients[2] * i ** 2 + coefficients[3] * i ** 3 + coefficients[
                4]
            * i ** 4 + noise[0])
    return x_array, np.array(y_array)

# test the code by typing python test_polynomial.py in the terminal

# for the following two tasks we need some sklearn
from sklearn.linear_model import LinearRegression
from sklearn.preprocessing import PolynomialFeatures

"""
   Returns a trained model that fits a polynomial of the specified degree to the data.
   """


def fit_curve(X, y, degree):
    poly_features = PolynomialFeatures(degree=degree)
    x_train_poly = poly_features.fit_transform(X)
    poly_reg = LinearRegression()
    return poly_reg.fit(x_train_poly, y)


"""
    Plots a curve for model, which represents a polynomial of the specified degree.
    The x values for the curve are 100 points evenly spaced across the interval [-5.0, 5.0].
    """


def plot_curve(degree, model):
    x_new = np.linspace(-5.0, 5.0, 100).reshape(-1, 1)
    plot1 = PolynomialFeatures(degree=degree)
    y_new = model.predict(plot1.fit_transform(x_new))
    plotting = plot1.fit_transform(x_new, y_new)
    plt.plot(x_new, y_new, label=degree)


# some code to test your plot
# c = generate_coefficients()
# x_array, y_array = generate_data(100, c)
# plt.scatter(x_array, y_array)
# plt.show()

"""
    Plots X and y (as a scatter plot) and also constrains the y limit so that later, much larger values of y will not
    reset it.
    """


# Peter's code
def plot_data(X, y):
    plt.ylim((y.min() - 0.1 * (y.max() - y.min()),
              y.max() + 0.1 * (y.max() - y.min())))
    plt.scatter(X, y)


"""
    Generates m training points and fits models of degrees 1, 2, and 20. Plots the data and the curves for the models.
    """


# Peter's code
def experiment_1(m):
    coeffs = generate_coefficients()
    X, y = generate_data(m, coeffs)
    plot_data(X, y)
    for d in [1, 2, 20]:
        model = fit_curve(X, y, d)
        plot_curve(d, model)
    plt.xlabel('x')
    plt.ylabel('y')
    plt.legend()
    plt.show()


# run this code below to get the first plot
# experiment_1(20)

# some more sklearn
from sklearn.metrics import mean_squared_error

"""
   Returns the mean squared error for model (a polynomial of the specified degree) on X and y.
   """


def mse(X, y, degree, model):
    y_true = y
    poly_features = PolynomialFeatures(degree=degree)
    y_pred = model.predict(poly_features.fit_transform(X))
    return mean_squared_error(y_true, y_pred)


"""
    Runs the following experiment 100 times:

    Generate m training data points
    Generate 100 testing data points (using the same coefficients)
    For each d from 1 through 30, fit a curve of degree d to the training data and measure its mse on the testing data.

    After the 100 runs, plots the average mse of each degree.
    """


# Some more of Peter's code
def experiment_2(m):
    mses = {i: [] for i in range(1, 31)}
    for i in range(100):
        coeffs = generate_coefficients()
        X_train, y_train = generate_data(m, coeffs)
        X_test, y_test = generate_data(100, coeffs)
        for d in range(1, 31):
            model = fit_curve(X_train, y_train, d)
            mses[d] += [mse(X_test, y_test, d, model)]
    averages = [np.mean(mses[d]) for d in mses]
    plt.ylim(0, 500)
    plt.plot(range(1, 31), averages)
    plt.xlabel('Degree')
    plt.ylabel('Average MSE (100 runs)')
    plt.show()


experiment_2(20)
