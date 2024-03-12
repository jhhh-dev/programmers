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
	
	private static final String FILE_ADDRESS = "D:\\test.csv"; //���� ���� ���
	
	private static final Long FREE_TIME = 3L;
	private static final Long MINIMUM_TIME = 10L;
	private static final Long INTERVAL_TIME = 5L;
	private static final Long PER_FEE = 500L;
	private static final Long MAXIMUM_FEE = 36000L;
	

	public static void main(String[] args) throws ParseException {
		Codingtest codingtest = new Codingtest();
		DecimalFormat decFormat = new DecimalFormat("###,###��");
		List<List<String>> resultList = new ArrayList<List<String>>();
		
		// 1. csv �����б�
		List<List<String>> csvList = codingtest.readCSV();
		
		// 2. ��� ����ϱ�
		for(List<String> l : csvList) {
			// �����ð�-�����ð� ��� -> ��� ��� -> ���ڿ� ����
			String[] arr = {l.get(0), decFormat.format(codingtest.feeCalc(codingtest.timeCalc(l.get(1), l.get(2))))};
			resultList.add(Arrays.asList(arr));
		}
		
		// 3. �����ϱ�
        Collections.sort(resultList, new Comparator<List<String>>() {
            @Override
            public int compare(List<String> l1, List<String> l2) {
                // ù ��° ��Ҹ� �������� ����
                return l1.get(0).compareTo(l2.get(0));
            }
        });
        
        // 4. ����ϱ�
        for (List<String> l : resultList) {
        	System.out.println(l.get(0) + " " + l.get(1));
        }
		
	}

	
	// csv ���� �б�
	public List<List<String>> readCSV(){
		List<List<String>> csvList = new ArrayList<List<String>>();
		File csv = new File(FILE_ADDRESS);
		BufferedReader br = null;
		String line = "";
		
		try {
			// csv ���� �о ����Ʈ�� �ֱ�
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
			// �ڿ� ����
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
	
	// �����ð�-�����ð� ���
	public Long timeCalc(String arrivalTime, String leftTime) throws ParseException {
		// String date�� ����
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		Date arrivalDate = dateFormat.parse(arrivalTime);
		Date leftDate = dateFormat.parse(leftTime);
		
		// �����ð�-�����ð� ������ ���
		Long diff = leftDate.getTime() - arrivalDate.getTime();
		Long resultDiff = diff / (1000 * 60);
		
		return resultDiff;
	}
	
	// ��� ���
	public Long feeCalc(Long diffTime) {
		// 3������, 10������ ����
		if(FREE_TIME >= diffTime) return 0L;
		if(MINIMUM_TIME >= diffTime) return 1000L;
		
		// 5������ ������ ��� ����ϱ�
		Long div = diffTime/INTERVAL_TIME;
		Long rest = diffTime%INTERVAL_TIME;
		
		Long result = PER_FEE * div + (rest != 0 ? 500 : 0);
		
		return result > MAXIMUM_FEE ? MAXIMUM_FEE : result;
	}
}
