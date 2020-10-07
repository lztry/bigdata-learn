import java.util.HashMap;
import java.util.Stack;

public class test {
    public static  boolean tesss(String s){
        if(s.length()%2!=0||s.length()==0)
           return false;
        Stack<Character> stack=new Stack<>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('||s.charAt(i)=='[')
                stack.push(s.charAt(i));
            else{
                if(!stack.empty()) {
                    int result = (int) stack.pop() - (int) (s.charAt(i));
                    if (result == -1 || result == -2)
                        continue;
                    else
                        return false;
                }
            }
        }
        if(stack.empty())
            return true;
        else
            return false;
    }
    public static int removeDuplicates(int[] nums) {
        if(nums.length <= 1)
            return nums.length;
        HashMap<Integer,Integer> keyMap  = new HashMap<>();
        HashMap<Integer,Integer> valueMap  = new HashMap<>();
        valueMap.put(nums[0],0);
        keyMap.put(0,nums[0]);
        int location=1;
        for(int i=1;i<nums.length;i++){
            if(valueMap.get(nums[i])==null){
                valueMap.put(nums[i],location);
                keyMap.put(location++,nums[i]);
            }

        }
      
        for(int i=1;i<location;i++)
            nums[i]=keyMap.get(i).intValue();
        return location;
    }
    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{1,1,2}));
    }
}
