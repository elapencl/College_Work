# Assignment Python 1

s = 'sphinx of black quartz judge my vow'
nums = [3, -4, 7, -2, 0, 8]
# puns = [1,2,2,3]
n = 10

# Global temperatures (difference from 20th century average in degrees centigrade), 1880 through 2019
# From https://www.ncdc.noaa.gov/cag/global/time-series/globe/land_ocean/ytd/12/1880-2019
temps = [-0.12, -0.09, -0.10, -0.18, -0.27, -0.25, -0.24, -0.28, -0.13, -0.08, -0.34, -0.25, -0.30, -0.33, -0.31, -0.24,
         -0.09, -0.10, -0.27, -0.15, -0.07, -0.15, -0.25, -0.37, -0.45, -0.28, -0.21, -0.38, -0.43, -0.44, -0.40, -0.44,
         -0.34, -0.32, -0.14, -0.09, -0.32, -0.39, -0.30, -0.25, -0.23, -0.16, -0.24, -0.25, -0.24, -0.18, -0.07, -0.17,
         -0.18, -0.33, -0.11, -0.06, -0.12, -0.26, -0.11, -0.16, -0.12, -0.01, -0.02, 0.01, 0.16, 0.27, 0.11, 0.10,
         0.28, 0.18, -0.01, -0.04, -0.05, -0.07, -0.15, 0.00, 0.04, 0.13, -0.10, -0.13, -0.18, 0.07, 0.12, 0.08, 0.05,
         0.09, 0.10, 0.12, -0.14, -0.07, -0.01, 0.00, -0.03, 0.11, 0.06, -0.07, 0.04, 0.19, -0.06, 0.01, -0.07, 0.21,
         0.12, 0.23, 0.28, 0.32, 0.19, 0.36, 0.17, 0.16, 0.23, 0.38, 0.39, 0.29, 0.45, 0.39, 0.24, 0.28, 0.34, 0.47,
         0.32, 0.51, 0.65, 0.44, 0.42, 0.57, 0.62, 0.64, 0.58, 0.67, 0.64, 0.62, 0.54, 0.64, 0.72, 0.58, 0.64, 0.67,
         0.74, 0.93, 0.99, 0.91, 0.83, 0.95]

# Palindrome
# Print True if s is a palindrome (reads the same forward and backward), False otherwise.

print('True' if s == s[::-1] else 'False')

# Vowel Removal
# Print s with all of its vowels removed. For the example above, this is sphnx f blck qrtz jdg my vw.

print("".join((i for i in s if not i == 'a' and not i == 'e' and not i == 'i' and not i == 'o' and not i == 'u')))

# Word Reversal
# Prints s with each word reversed. For the example above, this is xnihps fo kcalb ztrauq egduj ym wov.

# print(s[::-1].reverse())
print(" ".join(i[::-1] for i in s.split(" ")))

# Strictly Increasing
# Print True if nums is a is strictly increasing (i.e., each number is larger than the one before it), False otherwise.

# print('True' if puns is sorted(puns) else 'False')
# print('True' if len(set(puns)) == len(puns) is sorted(puns) else 'False')

print('True' if (nums == sorted(nums)) & (len(set(nums)) == len(nums)) else 'False')

# Negative Flattening
# Print nums, but with 0s in place of all negative numbers. For the example above, this is [3, 0, 7, 0, 0, 8].

print(list(i if i >= 0 else 0 for i in nums))

# Digit Sums
# Print a list of all non negative integers under 100 whose digits add up to a multiple of 5. The first three such
# numbers are 0, 5, and 14. Note that this doesn't depend on the data above.

print(list(l for l in range(0, 100) if sum(int(i) for i in str(l)) % 5 == 0))

# Climate Change
# Print the number of the warmest years on record that have occurred last n years. For the example above, the answer is
# 8 -- that is, the 8 warmest years on record have all occurred in the last 10 years. Put another way, the warmest, 2nd
# warmest, and so on down to 8th warmest years in the entire list appear among the last 10 elements. The 9th does not,
# so it doesn't matter whether the 10th does.
# Note that the answer for n = 20 is 9 but the answer for n = 25 is 22; printing out the sorted n warmest years and
# sorted n most recent years will help explain this confusing result.
# The answer for n = 5 is 5.

counter = 1
while sorted(temps)[-n:][-counter] is sorted(temps[-n:])[-counter]: counter += 1
print(counter - 1)
































