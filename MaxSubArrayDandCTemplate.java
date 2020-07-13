/*
    Grade = 100%, Great
*/
public class MaxSubArrayDandCTemplate 
{
    //Divide and Conquer, Corman pg 71; O(nlog2n)
    //int low, mid; high;
    public static void main(String[] args) 
    {
        int[] values = {0, 13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, 
                       -22, 15, -4, 7};
        int low = 1; int high = 16; int mid = (low + high) / 2;
       
        int[] LRMax = new int[3];
        
        
        LRMax =maxCrossingSubarray(values, low, mid, high );
        System.out.println(LRMax[0] + " " + LRMax[1] + " " +LRMax[2]);

        LRMax = maxSubarray(values, low, high );
        System.out.println(LRMax[0] + " " + LRMax[1] + " " +LRMax[2]);
       
        LRMax = bruteForceMaxSubarray(values, low, high);       
        System.out.println(LRMax[0] + " " + LRMax[1] + " " +LRMax[2]);
    }
    
    public static int[] maxSubarray(int[] values, int low, int high)
    {   //returns LRMax: LRMax[0 and 1] -> left and right index of max subarray
        //        and LRMax[2] -> the crossing sub array's sum
       int[] lLRMax = new int[3];
       int[] rLRMax = new int[3];
       int[] cLRMax = new int[3];
       int[] LRMax = new int[3];
       int mid=0;
       
       //base case
       if (high ==low)
       {
           LRMax[0]=low;
           LRMax[1]=high;
           LRMax[2] = values[low];
       }
       
       //reduced problem
       else
       {
           mid = ((low+high)/2);
           lLRMax = maxSubarray(values, low, mid);
           rLRMax = maxSubarray(values, mid+1, high);
           cLRMax = maxCrossingSubarray(values, low, mid, high);
           
           
           if (lLRMax[2] >= rLRMax[2] && lLRMax[2] >= cLRMax[2])
           {
               return(lLRMax);
           }
           
           if (rLRMax[2] >= lLRMax[2] && rLRMax[2] >= cLRMax[2])
           {
               return(rLRMax);
           }
           
           else
           {
               return cLRMax;
           }
           
             
       }
       
      return LRMax; 
      
    }
    
    public static int[] maxCrossingSubarray(int[] values, int low, int mid,
                                           int high)
    {   // assumes max sub array includes the midpoint of the array
        //returns LRMax: LRMax[0 and 1] -> left and right index of max crossing
        //        subarray; and LRMax[2] -> the crossing sub array's sum 
        int[] LRMax = new int[3];
        int leftSum = Integer.MIN_VALUE;
        int sum=0;
        int maxLeft=0;
        //left side of crossing array
        for(int i = mid; i >= low; i--)
        {
            sum = sum + values[i];
            if (sum > leftSum)
            {
                leftSum = sum;
                maxLeft = i;
            }
            
        }
        
        //right side of crossing array
        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        int maxRight=0;
        for(int j = mid + 1; j <= high; j++)
        {
            sum = sum + values[j];
            if (sum > rightSum)
            {
                rightSum = sum;
                maxRight = j;
            }
            
        }
        
        LRMax[0]=maxLeft;
        LRMax[1]= maxRight;
        LRMax[2]=leftSum + rightSum;
        
        
        return LRMax;
    }
    
    public static int[] bruteForceMaxSubarray(int[] values, int low, int high)
    {
        int[] LRMax = new int[3];
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;        
        for(int left = low; left <= high; left++)
        {
            sum = 0;
            for(int right = left; right <= high; right++)
            {
                sum = sum + values[right];
                if(sum > maxSum)
                {
                    maxSum = sum;
                    LRMax[0] = left;
                    LRMax[1] = right;
                    LRMax[2] = maxSum;
                    
                }    
            }    
            System.out.println("with left = " + left + " max sum  is " + maxSum + 
                               " from index   " + LRMax[0] + " to "+ LRMax[1] );
        }
        return LRMax;
    }
}
