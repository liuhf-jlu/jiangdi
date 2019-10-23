package cn.edu.ccut.se;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Scanner;

public class Question {
	// num �ɳ�ϰ�������
	// is_include_Multiplication_division �Ƿ�����˳���
	// size_operationNum ��Χ��С
	// is_include_negative ���ڷ������
	public String[][] CreateQuestion(int num, boolean is_include_Multiplication_division, int size_operationNum,
			boolean is_include_negative) {
		int num1, num2, result;
		int type;
		int count = 0;
		String[][] Question_Answer = new String[99999][2];
		while (true) {
			if (count >= num)
				break;
			if (is_include_negative) {
				num1 = (int) ((Math.random() * size_operationNum) - 50);
				num2 = (int) ((Math.random() * size_operationNum) - 50);
			} else {
				num1 = (int) (Math.random() * size_operationNum);
				num2 = (int) (Math.random() * size_operationNum);
			}
			if (!is_include_negative)
				if (num1 < 0 || num2 < 0)
					continue;
			type = 1 + (int) (Math.random() * 4);
			if (type == 1) {
				result = num1 + num2;
				if (result < 100) {
					Question_Answer[count][0] = "("+num1+")" + " + " +"("+ num2+")" + " =";
					Question_Answer[count][1] = result+"";
					count++;
				}
			} else if (type == 2) {
				result = num1 - num2;
				if (result < 100) {
					Question_Answer[count][0] = "("+num1+")" + " - " +"("+ num2+")" + " =";
					Question_Answer[count][1] = result+"";
					count++;
				}
			} else if (type == 3 && is_include_Multiplication_division) {
				result = num1 * num2;
				if (result < 100) {
					Question_Answer[count][0] = "("+num1+")" + " �� " +"("+ num2+")" + " =";
					Question_Answer[count][1] = result+"";
					count++;
				}
			} else if (type == 4 && is_include_Multiplication_division) {
				Double result_;
				if (num2 == 0)
					continue;
				result_ = (double) num1 / num2;
				if (result_ < 100) {
					Question_Answer[count][0] = "("+num1+")" + " �� " +"("+ num2+")" + " =";
					Question_Answer[count][1] = String.format("%.2f", result_); // ���ȿ���
					count++;
				}
			}
		}
		return Question_Answer;
	}

	/*
	 * rows���������
	 */
	public void SaveToDisk(String[][] question_answer, int lines, int rows) {
		// ����������������
		OutputStream outputStream = null;
		// ��ȡʱ�䣬����������ʱ��仯���ļ���
		LocalDate date = LocalDate.now();
		// ����result.txt�ļ�
		try {
			outputStream = new FileOutputStream("./result" + date + ".txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// �����ʽ����д���ļ�
		byte[] header = "-*--*--*--*--*--*--*--*--*--*--*-  ��  ��  ��  ��  ��  -*--*--*--*--*--*--*--*--*--*--*--*--*--*--*-\r\n"
				.getBytes();
		try {
			outputStream.write(header);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// ��Ŀ���岿�ָ�ʽ����д���ļ�
		try {
			byte[] bs = null;
			String str = null;
			int count = 0;
			for (int i = 0; i < lines; i++) {
				str = "";
				for (int j = 0; j < rows; j++) {
					if (question_answer[count][0] == null) // �������ʽΪ�գ������������ʽβ������ƴ��
						break;
					str += "\t"+(count+1)+"��" + question_answer[count][0] + "____\t"; // ��һ����ʽƴ�ӣ�ָ��rows��
					count++;
				}
				str += "\r\n";
				bs = str.getBytes();
				outputStream.write(bs);
				if (count >= lines)
					break;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// β����ʽ����д���ļ�
		header = "\r\n\r\n-*--*--*--*--*--*--*--*--*--*--*-  ��  ��  ��  ��  -*--*--*--*--*--*--*--*--*--*--*--*--*--*-\r\n"
				.getBytes();
		try {
			outputStream.write(header);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// �𰸸�ʽ����д���ļ�
		try {
			String str_answer = "";
			int count_answer = 0;
			byte[] bs = null;
			for (int i = 0; i < lines; i++) {
				str_answer="";
				for (int j = 0; j < 5; j++) {
					if (question_answer[count_answer][1] == null)
						break;
					str_answer += "\t" +(count_answer+1)+ "��"+question_answer[count_answer][1] + "\t";
					count_answer++;
				}
				str_answer += "\r\n";
				bs = str_answer.getBytes();
				outputStream.write(bs);
				if (count_answer >= lines)
					break;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void SaveToDisk(String[][] question_answer, int lines) {
		OutputStream outputStream = null;
		LocalDate date = LocalDate.now();
		try {
			outputStream = new FileOutputStream("./result" + date + ".txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		byte[] header = "������\r\n".getBytes();
		try {
			outputStream.write(header);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int i = 0; i < lines; i++) {
			byte[] bs = (question_answer[i][0] + "\r\n").getBytes();

			try {
				outputStream.write(bs);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		header = "\r\n\r\n\r\n�ο���\r\n".getBytes();
		try {
			outputStream.write(header);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int i = 0; i < lines; i++) {
			byte[] bs = question_answer[i][1].getBytes();

			try {
				outputStream.write(bs);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		try {
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int Question_Size(String[][] question_answer) {
		int i = 0;
		while (question_answer[i][0] != null) {
			i++;
		}
		return i;
	}

	public static void main(String[] args) {
		Question q = new Question();
		int num = 10;
		int size_operationNum = 100;
		boolean is_include_Multiplication_division = false;
		boolean is_include_negative = false;
		Scanner input = new Scanner(System.in);

		int rows = 1;

		System.out.print("������Ҫ���ɵ�����������");
		num = input.nextInt();

		System.out.print("��������ֵ��Χ��");
		size_operationNum = input.nextInt();

		System.out.print("�Ƿ�����˳���");
		int flag = input.nextInt();
		if (flag == 1)
			is_include_Multiplication_division = true;
		else
			is_include_Multiplication_division = false;

		System.out.print("�Ƿ����������");
		flag = input.nextInt();
		if (flag == 1)
			is_include_negative = true;
		else
			is_include_negative = false;

		System.out.print("��ӡ������Ĭ��Ϊ1�У���");
		rows = input.nextInt();

		String[][] question_answer = q.CreateQuestion(num, is_include_Multiplication_division, size_operationNum,
				is_include_negative);
		int size = q.Question_Size(question_answer);
		q.SaveToDisk(question_answer, size, rows);
		System.out.print("��ҵ�����ɣ����Ŀ¼��./");

	}

}
