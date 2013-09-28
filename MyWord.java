/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wordbag;

/**
 *
 * @author cbierman
 */
public class MyWord {
    String word;
    int frequency;
    
    public MyWord(String w){
        frequency = 1;
        word = w;
        
    }
    
    public String getWord() {
        return word;
    }
    
    public int getFrequency() {
        return frequency;
       
    }
    
    public void setFrequency(int f) {
        frequency = f;
        
    }
}
