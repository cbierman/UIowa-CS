/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wordbag;

/**
 *
 * @author Cale Bierman
 * @hawkid cbierman
 */

import java.util.Random;

public class WordBag {
    
    MyWord [] myArray;
    static int defaultCapacity = 1000;
    int numWords; //how many distinct words
    
    public WordBag() {
        this(defaultCapacity);
    }
    
    public WordBag(int x) {
       if (x < defaultCapacity)
           x = defaultCapacity + 1;
       myArray = new MyWord [x];
       numWords = 0;
    }
    
    //Increases the frequency of the provided word and keeps myArray in decreasing
    //order
    public void addOccurance(String word) {
        int currFreq;
        int index = getIndexOf(word);
        //System.out.println(index + " <<<<<<<<");
        
        //If the index == numWords, then we have a new word to go inside the list
        if (index == numWords) {
            myArray[index] = new MyWord(word);
            numWords++;
        }
        else {
            //Otherwise, we get the current freqency and add one to it
            currFreq = myArray[index].getFrequency();
            currFreq = currFreq + 1;
            
            //safe will be used as a handle for the word we're adding an occurance to
            MyWord safe = myArray[index];
            safe.setFrequency(currFreq);
            //if the index is 0, the word is already the most frequent word
            //so we just increase it's frequency 
            if (index == 0) {
                myArray[index].setFrequency(currFreq);
            }
            //otherwise, if increasing the frequency puts myArray out of order,
            //we need to find where it needs to go and put it there
            else if (currFreq > myArray[index -1].getFrequency()) {
                //Use binary search to find the index of where the word needs to go
                int place = binarySearch(myArray, 0, index, currFreq);
                //However, if the new frequency is not in myArray, we have to 
                //linearly search for where it needs to go
                if (place == -1){
                    for (int i = 0; i< numWords;i++) {
                        if (myArray[i].getFrequency() < currFreq) {
                            place = i;
                            break;
                        }
                    }
                    
                }
                //We then shift the rest of the list items over by 1 space
                //to make room.
                for (int j = index; j>place; j--) {
                    myArray[j] = myArray[j-1];
                }
                //and insert the new frequency into it's correct position
                myArray[place]=safe;
                
            }
            //This fires off when increasing the frequency doesn't affect the
            //order.
            else {
                myArray[index].setFrequency(currFreq);
            }
        }
    }
    
    public void removeOccurance(String word) {
        
        int index = getIndexOf(word);
        
        if (index == numWords)
            return;
        
        int currFreq = myArray[index].getFrequency();
        if (currFreq > 1) {
            myArray[index].setFrequency(currFreq - 1);
        }
        else {
            for (int j = index + 1; j < numWords; j++) {
                myArray[j-1] = myArray[j];
            }
            numWords--;
        }
        
    }
    
    public String mostFrequent(){
        return myArray[0].getWord();
        
    }
    
    public int numberOfWords() { //return number of distinct words
        return numWords;
    }
    
    public int getFrequency(String word) {
        int index = getIndexOf(word);
        if (index < numWords) {
            return myArray[index].getFrequency();
        }
        return -1;
    }
    
    private int getIndexOf(String word) {
        int index = numWords;
        
        for (int i = 0; i < numWords; i++) {
            if (myArray[i].getWord().equals(word)) {
                index = i;
                break;
            }
        }
        
        return index;
    }
    //Uses recursion to find the index of x within the Array A.
    public static int binarySearch(MyWord [] A, int start, int end, int x) {
        
        int mid = (start + end)/2;
        
        if (start > end) { 
            return -1;
        }
        
        if (A[mid].getFrequency() == x) {
            return mid;
        }
        
        else if (A[mid].getFrequency() < x) {
            return binarySearch(A, start, mid-1, x);
        }
        
        else {
            return binarySearch(A, mid+1, end, x);
        }
        
        
       
       
        
        
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        WordBag bg = new WordBag();

         
         
         for(int y = 0; y < bg.numWords; y++){
            System.out.print(bg.myArray[y].getWord() + " ");
            System.out.println(bg.myArray[y].getFrequency());
            //System.out.println(bg.myArray[y]);
       }
        
        
        //System.out.println(binarySearch(bg.myArray, 0, (bg.numWords)-1 , 2));
        
        
       
    }
}
