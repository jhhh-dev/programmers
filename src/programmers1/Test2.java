package programmers1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test2 {
	//���α׷��ӽ� ��ɰ���
	//....Ǯ��Ǫ�µ� �ڵ尡 ��...��...��...���ϴ� ��� �ڵ� ���� �����ϼ��� ��
	//���� ���׸� Ǯ�� �󱸴� �������� 2024.03.15
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
