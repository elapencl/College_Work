/*
Group Neptune's Submission for Gerrymanderer project. Please see response document for detailed
explanation of algorithm

 */

public class NeptuneGerrymanderer implements Gerrymanderer {

    //Creates vertically striped districts
    public void verticalStriper(int[][] result0, int d ){
        int i = 0;

        for (int x = 0; x < d; x++) {
            for (int y = 0; y < d; y++) {

                result0[x][y] = i;
                i++;
            }

        }
    }

    //creates horizontallu striped districts
    public void horizontalStriper(int[][] result1, int d){
        int t = 0;
        for (int x = 0; x < d; x++) {
            for (int y = 0; y < d; y++) {

                result1[y][x] = t;
                t++;
            }

        }
    }

    //creates vertical districts of width 2, and height roughly half of d
    public void districtMappingLegoVertical(int[][] result2, int d){
        int j;
        int p = 0;

        /*
        Because the electorate has odd dimension and this district mapping
        creates width 2 districts, we just work with a d*d-1 size array and make
        the last column its own district.
         */

        j=d-1;

        //loop through creating districts with width 2. See question document for visual
        for (int x = 0; x < j; x++) {
            for (int y = 0; y < d; y++) {
                if (Math.floorMod(x, 2) == 0) {
                    if (y <= (d / 2) ){
                        result2[x][y] = p;
                        p++;
                    }
                    if (y > (d / 2)) {
                        result2[x + 1][y - (d / 2) - 1] = p;
                        p++;
                    }

                } else {
                    if (y < (d / 2)) {
                        result2[x - 1][y + (d / 2)+1] = p;
                        p++;
                    }
                    if (y >= (d / 2)) {
                        result2[x][y] = p;
                        p++;
                    }

                }


            }
        }

        //making the last remaining column its own district
        for(int k=0;k<d;k++){
            result2[d-1][k] = p;
            p++;
        }
    }

    /*
    Creates horizontal districts of width 2, and length roughly half of d
    This function is very similar to the vertical, but with the appropriate
    adjustments to make horizontal districts.
     */

    public void districtMappingLegoHorizontal(int [][] result3,int d){
        int a = d-1;
        int b = 0;
        for (int x = 0; x < d; x++) {
            for (int y = 0; y < d; y++) {

                //this handles the one one remaining row so that we can work with a d*d-1 electorate
                if (Math.floorMod(b,d) == (d-1)){
                    result3[d-1][x]=b;
                    b++;
                }
                else if(Math.floorMod(y, 2) == 0) {
                    if (x <= (a / 2) ){
                        result3[y][x] = b;
                        b++;
                    }
                    if (x > (a / 2)) {
                        result3[y + 1][x - (a / 2) -1] = b;
                        b++;
                    }

                } else {
                    if (x < (a / 2)) {
                        result3[y - 1][x + (a / 2)+1] = b;
                        b++;
                    }
                    if (x >= (a / 2)) {
                        result3[y][x] = b;
                        b++;
                    }

                }


            }
        }

    }


    /*
    Fifth district mapping, almost identical to districtMappingLegoVertical in
    creating districts of width 2, but one column is always an (arbitrary) length 3.
     */

    public void districtMappingFive(int[][] result4, int d){
        int h;
        int u = 0;

        h=d-1;
        for (int x = 0; x < h; x++) {
            for (int y = 0; y < d; y++) {
                if (Math.floorMod(x, 2) == 0) {
                    if (y < (d - 3) ){
                        result4[x][y] = u;
                        u++;
                    }
                    if (y >= (d - 3)) {
                        result4[x + 1][y - (d - 3)] = u;
                        u++;
                    }

                } else {
                    if (y < (3)) {
                        result4[x - 1][y + (d-3 )] = u;
                        u++;
                    }
                    if (y >= (3 )) {
                        result4[x][y] = u;
                        u++;
                    }

                }

            }
        }


        for(int k=0;k<d;k++){
            result4[d-1][k] = u;
            u++;
        }
    }



    @Override
    public int[][] gerrymander(Electorate e, boolean party) {
        //get the number of districts
        int d = e.getNumberOfDistricts();

        //create 5 different result arrays to hold are 5 different district mappings
        int[][] result0 = new int[d][d];
        int[][] result1 = new int[d][d];
        int[][] result2= new int[d][d];
        int[][] result3 = new int[d][d];
        int[][] result4 = new int[d][d];

        //call all of our district mapping functions
        verticalStriper(result0, d);
        horizontalStriper(result1,d);
        districtMappingLegoVertical(result2,d);
        districtMappingLegoHorizontal(result3,d);
        districtMappingFive(result4, d);

        //figure out how well the different mappings perform
        int[] wins = new int[5];
        wins[0] = e.getPurpleWins(result0);
        wins[1] = e.getPurpleWins(result1);
        wins[2] = e.getPurpleWins(result2);
        wins[3] = e.getPurpleWins(result3);
        wins[4] = e.getPurpleWins(result4);

        //if purple gerrymandering, find the best mapping for a purple victory
        int temp = 0;
        if(party) {
            for (int v = 1; v < 5; v++) {
                if (wins[v] > wins[temp]) {
                    temp = v;
                }
            }
        }

        //if yellow wins, find the best mapping for a yellow victory (or where purple did worst)
        else {
            for (int v = 1; v < 5; v++) {
                if (wins[v] < wins[temp]) {
                    temp = v;
                }
            }
        }


        //returns the best mapping
        if(temp == 0){
            return result0;
        }
        if(temp == 1){
            return result1;
        }
        if(temp == 2){
            return result2;
        }
        if(temp == 3){
            return result3;
        }
        if (temp ==4)
        {
            return result4;
        }

        // in case something went wrong we will default to vertical striper
        else return result0;
    }

}
