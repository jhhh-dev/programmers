package codingtest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Codingtest {
	
	private static final String FILE_ADDRESS = "D:\\test.csv"; //파일 절대 경로
	
	private static final Long FREE_TIME = 3L;
	private static final Long MINIMUM_TIME = 10L;
	private static final Long INTERVAL_TIME = 5L;
	private static final Long PER_FEE = 500L;
	private static final Long MAXIMUM_FEE = 36000L;
	

	public static void main(String[] args) throws ParseException {
		Codingtest codingtest = new Codingtest();
		DecimalFormat decFormat = new DecimalFormat("###,###원");
		List<List<String>> resultList = new ArrayList<List<String>>();
		
		// 1. csv 파일읽기
		List<List<String>> csvList = codingtest.readCSV();
		
		// 2. 요금 계산하기
		for(List<String> l : csvList) {
			// 출차시간-입차시간 계산 -> 요금 계산 -> 문자열 포맷
			String[] arr = {l.get(0), decFormat.format(codingtest.feeCalc(codingtest.timeCalc(l.get(1), l.get(2))))};
			resultList.add(Arrays.asList(arr));
		}
		
		// 3. 정렬하기
        Collections.sort(resultList, new Comparator<List<String>>() {
            @Override
            public int compare(List<String> l1, List<String> l2) {
                // 첫 번째 요소를 기준으로 정렬
                return l1.get(0).compareTo(l2.get(0));
            }
        });
        
        // 4. 출력하기
        for (List<String> l : resultList) {
        	System.out.println(l.get(0) + " " + l.get(1));
        }
		
	}

	
	// csv 파일 읽기
	public List<List<String>> readCSV(){
		List<List<String>> csvList = new ArrayList<List<String>>();
		File csv = new File(FILE_ADDRESS);
		BufferedReader br = null;
		String line = "";
		
		try {
			// csv 파일 읽어서 리스트에 넣기
			br = new BufferedReader(new FileReader(csv, Charset.forName("UTF-8")));
			while ((line = br.readLine()) != null) {
				List<String> aLine = new ArrayList<String>();
				String[] lineArr = line.split(",");
				aLine = Arrays.asList(lineArr);
				csvList.add(aLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원 해제
			try {
				if(br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return csvList;
	}
	
	// 출차시간-입차시간 계산
	public Long timeCalc(String arrivalTime, String leftTime) throws ParseException {
		// String date로 포맷
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		Date arrivalDate = dateFormat.parse(arrivalTime);
		Date leftDate = dateFormat.parse(leftTime);
		
		// 출차시간-입차시간 분으로 계산
		Long diff = leftDate.getTime() - arrivalDate.getTime();
		Long resultDiff = diff / (1000 * 60);
		
		return resultDiff;
	}
	
	// 요금 계산
	public Long feeCalc(Long diffTime) {
		// 3분이하, 10분이하 리턴
		if(FREE_TIME >= diffTime) return 0L;
		if(MINIMUM_TIME >= diffTime) return 1000L;
		
		// 5분으로 나눠서 요금 계산하기
		Long div = diffTime/INTERVAL_TIME;
		Long rest = diffTime%INTERVAL_TIME;
		
		Long result = PER_FEE * div + (rest != 0 ? 500 : 0);
		
		return result > MAXIMUM_FEE ? MAXIMUM_FEE : result;
	}
}
