/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wordbag;

/**
 *
 * @author cbierman
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
            currFreq = myArray[index].getFrequency();
            currFreq = currFreq + 1;
            MyWord safe = myArray[index];
            safe.setFrequency(currFreq);
            
            if (index == 0) {
                myArray[index] = safe;
            }
            
            else if (currFreq > myArray[index -1].getFrequency()) {
                int place = binarySearch(myArray, 0, index, currFreq);
                if (place == -1){
                    for (int i = 0; i< numWords;i++) {
                        if (myArray[i].getFrequency() < currFreq) {
                            place = i;
                            break;
                        }
                    }
                    
                }
               
                for (int j = index; j>place; j--) {
                    myArray[j] = myArray[j-1];
                }
                
                myArray[place]=safe;
                //System.out.println(myArray[place].getWord() + " went from " + index + " to " + place);
            }
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
        String [] words = new String [5];
        words[0] = "tim";
        words[1] = "steve";
        words[2] = "paul";
        words[3] = "austin";
        words[4] = "jim";
        
       for(int i = 0; i <= 10; i++) {
          Random r = new Random();
           int x = r.nextInt(4);
           bg.addOccurance(words[x]);
       }
     
        //bg.removeOccurance("jim");
//         bg.addOccurance("tim");
//         bg.addOccurance("steve");
//         bg.addOccurance("austin");
//         bg.addOccurance("austin");
//         bg.addOccurance("jim");
//         bg.addOccurance("tim");
//         bg.addOccurance("tim");
//         bg.addOccurance("austin");
//         bg.addOccurance("steve");
//         bg.addOccurance("steve");
//         bg.addOccurance("steve");
//         bg.addOccurance("jim");
//         bg.addOccurance("jim");
//         bg.addOccurance("jim");
//         bg.addOccurance("tim");
//         bg.addOccurance("steve");
//         bg.addOccurance("jim");
//         bg.addOccurance("steve");
//         bg.addOccurance("austin");
//         bg.addOccurance("austin");
//         bg.addOccurance("steve");
//         bg.addOccurance("tim");
//         bg.addOccurance("steve");
//         bg.addOccurance("jim");
//         bg.addOccurance("tim");
//         bg.addOccurance("steve");
//         bg.addOccurance("austin");
//         bg.addOccurance("austin");
//         bg.addOccurance("austin");
//         bg.addOccurance("austin");
//         bg.addOccurance("austin");
//         bg.addOccurance("jim");
         
         
         for(int y = 0; y < bg.numWords; y++){
            System.out.print(bg.myArray[y].getWord() + " ");
            System.out.println(bg.myArray[y].getFrequency());
            //System.out.println(bg.myArray[y]);
       }
        
        
        //System.out.println(binarySearch(bg.myArray, 0, (bg.numWords)-1 , 2));
        
        
       
    }
}
