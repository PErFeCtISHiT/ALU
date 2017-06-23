/**
 * ģ��ALU���������͸���������������
 * @author [��161250081_����ͮ��]
 *
 */
public class ALU {
	public static void main(String [] args){
		ALU x = new ALU();
		System.out.print(x.integerDivision("1010", "0011", 4));
	}
	/**
	 * ����ʮ���������Ķ����Ʋ����ʾ��<br/>
	 * ����integerRepresentation("9", 8)
	 * @param number ʮ������������Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ
	 * @param length �����Ʋ����ʾ�ĳ���
	 * @return number�Ķ����Ʋ����ʾ������Ϊlength
	 */
	public String integerRepresentation (String number, int length) {//�����
		int flag = 0;//����λ
		int integer = 0;//����
		int label = 0;
		String result = "";
		String ex_result = "";
		if(number.charAt(0) == '-'){
			flag = 1;
			number = number.substring(1);
		}
		for(int i = 0;i < number.length();i++){
			integer = (integer * 10) + ((number.charAt(i)) - 48);
		}
		if(flag == 1){
			integer = integer - 1;
		}//�����������ֵ��һ�����ȡ��
		while(integer != 0){
			label = integer & 1;
			integer = integer >> 1;
		result = Integer.toString(label) + result;
		}//���ɶ����Ʊ�ʾ
		if(flag == 1){
			for(int i = 0;i < result.length();i++){
				if(result.charAt(i) == '0'){
					ex_result = ex_result + "1";
				}
				else
					ex_result = ex_result + "0";
			}
		}//�ԣ�������һ��ȡ��,��һ��Ϊ������չ
		if(flag == 0){
		    while(result.length() < length){
			    result = "0" + result;
		    }
		    return result;
		}
		else{
			while(ex_result.length() < length){
				ex_result = "1" + ex_result;
			}
			return ex_result;
		}
	}
	
	/**
	 * ����ʮ���Ƹ������Ķ����Ʊ�ʾ��
	 * ��Ҫ���� 0������񻯡����������+Inf���͡�-Inf������ NaN�����أ������� IEEE 754��
	 * �������Ϊ��0���롣<br/>
	 * ����floatRepresentation("11.375", 8, 11)
	 * @param number ʮ���Ƹ�����������С���㡣��Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ
	 * @param eLength ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @return number�Ķ����Ʊ�ʾ������Ϊ 1+eLength+sLength���������ң�����Ϊ���š�ָ���������ʾ����β������λ���أ�
	 */
	public String floatRepresentation (String number, int eLength, int sLength) {//�����
		int flag = 0;//����
		int integer = 0;//��������
		String left = "";//����������
		String right = "";//С��������
		String result = "";//���
		int pointNum = 0;//����
		String moveStr = "";//�����ַ���
		String endStr = "";//β���ַ���
		int label = 0;//�м����
		if(number.equals("+Inf")){
			result = "0";
			for(int i = 0;i < eLength;i++){
				result = result + "1";
			}
			for(int i = 0;i < sLength;i++){
				result = result + "0";
			}
			return result;
		}//������
		else if(number.equals("-Inf")){
			result = "1";
			for(int i = 0;i < eLength;i++){
				result = result + "1";
			}
			for(int i = 0;i < sLength;i++){
				result = result + "0";
			}
			return result;
		}//������
		else if(number.equals("NaN")){
			result = "1";
			for(int i = 0;i < eLength;i++){
				result = result + "1";
			}
			for(int i = 0;i < sLength;i++){
				result = result + "1";
			}
			return result;
		}
		else{
		if(number.charAt(0) == '-'){
			flag = 1;
			number = number.substring(1);
		}
		String store[] = number.split("\\.");
		for(int i = 0;i < store[0].length();i++){
			integer = (integer * 10) + ((number.charAt(i)) - 48);
		}
		int integersave = integer;
		while(integer != 0){
			label = integer & 1;
			integer = integer >> 1;
		left = Integer.toString(label) + left;
		}//�õ��������ֵĶ����Ʊ�ʾ
		float tempStore = 0;
		if(store.length > 1){
		float tempRight = Float.valueOf(store[1]);
		while(tempRight > 1){
			tempRight = tempRight / 10;
		}
		for(int i = 0;store[1].charAt(i) == '0' && i < store[1].length() - 1;i++){
			tempRight = tempRight / 10;
		}

		tempStore = tempRight;
		while(tempRight != 0){
			if((tempRight * 2) >= 1){
				tempRight = tempRight * 2 - 1;
				right = right + "1";
			}
			else{
				tempRight = tempRight * 2;
				right = right + "0";
			}
		}
		}else{
			float tempRight = 0;

			tempStore = tempRight;
			while(tempRight != 0){
				if((tempRight * 2) >= 1){
					tempRight = tempRight * 2 - 1;
					right = right + "1";
				}
				else{
					tempRight = tempRight * 2;
					right = right + "0";
				}
			}
		}
		//�õ�С�����ֵĶ����Ʊ�ʾ
		if(flag == 0){
			result = "0";
		}
		else
			result = "1";//�õ�����λ
		integer = integersave;
		if((integer == 0 && tempStore == 0 )||( integer == 0 && tempStore < Math.pow(2, 2 - Math.pow(2, eLength - 1) - sLength))){
			for(int i = 0;i < eLength;i++){
				result = result + "0";
			}
			for(int i = 0;i < sLength;i++){
				result = result + "0";
			}
		}//0�;���ֵ����0����
		else if(integer == 0 && tempStore >= Math.pow(2, 2 - Math.pow(2, eLength - 1))){
			if(right.length() >= (Math.pow(2, eLength - 1) - 2 + sLength)){
				right = right.substring(0,(int)(Math.pow(2,eLength) + sLength));
			}
			for(int i = 0;right.charAt(i) == '0';i++){
				pointNum--;
			}
			pointNum--;
			int j = 1;
			for(int i = 0;i < eLength - 1;i++){
				j = j << 1;
			}
			pointNum = pointNum + j;
			pointNum--;
			while(pointNum != 0){
				label = pointNum & 1;
				pointNum = pointNum >> 1;
			moveStr = Integer.toString(label) + moveStr;
			}//�õ�����
			while(moveStr.length() != eLength)
			    moveStr = "0" + moveStr;
			endStr = right;
			int temp = 0;
			for(int i = 0;endStr.charAt(i) != '1';i++){
				temp = i;
			}
			temp++;
			if(endStr.charAt(0) == '1')
			temp = 0;
			if(endStr.length() < sLength){
			    while(endStr.length() < sLength){
				  endStr = 	endStr + "0";
			    }
		    }
			endStr = endStr.substring(temp + 1);
			if(endStr.length() < sLength){
			    while(endStr.length() < sLength){
				  endStr = 	endStr + "0";
			    }
		    }
			else{
				endStr = endStr.substring(0,sLength);
			}
			result = result + moveStr + endStr;
		}//���С��1����
		else if(integer == 0 && tempStore >= Math.pow(2, 2 - Math.pow(2, eLength - 1) - sLength) && tempStore < Math.pow(2, 2 - Math.pow(2, eLength - 1))){
			if(right.length() >= (Math.pow(2, eLength - 1) - 2 + sLength)){
				right = right.substring(0,(int)(Math.pow(2,eLength) + sLength));
			}
			for(int i = 0;i < eLength;i++){
				result = result + "0";
			}

			int temp = (int)Math.pow(2, eLength - 1) - 2;
			right = right.substring(temp);
			if(right.length() < sLength){
			    while(right.length() < sLength){
				  right = right + "0";
			    }
		    }
			result = result + right;
		}//�������
		else if(integer != 0){
			pointNum = left.length() - 1;
			int j = 1;
			for(int i = 0;i < eLength - 1;i++){
				j = j << 1;
			}
			pointNum = pointNum + j;
			pointNum--;
			while(pointNum != 0){
				label = pointNum & 1;
				pointNum = pointNum >> 1;
			moveStr = Integer.toString(label) + moveStr;
			}//�õ�����
			while(moveStr.length() != eLength)
			    moveStr = "0" + moveStr;
			right = (left + right).substring(1);
			if(right.length() >= (Math.pow(2, eLength - 1) - 2 + sLength)){
				right = right.substring(0,(int)(Math.pow(2,eLength) + sLength));
			}
			endStr = right;
			if(endStr.length() < sLength){
			    while(endStr.length() < sLength){
				  endStr = 	endStr + "0";
			    }
		    }
			else{
				endStr = endStr.substring(0,sLength);
			}
			result = result + moveStr + endStr;
		}//��񻯴���1����
		return result;
		}
	}

	/**
	 * ����ʮ���Ƹ�������IEEE 754��ʾ��Ҫ�����{@link #floatRepresentation(String, int, int) floatRepresentation}ʵ�֡�<br/>
	 * ����ieee754("11.375", 32)
	 * @param number ʮ���Ƹ�����������С���㡣��Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ
	 * @param length �����Ʊ�ʾ�ĳ��ȣ�Ϊ32��64
	 * @return number��IEEE 754��ʾ������Ϊlength���������ң�����Ϊ���š�ָ���������ʾ����β������λ���أ�
	 */
	public String ieee754 (String number, int length) {//�����
		String result;
		if(length == 32){
			result = floatRepresentation(number,8,23);
		}
		else{
			result = floatRepresentation(number,11,52);
		}
		return result;
	}

	/**
	 * ��������Ʋ����ʾ����������ֵ��<br/>
	 * ����integerTrueValue("00001001")
	 * @param operand �����Ʋ����ʾ�Ĳ�����
	 * @return operand����ֵ����Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ
	 */
	public String integerTrueValue (String operand) {//�����
		int postNum = 1;
		int result = 0;
		if(operand.charAt(0) == '0'){
			int i = operand.length() - 1;
			while(i != 0){
				if(operand.charAt(i) =='1')
					result = result + postNum;
				postNum = postNum << 1;
				i--;
			}
		}
		else{
			int i = operand.length() - 1;
			while(i != 0){
				if(operand.charAt(i) =='1')
					result = result + postNum;
				postNum = postNum << 1;
				i--;
			}
			result = result - postNum;
		}
		String resultStr = String.valueOf(result);
		return resultStr;
	}

	/**
	 * ���������ԭ���ʾ�ĸ���������ֵ��<br/>
	 * ����floatTrueValue("01000001001101100000", 8, 11)
	 * @param operand �����Ʊ�ʾ�Ĳ�����
	 * @param eLength ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @return operand����ֵ����Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ����������ֱ��ʾΪ��+Inf���͡�-Inf���� NaN��ʾΪ��NaN��
	 */
	public String floatTrueValue (String operand, int eLength, int sLength) {//�����
		String result = "";
		int moveFlag = 1;
		int endFlag = 1;//��������������
		int zeroFlag = 1;//��������0
		int moveNum = 0;//ָ��
		String rightStr = "";
		int i = 1;
		for(int j = 1;j <= eLength;j++){
			if(operand.charAt(j) == '1'){
				zeroFlag = 0;
				break;
			}
		}
		for(;i <= eLength;i++){
			if(operand.charAt(i) == '0'){
				moveFlag = 0;
				break;
			}
		}
		for(;i <= sLength + eLength;i++){
			if(operand.charAt(i) == '1'){
				endFlag = 0;
				break;
			}
		}
		if (moveFlag == 1 && endFlag == 1){
			if(operand.charAt(0) == '0'){
				result = "+Inf";
			}
			else
				result = "-Inf";
		}//����
		else if(moveFlag == 1 && endFlag == 0){
			result = "NaN";
		}//NaN
		else if(zeroFlag == 1 && endFlag == 1){
			result = "0.0";
		}//0
		else if(zeroFlag == 1 && endFlag == 0){
			if(operand.charAt(0) == '1')
			result = "-";
		operand = operand.substring(1);
		moveNum =  (int)Math.pow(2, eLength - 1) - 2;
		operand = operand.substring(eLength);
		for(;moveNum > 0;moveNum--){
			operand = "0" + operand;
		}
		double temp = 0.5;
		double rightNum = 0;
		for(int j = 0;j < operand.length();j++){
			if(operand.charAt(j) == '1'){
				rightNum = rightNum + temp;
			}
			temp = temp / 2;
		}
		rightStr = String.valueOf(rightNum);
		result = result + rightStr;
		}//�������
		else{
			if(operand.charAt(0) == '1')
			result = "-";
		operand = operand.substring(1);
		int temp = 1;
		int vmoveNum = 0;
		for(int j = eLength - 1;j >= 0;j--){
			if(operand.charAt(j) == '1'){
				vmoveNum = vmoveNum + temp;
			}
			temp = temp << 1;
		}
		moveNum = vmoveNum - (int)Math.pow(2, eLength - 1) + 1;
		operand = operand.substring(eLength);
		if(moveNum < 0){
			operand = "1" + operand;
			moveNum++;
			while(moveNum < 0){
				operand = "0" + operand;
				moveNum++;
			}
			double e_temp = 0.5;
			double rightNum = 0;
			for(int j = 0;j < operand.length();j++){
				if(operand.charAt(j) == '1'){
					rightNum = rightNum + e_temp;
				}
				e_temp = e_temp / 2;
			}
			rightStr = String.valueOf(rightNum);
			result = result + rightStr;
		}
		else if(moveNum == 0){
			double e_temp = 0.5;
			double rightNum = 0;
			for(int j = 0;j < operand.length();j++){
				if(operand.charAt(j) == '1'){
					rightNum = rightNum + e_temp;
				}
				e_temp = e_temp / 2;
			}
			rightNum += 1;
			rightStr = String.valueOf(rightNum);
			result = result + rightStr;
		}
		else{
			String left = "1" + operand.substring(0,moveNum);
			operand = operand.substring(moveNum);
			double e_temp = 0.5;
			double rightNum = 0;
			for(int j = 0;j < operand.length();j++){
				if(operand.charAt(j) == '1'){
					rightNum = rightNum + e_temp;
				}
				e_temp = e_temp / 2;
			}
			int leftNum = 0;
			int x_temp = 1;
			for(int j = left.length() - 1;j >= 0;j--){
				if(left.charAt(j) == '1')
				leftNum = leftNum + x_temp;
				x_temp = x_temp << 1;
			}
			double FullNum = leftNum + rightNum;
			result = result + String.valueOf(FullNum);
		}
		}//�����
		return result;
	}

	/**
	 * ��λȡ��������<br/>
	 * ����negation("00001001")
	 * @param operand �����Ʊ�ʾ�Ĳ�����
	 * @return operand��λȡ���Ľ��
	 */
	public String negation (String operand) {//�����
		String result = "";
		for(int i = 0;i < operand.length();i++){
			if(operand.charAt(i) == '0'){
				result = result + "1";
			}
			else
				result = result + "0";
		}
		return result;
	}

	/**
	 * ���Ʋ�����<br/>
	 * ����leftShift("00001001", 2)
	 * @param operand �����Ʊ�ʾ�Ĳ�����
	 * @param n ���Ƶ�λ��
	 * @return operand����nλ�Ľ��
	 */
	public String leftShift (String operand, int n) {//�����
		if(n > operand.length()){
			String temp = "";
			for(int i = 0;i < operand.length();i++)
				temp = temp + "0";
			return temp;
		}
		String result = operand.substring(n);
		while(n != 0){
			result = result + "0";
			n--;
		}
		return result;
	}

	/**
	 * �߼����Ʋ�����<br/>
	 * ����logRightShift("11110110", 2)
	 * @param operand �����Ʊ�ʾ�Ĳ�����
	 * @param n ���Ƶ�λ��
	 * @return operand�߼�����nλ�Ľ��
	 */
	public String logRightShift (String operand, int n) {//�����
		String result = "";
		int store = n;
		if(n >= operand.length()){
			for(int i = 0;i < operand.length();i++)
				result = result + "0";
			return result;
		}
		while(n != 0){
			result = result + "0";
			n--;
		}
		result = result + operand.substring(0,operand.length() - store);
		return result;
	}

	/**
	 * �������Ʋ�����<br/>
	 * ����ariRightShift("11110110", 2)
	 * @param operand �����Ʊ�ʾ�Ĳ�����
	 * @param n ���Ƶ�λ��
	 * @return operand��������nλ�Ľ��
	 */
	public String ariRightShift (String operand, int n) {//�����
		String result = "";
		int store = n;
		if(operand.charAt(0) == '0'){
			if(n >= operand.length()){
				for(int i = 0;i < operand.length();i++)
					result = result + "0";
				return result;
			}
			while(n != 0){
				result = result + "0";
				n--;
			}
			result = result + operand.substring(0,operand.length() - store);
		}
		else{
			if(n >= operand.length()){
				for(int i = 0;i < operand.length();i++)
					result = result + "1";
				return result;
			}
			while(n != 0){
				result = result + "1";
				n--;
			}
			result = result + operand.substring(0,operand.length() - store);
		}
		return result;
	}

	/**
	 * ȫ����������λ�Լ���λ���мӷ����㡣<br/>
	 * ����fullAdder('1', '1', '0')
	 * @param x ��������ĳһλ��ȡ0��1
	 * @param y ������ĳһλ��ȡ0��1
	 * @param c ��λ�Ե�ǰλ�Ľ�λ��ȡ0��1
	 * @return ��ӵĽ�����ó���Ϊ2���ַ�����ʾ����1λ��ʾ��λ����2λ��ʾ��
	 */
	public String fullAdder (char x, char y, char c) {//�����
		String result = null;
		int numFlag = 0;
		int Cout = 0;
		Cout = (x & y) | (x & c) | (y & c);
		numFlag = x ^ y ^ c;
		char add = (char)numFlag;
		char out = (char)Cout;
		result = String.valueOf(out) + String.valueOf(add);
		return result;
	}

	/**
	 * 4λ���н�λ�ӷ�����Ҫ�����{@link #fullAdder(char, char, char) fullAdder}��ʵ��<br/>
	 * ����claAdder("1001", "0001", '1')
	 * @param operand1 4λ�����Ʊ�ʾ�ı�����
	 * @param operand2 4λ�����Ʊ�ʾ�ļ���
	 * @param c ��λ�Ե�ǰλ�Ľ�λ��ȡ0��1
	 * @return ����Ϊ5���ַ�����ʾ�ļ����������е�1λ�����λ��λ����4λ����ӽ�������н�λ��������ѭ�����
	 */
	public String claAdder (String operand1, String operand2, char c) {//�����
		char G[] = new char[4];
		char P[] = new char[4];
		char C[] = new char[5];
		String result = "";
		for(int i = 0;i < 4;i++){
			G[i] = (char)(operand1.charAt(3-i) & operand2.charAt(3-i));
			P[i] = (char)(operand1.charAt(3-i) | operand2.charAt(3-i));
		}
		C[0] = c;
		C[1] = (char)(G[0] | (P[0] & C[0]));
		C[2] = (char)(G[1] | P[1] & G[0] | P[1] & P[0] & C[0]);
		C[3] = (char)(G[2] | P[2] & G[1] | P[2] & P[1] & G[0] | P[2] & P[1] & P[0] & C[0]);
		C[4] = (char)(G[3] | P[3] & G[2] | P[3] & P[2] & G[1] | P[3] & P[2] & P[1] & G[0] | P[3] & P[2] & P[1] & P[0] & C[0]);
		for(int i = 3;i >= 0;i--){
			result = fullAdder(operand1.charAt(i),operand2.charAt(i),C[3-i]).charAt(1) + result;
		}
		result = C[4] + result;
		return result;
	}

	/**
	 * ��һ����ʵ�ֲ�������1�����㡣
	 * ��Ҫ�������š����š�����ŵ�ģ�⣬
	 * ������ֱ�ӵ���{@link #fullAdder(char, char, char) fullAdder}��
	 * {@link #claAdder(String, String, char) claAdder}��
	 * {@link #adder(String, String, char, int) adder}��
	 * {@link #integerAddition(String, String, int) integerAddition}������<br/>
	 * ����oneAdder("00001001")
	 * @param operand �����Ʋ����ʾ�Ĳ�����
	 * @return operand��1�Ľ��������Ϊoperand�ĳ��ȼ�1�����е�1λָʾ�Ƿ���������Ϊ1������Ϊ0��������λΪ��ӽ��
	 */
	public String oneAdder (String operand) {//�����
		char C[] = new char[operand.length() + 1];
		String result = "";
		C[0] = '1';//Cin,��ͽ�λ
		for(int i = operand.length() - 1;i >= 0;i--){
			result = (operand.charAt(i) ^ C[operand.length() - i - 1]) + result;
			C[operand.length() - i] = (char)(operand.charAt(i) & C[operand.length() - i - 1]);
		}
		if(result.charAt(0) == '1' && operand.charAt(0) == '0')
		    result = "1" + result;
		else
			result = "0" + result;
		return result;
	}

	/**
	 * �ӷ�����Ҫ�����{@link #claAdder(String, String, char)}����ʵ�֡�<br/>
	 * ����adder("0100", "0011", '0', 8)
	 * @param operand1 �����Ʋ����ʾ�ı�����
	 * @param operand2 �����Ʋ����ʾ�ļ���
	 * @param c ���λ��λ
	 * @param length ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ����Ҫ�ڸ�λ������λ
	 * @return ����Ϊlength+1���ַ�����ʾ�ļ����������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������lengthλ����ӽ��
	 */
	public String adder (String operand1, String operand2, char c, int length) {//�����
		int High_Flag1 = 0;
		int High_Flag2 = 0;
		int time = length >> 2 ;
		String result = "";
		char C[] = new char[time + 1];
		C[0] = c;
		String str1[] = new String[time];
		String str2[] = new String[time];
		operand1 = ALU.turn(operand1, length);
		operand2 = ALU.turn(operand2, length);
		for(int i = 0;i < time;i++){
			str1[i] = operand1.substring((time - i - 1)*4,(time - i)*4);
			str2[i] = operand2.substring((time - i - 1)*4,(time - i)*4);
		}
		for(int i = 0;i < time;i++){
			result = claAdder(str1[i],str2[i],C[i]).substring(1) + result;
			C[i + 1] = claAdder(str1[i],str2[i],C[i]).charAt(0);
		}
		High_Flag1 = operand1.charAt(0);
		High_Flag2 = operand2.charAt(0);
		if((High_Flag1 ^ High_Flag2) == 0 && (High_Flag1 ^ result.charAt(0)) == 1){
		    result = "1" + result;
    	}
		else{
			result = "0" + result;
		}
		return result;
	}

	/**
	 * �����ӷ���Ҫ�����{@link #adder(String, String, char, int) adder}����ʵ�֡�<br/>
	 * ����integerAddition("0100", "0011", 8)
	 * @param operand1 �����Ʋ����ʾ�ı�����
	 * @param operand2 �����Ʋ����ʾ�ļ���
	 * @param length ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ����Ҫ�ڸ�λ������λ
	 * @return ����Ϊlength+1���ַ�����ʾ�ļ����������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������lengthλ����ӽ��
	 */
	public String integerAddition (String operand1, String operand2, int length) {//�����
		String result = "";
		operand1 = ALU.turn(operand1, length);
		operand2 = ALU.turn(operand2, length);
		result = adder(operand1,operand2,'0',length);
		return result;
	}


	/**
	 * �����������ɵ���{@link #adder(String, String, char, int) adder}����ʵ�֡�<br/>
	 * ����integerSubtraction("0100", "0011", 8)
	 * @param operand1 �����Ʋ����ʾ�ı�����
	 * @param operand2 �����Ʋ����ʾ�ļ���
	 * @param length ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ����Ҫ�ڸ�λ������λ
	 * @return ����Ϊlength+1���ַ�����ʾ�ļ����������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������lengthλ��������
	 */
	public String integerSubtraction (String operand1, String operand2, int length) {//�����
		String ChangedOperand2 = oneAdder(negation(operand2)).substring(1);
		String result = integerAddition(operand1,ChangedOperand2,length);
		return result;
	}


	/**
	 * �����˷���ʹ��Booth�㷨ʵ�֣��ɵ���{@link #adder(String, String, char, int) adder}�ȷ�����<br/>
	 * ����integerMultiplication("0100", "0011", 8)
	 * @param operand1 �����Ʋ����ʾ�ı�����
	 * @param operand2 �����Ʋ����ʾ�ĳ���
	 * @param length ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ����Ҫ�ڸ�λ������λ
	 * @return ����Ϊlength+1���ַ�����ʾ����˽�������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������lengthλ����˽��
	 */
	public String integerMultiplication (String operand1, String operand2, int length) {//�����
		String result = "";
		char overflag = '0';
		operand1 = ALU.turn(operand1, length);
		operand2 = ALU.turn(operand2, length);
		String anti_operand1 = oneAdder(negation(operand1)).substring(1);
		String p = "0";
		p = ALU.turn(p, length);
		char boothBit = '0';
		for(int i = 0;i < length;i++){
		    if((boothBit ^ (operand2.charAt(length - 1))) == 0){
		    	boothBit = operand2.charAt(length - 1);
		    	operand2 = ariRightShift(operand2,1);
		    	operand2 = p.charAt(length - 1) + operand2.substring(1);
		    	p = ariRightShift(p,1);
		    }
		    else if(boothBit == '0'){
		    	p = adder(p,anti_operand1,'0',length).substring(1);
		    	boothBit = operand2.charAt(length - 1);
		    	operand2 = ariRightShift(operand2,1);
		    	operand2 = p.charAt(length - 1) + operand2.substring(1);
		    	p = ariRightShift(p,1);
		    }
		    else{
		    	p = adder(p,operand1,'0',length).substring(1);
		    	boothBit = operand2.charAt(length - 1);
		    	operand2 = ariRightShift(operand2,1);
		    	operand2 = p.charAt(length - 1) + operand2.substring(1);
		    	p = ariRightShift(p,1);
		    }
		}
		result = operand2;
		if(operand2.charAt(0) == '0'){
		for(int i = 0;i < length;i++){
			if(p.charAt(i) != '0'){
				overflag = '1';
				break;
			}
		}
		}
		else{
			for(int i = 0;i < length;i++){
				if(p.charAt(i) != '1'){
					overflag = '1';
					break;
				}
			}
			}
		result = overflag + result;
		return result;
	}


	/**
	 * �����Ĳ��ָ������������ɵ���{@link #adder(String, String, char, int) adder}�ȷ���ʵ�֡�<br/>
	 * ����integerDivision("0100", "0011", 8)
	 * @param operand1 �����Ʋ����ʾ�ı�����
	 * @param operand2 �����Ʋ����ʾ�ĳ���
	 * @param length ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ����Ҫ�ڸ�λ������λ
	 * @return ����Ϊ2*length+1���ַ�����ʾ�������������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0�������lengthλΪ�̣����lengthλΪ����
	 */
	public String integerDivision (String operand1, String operand2, int length) {//�����
		int divisor = Integer.valueOf(integerTrueValue(operand2));
		String OF = "0";
		String result = "";
		String mininum = "1";
		String opone = "1";
		int flag = 0;
		for(int i = 0;i < operand1.length() - 1;i++){
			mininum = mininum + "0";
			opone = opone + "1";
		}
		if(operand1.charAt(0) == '1')
			flag = 1;
		if(operand1.equals(mininum) && operand2.equals(opone))
			OF = "1";
		if(divisor == 0)//��0
			return "NaN";
		operand1 = ALU.turn(operand1, 2 * length);
		operand2 = ALU.turn(operand2, length);
		if(divisor == 1){//��1
			String R = "";
			for(int i = 0;i < length;i++)
				R = R + "0";
			result = OF + operand1.substring(length) + R;
			return result;
		}
		else if(divisor == -1){//��-1
			String R = "";
			for(int i = 0;i < length;i++)
				R = R + "0";
			String antiOperand1 = oneAdder(negation(operand1.substring(length))).substring(1);
			result = OF + antiOperand1 + R;
			return result;
		}
		String antiOperand2 = oneAdder(negation(operand2)).substring(1);
		String storeR = operand1.substring(0,length);
		String storeQ = operand1.substring(length);
		if(storeR.charAt(0) == operand2.charAt(0))
		storeR = adder(storeR,antiOperand2,'0',length).substring(1);
		else
			storeR = adder(storeR,operand2,'0',length).substring(1);
		for(int i = 0;i < length;i++){
			if(storeR.charAt(0) == operand2.charAt(0)){
				storeR = leftShift(storeR,1);
				storeR = storeR.substring(0,length - 1) + storeQ.charAt(0);
				storeQ = leftShift(storeQ,1);
				storeQ = storeQ.substring(0,length - 1) + "1";
				storeR = adder(storeR,antiOperand2,'0',length).substring(1);
			}
			else{
				storeR = leftShift(storeR,1);
				storeR = storeR.substring(0,length - 1) + storeQ.charAt(0);
				storeQ = leftShift(storeQ,1);
				storeQ = storeQ.substring(0,length - 1) + "0";
				storeR = adder(storeR,operand2,'0',length).substring(1);
			}
		}
		storeQ = leftShift(storeQ,1);
		if(storeR.charAt(0) == operand2.charAt(0))
			storeQ = storeQ.substring(0,length - 1) + "1";
		else
			storeQ = storeQ.substring(0,length - 1) + "0";
		if(operand1.charAt(0) != operand2.charAt(0))
			storeQ = oneAdder(storeQ).substring(1);
		if(storeR.charAt(0) != operand1.charAt(0)){
			if(operand1.charAt(0) == operand2.charAt(0))
				storeR = adder(storeR,operand2,'0',length).substring(1);
			else
				storeR = adder(storeR,antiOperand2,'0',length).substring(1);
		}
		if(flag == 1){//���Ǹ�������
			String temp = oneAdder(negation(operand2)).substring(1);
			if(storeR.equals(temp)){
				storeQ = integerAddition(storeQ,"1",length).substring(1);
				result = OF + storeQ;
				for(int i = 0;i < length;i++)
					result = result + "0";
				return result;
			}
			else if(storeR.equals(operand2)){
				result = OF + storeQ.substring(1) ;
				for(int i = 0;i < length;i++)
					result = result + "0";
				return result;
			}
		}
		result = OF + storeQ + storeR;
		return result;
	}


	/**
	 * �����������ӷ������Ե���{@link #adder(String, String, char, int) adder}�ȷ�����
	 * ������ֱ�ӽ�������ת��Ϊ�����ʹ��{@link #integerAddition(String, String, int) integerAddition}��
	 * {@link #integerSubtraction(String, String, int) integerSubtraction}��ʵ�֡�<br/>
	 * ����signedAddition("1100", "1011", 8)
	 * @param operand1 ������ԭ���ʾ�ı����������е�1λΪ����λ
	 * @param operand2 ������ԭ���ʾ�ļ��������е�1λΪ����λ
	 * @param length ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ����������ţ�����ĳ���������ĳ���С��lengthʱ����Ҫ���䳤����չ��length
	 * @return ����Ϊlength+2���ַ�����ʾ�ļ����������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������2λΪ����λ����lengthλ����ӽ��
	 */
	public String signedAddition (String operand1, String operand2, int length) {//�����
		int flag = 0;
		String addStr = "";
		String store = operand1;
		if((operand1.charAt(0) ^ operand2.charAt(0)) != 0)
			flag = 1;//ͬ��Ϊ0���Ϊ1
		operand1 = operand1.substring(1);
		operand2 = operand2.substring(1);
		operand1 = '0' + operand1;
		operand2 = '0' + operand2;
		operand1 = ALU.turn(operand1, length);
		operand2 = ALU.turn(operand2, length);
		if(flag == 0){
			addStr = adder(operand1,operand2,'0',length * 2);
			addStr = addStr.substring(length);
			addStr = addStr.substring(0,1) + store.charAt(0) + addStr.substring(1);
			return addStr;
		}
		else{
			operand2 = negation(operand2);
			addStr = adder(operand1,operand2,'1',length);
			addStr = addStr.substring(1);
			if(addStr.charAt(0) == '0'){
				addStr = "0" + store.charAt(0) + addStr;
				return addStr;
			}
			else{
				String storeStr = String.valueOf(store.charAt(0));
				addStr = oneAdder(negation(addStr)).substring(1);
				addStr = "0" + negation(storeStr) + addStr;
				return addStr;
			}
		}
	}


	/**
	 * �������ӷ����ɵ���{@link #signedAddition(String, String, int) signedAddition}�ȷ���ʵ�֡�<br/>
	 * ����floatAddition("00111111010100000", "00111111001000000", 8, 8, 8)
	 * @param operand1 �����Ʊ�ʾ�ı�����
	 * @param operand2 �����Ʊ�ʾ�ļ���
	 * @param eLength ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param gLength ����λ�ĳ���
	 * @return ����Ϊ2+eLength+sLength���ַ�����ʾ����ӽ�������е�1λָʾ�Ƿ�ָ�����磨���Ϊ1������Ϊ0��������λ����������Ϊ���š�ָ���������ʾ����β������λ���أ����������Ϊ��0����
	 */
	public String floatAddition (String operand1, String operand2, int eLength, int sLength, int gLength) {
		String exponStr1 = "0" + operand1.substring(1,eLength + 1);
		String exponStr2 = "0" + operand2.substring(1,eLength + 1);//����
		String endStr1 = operand1.substring(eLength + 1);
		String endStr2 = operand2.substring(eLength + 1);//β��
		String resultSign = "";
		String resultEndStr = "";
		String resultExpon = "";
		String result = "";
		int expon1 = Integer.valueOf(integerTrueValue(exponStr1));
		int expon2 = Integer.valueOf(integerTrueValue(exponStr2));
		int largeExpon = Math.max(expon1, expon2);
		if(expon1 < expon2){
			if(expon1 == 0)
			endStr1 = keepRightShift(endStr1,expon2 - expon1,gLength);
			else{
				endStr1 = "1" + endStr1;
				endStr1 = keepRightShift(endStr1,expon2 - expon1 - 1,gLength);
				endStr1 = endStr1.substring(0,endStr1.length() - 1);
			}
			endStr1 = "0" + endStr1;
			endStr2 = "1" + endStr2;
			while(endStr2.length() < endStr1.length())
				endStr2 = endStr2 + "0";
		}
		else if(expon1 > expon2){
			if(expon2 == 0)
			endStr2 = keepRightShift(endStr2,expon1 - expon2,gLength);
			else{
				endStr2 = "1" + endStr2;
				endStr2 = keepRightShift(endStr2,expon1 - expon2 - 1,gLength);
				endStr2 = endStr2.substring(0,endStr2.length() - 1);
			}
			endStr2 = "0" + endStr2;
			endStr1 = "1" + endStr1;
			while(endStr1.length() < endStr2.length())
				endStr1 = endStr1 + "0";
		}
		else{
			if(expon2 == 0){
			endStr2 = keepRightShift(endStr2,expon1 - expon2,gLength);
			while(endStr1.length() < endStr2.length())
				endStr1 = endStr1 + "0";
			endStr2 = "0" + endStr2;
			endStr1 = "0" + endStr1;
			}
			else{
				endStr2 = keepRightShift(endStr2, 0,gLength);
				endStr2 = "1" + endStr2;
				endStr1 = keepRightShift(endStr1, 0,gLength);
				endStr1 = "1" + endStr1;
			}
		}
		endStr1 = operand1.charAt(0) + endStr1;
		endStr2 = operand2.charAt(0) + endStr2;
		int temp = (endStr1.length() + 3) / 4;
		resultSign = signedAddition(endStr1,endStr2,temp * 4).substring(1,2);
		resultEndStr = signedAddition(endStr1,endStr2,temp * 4).substring(signedAddition(endStr1,endStr2,temp * 4).length() - endStr1.length());
		if(resultEndStr.charAt(0) == '1'){
			largeExpon++;
			if(largeExpon >= Math.pow(2, eLength) - 1){
				result = "1";
				while(result.length() < 2 + eLength + sLength)
					result = result + "0";
				return result;
			}
			else{
				resultEndStr = resultEndStr.substring(1,1 + sLength);
				resultExpon = unsignedRepresentation(largeExpon,eLength);
				result = "0" + resultSign + resultExpon + resultEndStr;
				return result;
			}
		}
		resultEndStr = resultEndStr.substring(1);
		if(resultEndStr.charAt(0) == '1'){
			if(largeExpon >= Math.pow(2, eLength) - 1){
				result = "1";
				while(result.length() < 2 + eLength + sLength)
					result = result + "0";
				return result;
			}
		}
		else{
			while(resultEndStr.charAt(0) != '1' && largeExpon > 0){
				largeExpon--;
				resultEndStr = leftShift(resultEndStr,1);
			}
		}
		if(largeExpon > 0){
			resultEndStr = resultEndStr.substring(1,1 + sLength);
			resultExpon = unsignedRepresentation(largeExpon,eLength);
			result = "0" + resultSign + resultExpon + resultEndStr;
		}
		else{
			if(resultEndStr.charAt(0) == '0'){
				resultEndStr = resultEndStr.substring(1,1 + sLength);
				resultExpon = unsignedRepresentation(largeExpon,eLength);
				result = "0" + resultSign + resultExpon + resultEndStr;
			}
			else{
				largeExpon = 1;
				resultEndStr = resultEndStr.substring(1,1 + sLength);
				resultExpon = unsignedRepresentation(largeExpon,eLength);
				result = "0" + resultSign + resultExpon + resultEndStr;
			}
		}
		return result;
	}


	/**
	 * �������������ɵ���{@link #floatAddition(String, String, int, int, int) floatAddition}����ʵ�֡�<br/>
	 * ����floatSubtraction("00111111010100000", "00111111001000000", 8, 8, 8)
	 * @param operand1 �����Ʊ�ʾ�ı�����
	 * @param operand2 �����Ʊ�ʾ�ļ���
	 * @param eLength ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param gLength ����λ�ĳ���
	 * @return ����Ϊ2+eLength+sLength���ַ�����ʾ�������������е�1λָʾ�Ƿ�ָ�����磨���Ϊ1������Ϊ0��������λ����������Ϊ���š�ָ���������ʾ����β������λ���أ����������Ϊ��0����
	 */
	public String floatSubtraction (String operand1, String operand2, int eLength, int sLength, int gLength) {
		if(operand2.charAt(0) == '1')
			operand2 = "0" + operand2.substring(1);
		else
			operand2 = "1" + operand2.substring(1);
		String result = floatAddition(operand1,operand2,eLength,sLength,gLength);
		return result;
	}


	/**
	 * �������˷����ɵ���{@link #integerMultiplication(String, String, int) integerMultiplication}�ȷ���ʵ�֡�<br/>
	 * ����floatMultiplication("00111110111000000", "00111111000000000", 8, 8)
	 * @param operand1 �����Ʊ�ʾ�ı�����
	 * @param operand2 �����Ʊ�ʾ�ĳ���
	 * @param eLength ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @return ����Ϊ2+eLength+sLength���ַ�����ʾ����˽��,���е�1λָʾ�Ƿ�ָ�����磨���Ϊ1������Ϊ0��������λ����������Ϊ���š�ָ���������ʾ����β������λ���أ����������Ϊ��0����
	 */
	public String floatMultiplication (String operand1, String operand2, int eLength, int sLength) {
		String resultSign = "";
		String resultExpon = "";
		String resultEndStr = "";
		String result = "";
		String exponStr1 = "0" + operand1.substring(1,eLength + 1);
		String exponStr2 = "0" + operand2.substring(1,eLength + 1);//����
		String endStr1 = operand1.substring(eLength + 1);
		String endStr2 = operand2.substring(eLength + 1);//β��
		int expon1 = Integer.valueOf(integerTrueValue(exponStr1));
		int expon2 = Integer.valueOf(integerTrueValue(exponStr2));
		int resultExponNum = expon1 + expon2 - (int)(Math.pow(2, eLength - 1)) + 1;
		if(resultExponNum <= 0){
			result = "0";
			while(result.length() < 2+eLength+sLength){
				result = result + "0";
			}
			return result;
		}
		if((operand1.charAt(0) ^ operand2.charAt(0)) == 1)
			resultSign = "1";
		else
			resultSign = "0";
		if(expon1 == 0)
			endStr1 = "0" + endStr1;
		else
			endStr1 = "1" + endStr1;
		if(expon2 == 0)
			endStr2 = "0" + endStr2;
		else
			endStr2 = "1" + endStr2;
		for(int i = 0;i < sLength;i++){
		endStr1 = "0" + endStr1;
		endStr2 = "0" + endStr2;
		}
		int temp = (endStr1.length() + 3) / 4;
		resultEndStr = integerMultiplication(endStr1, endStr2, temp * 4);
		resultEndStr = resultEndStr.substring(resultEndStr.length() - sLength * 2 - 2);
		if(resultEndStr.charAt(0) == '1'){
			resultExponNum++;
			resultEndStr = resultEndStr.substring(1,1 + sLength);
		}
		else{
			if(resultEndStr.charAt(1) == '1'){
				resultEndStr = resultEndStr.substring(2,2 + sLength);
			}
			else{
				resultExponNum--;
				int i = 2;
				for(;resultEndStr.charAt(i) != '1' && (i < resultEndStr.length() - 1);i++){
					resultExponNum--;
				}
				if((i + 1 ) < resultEndStr.length() )
				resultEndStr = resultEndStr.substring(i + 1,i + 1 + sLength);
				else{
					resultEndStr = resultEndStr.substring(0,sLength);
					resultExponNum = resultExponNum + i;
				}
			}
		}
		resultExpon = unsignedRepresentation(resultExponNum,eLength);
		if((resultExponNum == Math.pow(2, eLength) - 1 ) || (resultExpon.charAt(0) == '0' && operand1.charAt(1) == '1' && operand2.charAt(1) == '1')){
			result = "1";
			while(result.length() < 2+eLength+sLength){
				result = result + "0";
			}
			return result;
		}
		else{
			result = "0";
			result = result + resultSign + resultExpon + resultEndStr;
			return result;
		}
	}


	/**
	 * �������������ɵ���{@link #integerDivision(String, String, int) integerDivision}�ȷ���ʵ�֡�<br/>
	 * ����floatDivision("00111110111000000", "00111111000000000", 8, 8)
	 * @param operand1 �����Ʊ�ʾ�ı�����
	 * @param operand2 �����Ʊ�ʾ�ĳ���
	 * @param eLength ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @return ����Ϊ2+eLength+sLength���ַ�����ʾ����˽��,���е�1λָʾ�Ƿ�ָ�����磨���Ϊ1������Ϊ0��������λ����������Ϊ���š�ָ���������ʾ����β������λ���أ����������Ϊ��0����
	 */
	public String floatDivision (String operand1, String operand2, int eLength, int sLength) {
		String resultSign = "";
		String resultExpon = "";
		String resultEndStr = "";
		String result = "";
		String exponStr1 = "0" + operand1.substring(1,eLength + 1);
		String exponStr2 = "0" + operand2.substring(1,eLength + 1);//����
		String endStr1 = operand1.substring(eLength + 1);
		String endStr2 = operand2.substring(eLength + 1);//β��
		int expon1 = Integer.valueOf(integerTrueValue(exponStr1));
		int expon2 = Integer.valueOf(integerTrueValue(exponStr2));
		int resultExponNum = expon1 - expon2 + (int)(Math.pow(2, eLength - 1)) - 1;
		if(resultExponNum <= 0){
			result = "0";
			while(result.length() < 2+eLength+sLength){
				result = result + "0";
			}
			return result;
		}
		if((operand1.charAt(0) ^ operand2.charAt(0)) == 1)
			resultSign = "1";
		else
			resultSign = "0";
		if(expon1 == 0)
			endStr1 = "0" + endStr1;
		else
			endStr1 = "1" + endStr1;
		if(expon2 == 0)
			endStr2 = "0" + endStr2;
		else
			endStr2 = "1" + endStr2;
		endStr1 = "0" + endStr1;
		endStr2 = "0" + endStr2;
		int temp = (endStr1.length() + 3) / 4;
		while(endStr1.length() < endStr2.length() + temp * 4 - 1){
			endStr1 = endStr1 + "0";
		}
		resultEndStr = integerDivision(endStr1, endStr2, temp * 4);
		if(resultEndStr.equals("NaN")){//��0
			String exception = "0" + resultSign;
			for(int i = 0;i < eLength;i++)
				exception = exception + "1";
			for(int i = 0;i < sLength;i++)
				exception = exception + "0";
			return exception;
		}
		resultEndStr = resultEndStr.substring(1,1 + sLength + 1);
		if(resultEndStr.charAt(0) == '0'){
			resultExponNum--;
			resultEndStr = leftShift(resultEndStr,1);
		}
		if(resultExponNum <= 0){
			result = "0";
			while(result.length() < 2+eLength+sLength){
				result = result + "0";
			}
			return result;
		}
		resultEndStr = resultEndStr.substring(1) ;
		resultExpon = unsignedRepresentation(resultExponNum,eLength);
		result = "0" + resultSign + resultExpon + resultEndStr;
		return result;
	}

	//����ҵ�е��õ��ĸ�������
    public static String turn(String operand,int Length){//��������λ��
		int High_Flag1 = 0;
		if(operand.charAt(0) == '1')
			High_Flag1 = 1;
		while(operand.length() < Length){
			if(High_Flag1 == 0)
				operand = "0" + operand;
			else
				operand = "1" + operand;
		}
		return operand;
    }

	public static String keepRightShift(String operand,int n,int keep){//����ĩβ�߼�����
		String result = "";
		while(n != 0){
			result = result + "0";
			n--;
		}
		result = result + operand;
		if(result.length() >  operand.length() + keep)
			result = result.substring(0,operand.length() + keep);
		else{
			while(result.length() < n + operand.length() + keep)
				result = result + "0";
		}
		return result;
	}

	public static String unsignedRepresentation(int n,int length){//�޷�������ת��Ϊ������
		String result = "";
		while(n != 0){
		if(n % 2 == 1)
			result = "1" + result ;
		else
			result = "0" + result ;
		n = n / 2;
		}
		while(result.length() < length)
			result = "0" + result;
		return result;
	}
}