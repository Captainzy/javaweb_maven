package arithmetic;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Sort{

	/** @Title: main
	  * @Description: TODO
	  * @author zouyang    
	  * @data 2016年5月16日 下午2:22:39
	  */
	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		int num = 1000000;
		int[] random = {1,10,2,22,3,5,2,11,98,9,8,8,76,1,1,1,323,3,23,3,32,13,6};
		//int[] random = {1, 4, 6, 7, 6, 6, 7, 6, 8, 6};sort
		//int[] random = getRandom(num);
		long begin = System.currentTimeMillis();
		//int[] rs = bubble(random);
		//quickSort(random, 0, random.length);
		//selectSort(random);
		//insertSort(random, 0, random.length);
		//QsortThreeInsertGather(random, 0, random.length);
		//Arrays.sort(random);
		mergeSort(random);
		long end = System.currentTimeMillis();
		
		System.out.println("排序花费时间："+(end-begin));
		for(int i = 0;i<random.length;i++){
			System.out.print(random[i]+"  ");
		}
	}

	public static int[] getRandom(int num){
		int[] r = new int[num];
		Random random = new Random();
		for(int i=0;i<num;i++){
			r[i] = random.nextInt(100);
		}
		return r;
	}

	public static void bubble(int[] random){
		for(int i = 0;i<random.length;i++){
			for(int j = 0;j<random.length-i-1;j++){
				if(random[j]>random[j+1]){
					int t = random[j];
					random[j] = random[j+1];
					random[j+1] = t;
				}
			}
		}
	}
	public static void quickSort(int[] random,int begin,int end){
		/**
		 * for each (unsorted) partition
			  set first element as pivot
			  storeIndex = pivotIndex + 1
			  for i = pivotIndex + 1 to rightmostIndex
			    if element[i] < element[pivot]
			      swap(i, storeIndex); storeIndex++
			  swap(pivot, storeIndex - 1)
		 */
		if(begin>=end){
			return ;
		}else{
			int pivot = random[begin],storeIndex = begin+1,t = 0;
			for(int i=begin;i<end;i++){
				if(random[i]<pivot){
					t = random[i];
					random[i] = random[storeIndex];
					random[storeIndex] = t; 
					storeIndex++;
				}
			}
			t = random[storeIndex-1];
			random[storeIndex-1] = pivot;
			random[begin] = t;
			quickSort(random,begin,storeIndex-2);
			quickSort(random,storeIndex,end);
		}
	}
	
	public static void selectSort(int random[]){
		int min = 0,index = 0 ;
		for(int i = 0;i<random.length;i++){
			min = random[i];
			index = i;
			for(int j = i;j<random.length;j++){
				if(random[j]<min){
					min = random[j];
					index = j;
				}
			}
			int t = random[i];
			random[i] = min;
			random[index] = t;
		}
	}
	
	public static void insertSort(int random[],int low,int hight){
		for(int i = low+1;i<hight;i++){
			if(random[i]<random[i-1]){
				for(int j = i;j>low;j--){
					if(random[j]<random[j-1]){
						int t = random[j];
						random[j] = random[j-1];
						random[j-1] = t;
					}else{
						break;
					}
				}
			}
		}
	}
	
	public static void QsortThreeInsertGather(int[] arr, int low, int high)
    {
        if (high - low  < 10)
        {
            insertSort(arr, low, high);
        }                                               //插排，递归出口
        arr = PartitionMedianOfThree(arr, low, high-1);  //三数取中       
        int first = low;
        int pivot =arr[first];
        int storeBeginIndex = first + 1;
        int storeEndIndex = high-1;
        for(int i = storeBeginIndex;i<storeEndIndex;i++){
        	if(arr[i]<pivot){
        		int t = arr[i];
        		arr[i] = arr[storeBeginIndex];
        		arr[storeBeginIndex] = t;
        		storeBeginIndex++;
        	}
        	if(arr[i]>pivot){
        		int t = arr[i];
        		arr[i] = arr[storeEndIndex];
        		arr[storeEndIndex] = t;
        		storeEndIndex--;
        	}
        }
        int t = arr[first];
        arr[first] = arr[storeBeginIndex-1];
        arr[storeBeginIndex-1] = t;
        
        QsortThreeInsertGather(arr, low, storeBeginIndex);
        QsortThreeInsertGather(arr, storeEndIndex, high);
    }
	
	 public static int[] PartitionMedianOfThree(int[] arr, int low, int high)
     {
         int mid = low + (high - low) / 2;
         if (arr[mid] > arr[high])
         {
             int t = arr[mid];
             arr[mid] = arr[high];
             arr[high] = t;
         }
         if (arr[low] > arr[high])
         {
             int t = arr[low];
             arr[low] = arr[high];
             arr[high] = t;
         }
         if (arr[mid] > arr[low])
         {
             int t = arr[mid];
             arr[mid] = arr[low];
             arr[low] = t;
         }                                                //将中间大小的数与第一个数交换
         return arr;
     }
	 
	 public static void mergeSort(int random[]){
		 for(int i = 1;i<random.length;i=i*2){
			 merge(random,0,i);
		 }
	 }
	 
	 public static void merge(int random[],int begin,int gap){
		 int i = begin;
		 while(i+2*gap<random.length){
			 mSort(random,i,gap,i+2*gap);
			 i = i+2*gap;
		 }
		 
		 if(i+gap<random.length){
			 mSort(random,i,gap,random.length);
		 }
	 }
	 
	 public static void mSort(int random[],int begin,int gap,int end){
		 int middle  = begin + gap;
		 int[] copyArray = new int[random.length];
		 int i = begin,j = middle;
		 int k = 0;
		 while(i<middle&&j<end){
			 if(random[i]<=random[j]){
				 copyArray[k++] = random[i];
				 i++;
			 }else{
				 copyArray[k++] = random[j];
				 j++;
			 }
		 }
		 while(i<middle){
			 copyArray[k++] = random[i];
			 i++;
		 }
		 while(j<end){
			 copyArray[k++] = random[j];
			 j++;
		 }
		 for(int n = 0;n<k;n++){
			 random[begin++] = copyArray[n];
		 }
	 }
}
