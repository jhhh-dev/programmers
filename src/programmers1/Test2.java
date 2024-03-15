package programmers1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test2 {
	//프로그래머스 기능개발
	//....풀긴푸는데 코드가 영...쩝...쳇...잘하는 사람 코드 보고 공부하세요 넵
	//나는 코테를 풀고 농구는 지고있음 2024.03.15
	public static void main(String[] args) {
		Test2 test2 = new Test2();
		int[] progresses = {93, 30, 55};
		int[] speeds = {1, 30, 5};
		
		test2.solution(progresses, speeds);
		
		
		
	}
	
    public int[] solution(int[] progresses, int[] speeds) {
        int[] tempAnswer = new int[progresses.length];
        List<Integer> answerList = new ArrayList<>();
        
        for(int i = 0; i < progresses.length; i++) {
        	while(progresses[i]<100) {
        		progresses[i] += speeds[i];
        		tempAnswer[i]++;
        	}
        }
        
        List<Integer> answerList2 = IntStream.of(tempAnswer).boxed().collect(Collectors.toList());
        
        for(int i=0;i<answerList2.size();i++) {
        	int cnt = 1;
        	while(true) {
        		if(answerList2.size()>i+1 && answerList2.get(i)>=answerList2.get(i+1)) {
        			answerList2.remove(i+1);
        			cnt++;
        		}else {
        			answerList.add(cnt);
        			break;
        		}
        	}
        }

        return answerList.stream().mapToInt(Integer::intValue).toArray();
    }
}
