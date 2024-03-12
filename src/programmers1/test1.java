package programmers1;

import java.util.ArrayList;
import java.util.List;

public class test1 {

	public static void main(String[] args) {
		int[] lottos = {0,0,0,0,0,0};
		int[] win_nums = {1,2,3,4,5,6};
		int[] answer = test1.solution(lottos, win_nums);
		
		for(int i=0;i<2;i++) {
			System.out.println(answer[i]);
		}

	}
	
	//쩝..코드가 스레기네요.. 정진하세요
    public static int[] solution(int[] lottos, int[] win_nums) {
        int count = 0;
        int wins = 0;
        int result = 7;
        List<Integer> win_nums_list = new ArrayList<>();
        
        for(int i=0;i<6;i++) {
        	win_nums_list.add(win_nums[i]);
        }
        
        for(int i=0;i<6;i++) {
        	if(lottos[i]==0) {
        		count++;
        	}
        	if(win_nums_list.contains(lottos[i])) {
        		wins++;
        	}
        }
        
        if(wins>0) {
        	result -= wins;
        }else {
        	result -= 1;
        }
        
//        int[] answer = new int[2];
        int[] answer = {(count==6)?result-count+1:result-count,result};
//        answer[1] = result-count;
//        answer[2] = result;
        
        return answer;
    }
}
