factorial

public int factorial(int n) {
if (n == 1) return 1;
return n*factorial(n-1);
}
bunnyEars
public int bunnyEars(int bunnies) {
//if (bunnies == 0) return 0;
return bunnies*2;
}
fibonacci
public int fibonacci(int n) {
if (n == 0) return 0;
if (n == 1) return 1;
return fibonacci(n-1)+fibonacci(n-2);
}
bunnyEars2
public int bunnyEars2(int bunnies) {
return bunnies * 5/2;
}
triangle
public int triangle(int rows) {
if (rows == 0) return 0;
return rows + triangle(rows - 1);
}
sumDigits
public int sumDigits(int n) {
if (n == 0) return 0;
return n % 10 + sumDigits(n / 10);
}
count7
public int count7(int n) {
if (n == 0) return 0;
if (n % 10 == 7) return 1 + count7(n / 10);
return count7(n / 10);
}

count8
public int count8(int n) {
if (n == 0) return 0;
if (n >= 88 && n % 100 == 88) return 2 + count8(n / 10);
if (n % 10 == 8) return 1 + count8(n / 10);
return count8(n / 10);
}
powerN
public int powerN(int base, int n) {
if (n == 1) return base;
if (n == 0) return 1;
return base * powerN(base, n-1);
}
countx
public int countX(String str) {
if (str.length() == 0) return 0;
if (str.charAt(0) != 'x') return countX(str.substring(1));
return 1 + countX(str.substring(1));
}
countHi
public int countHi(String str) {
if (str.length() < 2) return 0;
if (str.substring(0,2).equals("hi")) return 1 + countHi(str.substring(1));
else return countHi(str.substring(1));
}
change XY
public String changeXY(String str) {
if (str.equals("")) return str;
if (str.charAt(0) == 'x') return 'y' + changeXY(str.substring(1));
return str.charAt(0) + changeXY(str.substring(1));
}
changePi
public String changePi(String str) {
if (str.length() < 2) return str;
if (str.substring(0,2).equals("pi")) return "3.14" + changePi(str.substring(2));
return str.charAt(0) + changePi(str.substring(1));
}
noX
public String noX(String str) {
if (str.equals("")) return str;
if (str.charAt(0) == 'x') return noX(str.substring(1));
else return str.charAt(0) + noX(str.substring(1));
}
strDist
public int strDist(String str, String sub) {
if (!str.equals(sub) && str.length() <= sub.length()) return 0;
if (!str.startsWith(sub)) return strDist(str.substring(1, str.length()), sub);
if (!str.endsWith(sub)) return strDist (str.substring(0, str.length()-1), sub);
return str.length();
}
strCopies
public boolean strCopies(String str, String sub, int n) {
if(str.indexOf(sub)==-1 && n==0) return true;
if(str.indexOf(sub)!=-1) return strCopies(str.substring(str.indexOf(sub)+1), sub, n-1);
return false;
}
strCount
public int strCount(String str, String sub) {
int original = sub.length();
if(str.length() < original)
return 0;
if(str.substring(0, original).equals(sub))
return 1 + strCount(str.substring(original), sub);
return strCount(str.substring(1), sub);
}
nesParen
public boolean nestParen(String str) {
if(str.equals("")) return true;
if(str.charAt(0)!='(' || str.charAt(str.length()-1)!=')' ) return false;
else return nestParen(str.substring(1,str.length()-1));
}
parenBit
public String parenBit(String str) {
if (str.equals("")) return str;
if (str.charAt(0) == '(') {
if (str.charAt(str.length()-1) == ')')
return str;
else
return parenBit(str.substring(0, str.length()-1));
} else
return parenBit(str.substring(1));
}
countHi2
public int countHi2(String str) {
int length = str.length();
if (length < 2) return 0;
if (str.substring(length-2, length).equals("hi")) {
if ((length > 2 && str.charAt(length-3) != 'x') || length == 2 )
return 1 + countHi2(str.substring(0, length-1));
}
return countHi2(str.substring(0, length-1));
}
stringClean
public String stringClean(String str) {
if (str.length() < 2) return str;
if (str.charAt(0) == str.charAt(1)) return stringClean(str.substring(1));
return str.charAt(0) + stringClean(str.substring(1));
}
count11
public int count11(String str) {
if(str.length() < 2) return 0;
if(str.substring(0, 2).equals("11")) return 1 + count11(str.substring(2));
return count11(str.substring(1));
}
countAbc
public int countAbc(String str) {
if (str.length() < 3) return 0;
if (str.substring(0,3).equals("abc") || str.substring(0,3).equals("aba"))
return 1 + countAbc(str.substring(1));
return countAbc(str.substring(1));
}
countPairs
public int countPairs(String str) {
if(str.length() < 3) return 0;
if(str.charAt(0) == str.charAt(2)) return 1 + countPairs(str.substring(1));
return countPairs(str.substring(1));
}
endX
public String endX(String str) {
if(str.length() == 0) return str;
if(str.charAt(0) == 'x') return endX(str.substring(1)) + 'x';
return str.charAt(0) + endX(str.substring(1));
}
pairStar
public String pairStar(String str) {
if(str.length() < 2) return str;
if(str.charAt(0) == str.charAt(1)) return str.charAt(0) + "*" + pairStar(str.substring(1));
return str.charAt(0) + pairStar(str.substring(1));
}
allStar
public String allStar(String str) {
if(str.length() < 2) return str;
return str.charAt(0) + "*" + allStar(str.substring(1));
}
array 220
public boolean array220(int[] nums, int index) {
if(index >= nums.length - 1) return false;
if(nums[index] * 10 == nums[index+1]) return true;
return array220(nums, index + 1);
}
array11
public int array11(int[] nums, int index) {
if(index == nums.length) return 0;
if(nums[index] == 11) return 1 + array11(nums, index + 1);
return array11(nums, index + 1);
}
array6
public boolean array6(int[] nums, int index) {
if(index == nums.length) return false;
if(nums[index] == 6) return true;
return array6(nums, index + 1);
}
