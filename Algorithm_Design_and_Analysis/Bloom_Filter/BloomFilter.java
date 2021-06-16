import java.lang.Long;

public class BloomFilter<S> {

    //table of longs to store filter data
    public long [] bitArray = new long[1024];

    //Returns an array[2] with the low-order bits and high-order bits of a given string
    public int[] ourHash(String OurString){
        int hashed;
        int[] bits = new int[2];
        hashed = OurString.hashCode();

        ///splits the 32 bit int into two 16 bit ints and returns
        bits[0] = (hashed>>>16) & 0xFFFF;
        bits[1] =  hashed & 0xFFFF;
        return bits;
    }

    //Turns on two bits in our bitArray corresponding to our hashed string
    public void add(String OurString) {
        int[] bits;
        int index1,a,index2,b;

        //calls our hash function on the string
        bits = ourHash(OurString);

        index1 = bits[0]/64; //index of long (out of 1024) for first hash
        a = bits[0] - (64*index1); //which bit to turn on (out of 64)
        bitArray[index1] |= (1L << a); //turns the bit on

        index2 = bits[1]/64; //index for second hash
        b = bits[1] - (64*index2);
        bitArray[index2] |= (1L << b);
    }

    //Returns True if the two bits corresponding to the given string are turned on
    public boolean mightContain(String OurString){
        int[] bits;
        int index1,a,index2,b;

        bits = ourHash(OurString);

        index1 = bits[0]/64;//index of long (out of 1024) for first hash
        a = bits[0] - (64*index1);//which bit to turn on (out of 64)

        index2 = bits[1]/64; //index for second hash
        b = bits[1] - (64*index2);

        //checks if the indices are turned on
        if( ((bitArray[index2] & (1L << b)) != 0) && ((bitArray[index1] & (1L << a)) != 0 )){
            return true;
        }
        else return false;
    }

    //Returns the number of bits turned on in our bitArray
    public int trueBits(){
        int count = 0;
        //loops through the table and counts turned on bits
        for (int i=0;i<1024;i++){
            count += Long.bitCount(bitArray[i]);
        }
        return count;
    }
}