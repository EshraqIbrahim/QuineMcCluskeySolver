package tabular;

import java.util.LinkedList;
import java.util.Queue;

public class tabular {
	public int num_variable;
	public LinkedList<Integer> minterms = new LinkedList<Integer>();
	public LinkedList<Integer> donot_care = new LinkedList<Integer>();
	public LinkedList<LinkedList<Integer>> groub0 = new LinkedList<LinkedList<Integer>>();
	public LinkedList<LinkedList<Integer>> groub1 = new LinkedList<LinkedList<Integer>>();
	public LinkedList<LinkedList<Integer>> groub2 = new LinkedList<LinkedList<Integer>>();
	public LinkedList<LinkedList<Integer>> groub3 = new LinkedList<LinkedList<Integer>>();
	public LinkedList<LinkedList<Integer>> groub4 = new LinkedList<LinkedList<Integer>>();
	public LinkedList<LinkedList<Integer>> groub5 = new LinkedList<LinkedList<Integer>>();
	public LinkedList<LinkedList<Integer>> groub6 = new LinkedList<LinkedList<Integer>>();
	public LinkedList<LinkedList<Integer>> prime_implicant = new LinkedList<LinkedList<Integer>>();
	public LinkedList<LinkedList<Integer>> prime_implicantpetrick = new LinkedList<LinkedList<Integer>>();
	
	public LinkedList<LinkedList<Integer>> eseential_prime_implicant = new LinkedList<LinkedList<Integer>>();
	public String steps = "";
	public String[] answer;
	public Object[][] Implicantchart;
	public int answersize =0;

	public void fill_array() {
		for (int i = 0; i < minterms.size(); i++) {
			int count;
			if (minterms.get(i) < 0 || minterms.get(i) > 63) {
				throw new RuntimeException("the mumber of minterm is wrong");
			} else {
				int n = minterms.get(i);
				count = 0;
				while (n != 0) {
					n = n & (n - 1);
					count++;
				}
			}
			num_boolean_groub(count, minterms.get(i));
		}
		for (int i = 0; i < donot_care.size(); i++) {
			int count;
			if (donot_care.get(i) < 0 || donot_care.get(i) > 63) {
				throw new RuntimeException("the mumber of minterm is wrong");
			} else {
				int n = donot_care.get(i);
				count = 0;
				while (n != 0) {
					n = n & (n - 1);
					count++;
				}
			}
			num_boolean_groub(count, donot_care.get(i));
		}

	}

	public void num_boolean_groub(int num_one, int minterm) {
		LinkedList<Integer> temp = new LinkedList<Integer>();
		switch (num_one) {
		case 0:
			temp.add(0);
			temp.add(minterm);
			groub0.add(temp);
			break;
		case 1:
			temp.add(0);
			temp.add(minterm);
			groub1.add(temp);
			break;
		case 2:
			temp.add(0);
			temp.add(minterm);
			groub2.add(temp);
			break;
		case 3:
			temp.add(0);
			temp.add(minterm);
			groub3.add(temp);
			break;
		case 4:
			temp.add(0);
			temp.add(minterm);
			groub4.add(temp);
			break;
		case 5:
			temp.add(0);
			temp.add(minterm);
			groub5.add(temp);
			break;
		case 6:
			temp.add(0);
			temp.add(minterm);
			groub6.add(temp);
			break;
		default:
			throw new RuntimeException("the mumber of minterm is wrong");
		}
	}

	public void groubing() {
		printsteps();
		if (!(groub0.isEmpty() || groub1.isEmpty())) {
			groubing2list(groub0, groub1);
		}
		if (!(groub1.isEmpty() || groub2.isEmpty())) {
			groubing2list(groub1, groub2);
		}
		if (!(groub2.isEmpty() || groub3.isEmpty())) {
			groubing2list(groub2, groub3);
		}
		if (!(groub3.isEmpty() || groub4.isEmpty())) {
			groubing2list(groub3, groub4);
		}
		if (!(groub4.isEmpty() || groub5.isEmpty())) {
			groubing2list(groub4, groub5);
		}
		if (!(groub5.isEmpty() || groub6.isEmpty())) {
			groubing2list(groub5, groub6);
		}
		removelastgroub();
		get_prime(groub0);
		get_prime(groub1);
		get_prime(groub2);
		get_prime(groub3);
		get_prime(groub4);
		get_prime(groub5);
		get_prime(groub6);
		removevisit(groub0);
		removevisit(groub1);
		removevisit(groub2);
		removevisit(groub3);
		removevisit(groub4);
		removevisit(groub5);
		removevisit(groub6);
		if (groub0.isEmpty() && groub1.isEmpty() && groub2.isEmpty() && groub3.isEmpty() && groub4.isEmpty()
				&& groub5.isEmpty() && groub6.isEmpty()) {
			removeone(prime_implicant);
			removedublicale(prime_implicant);
			setmimtermspetrick();
			return;
		} else {
			groubing();
		}

	}

	public void get_prime(LinkedList<LinkedList<Integer>> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get(0) == 0) {
				prime_implicant.add(list.get(i));
				list.remove(i);
				i--;
			}
		}

	}

	public void removelastgroub() {
		if (!groub6.isEmpty()) {
			get_prime(groub6);
			groub6.removeAll(groub6);
			return;
		}
		if (!groub5.isEmpty()) {
			get_prime(groub5);
			groub5.removeAll(groub5);
			return;
		}
		if (!groub4.isEmpty()) {
			get_prime(groub4);
			groub4.removeAll(groub4);
			return;
		}
		if (!groub3.isEmpty()) {
			get_prime(groub3);
			groub3.removeAll(groub3);
			return;
		}
		if (!groub2.isEmpty()) {
			get_prime(groub2);
			groub2.removeAll(groub2);
			return;
		}
		if (!groub1.isEmpty()) {
			get_prime(groub1);
			groub1.removeAll(groub1);
			return;
		}
		if (!groub0.isEmpty()) {
			get_prime(groub0);
			groub0.removeAll(groub0);
			return;
		}
	}

	public void removeone(LinkedList<LinkedList<Integer>> list) {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).removeFirst();
		}

	}

	public void removevisit(LinkedList<LinkedList<Integer>> list) {
		for (int i = 0; i < list.size(); i++) {
			LinkedList<Integer> temp = new LinkedList<Integer>();
			temp.addAll(list.get(i));
			temp.set(0, 0);
			list.set(i, temp);
		}
	}

	public void groubing2list(LinkedList<LinkedList<Integer>> list1, LinkedList<LinkedList<Integer>> list2) {

		LinkedList<LinkedList<Integer>> listtemp = new LinkedList<LinkedList<Integer>>();
		listtemp.addAll(list1);
		for (int i = 0; i < listtemp.size(); i++) {
			LinkedList<Integer> temp1 = new LinkedList<Integer>();
			temp1.addAll(listtemp.get(i));
			int flag_minmize = 0;
			for (int j = 0; j < list2.size(); j++) {
				LinkedList<Integer> temp2 = new LinkedList<Integer>();
				temp2.addAll(list2.get(j));
				int x = temp2.get(1)-temp1.get(1);
				if (temp1.size() == temp2.size()) {
					int flag = 1;
					for (int g = 2; g < temp1.size(); g++) {
						flag = 0;
						for (int h = 2; h < temp1.size(); h++) {
							if (temp1.get(g) == temp2.get(h)) {
								flag = 1;
								break;
							}
						}
						if (flag == 0) {
							break;
						}
					}
					if ((flag == 1)
							&&((x == 1) || (x == 2) || (x == 4) || (x == 8)
									|| (x == 16) || (x == 32) || (x == 64))) {
						LinkedList<Integer> temp3 = new LinkedList<Integer>();
						temp3.addAll(temp1);
						temp3.set(0, 1);
						temp2.set(0, 1);
						temp3.add(Math.abs(x));
						flag_minmize = 1;
						list1.add(temp3);
						list2.set(j, temp2);
					}

				}

			}
			if (flag_minmize == 1) {
				list1.removeFirstOccurrence(listtemp.get(i));
			} else {
				if ((flag_minmize == 0) && (temp1.get(0) == 1)) {
					list1.removeFirstOccurrence(listtemp.get(i));
				}
			}
		}

	}

	public void removedublicale(LinkedList<LinkedList<Integer>> list) {
		for (int i = 0; i < list.size(); i++) {
			LinkedList<Integer> temp1 = new LinkedList<Integer>();
			temp1.addAll(list.get(i));
			for (int j = i + 1; j < list.size(); j++) {
				LinkedList<Integer> temp2 = new LinkedList<Integer>();
				temp2.addAll(list.get(j));
				if ((temp1.size() == temp2.size()) && (temp1.get(0) == temp2.get(0))) {
					int p = temp1.removeFirst();
					temp2.removeFirst();
					int flag = 0;
					if (temp1.containsAll(temp2)) {
						if (temp2.containsAll(temp1)) {
							flag = 1;
						}
					}
					temp1.addFirst(p);
					temp2.addFirst(p);
					if (flag == 1) {
						list.remove(j);
						j--;
					}

				}
			}
		}
	}

	 public void printsteps() {
	
	 steps = steps + print(groub0);
	 if(groub0.size()!=0){
		 steps = steps + "/"; 
	 }
	 steps = steps + print(groub1);
	 if(groub1.size()!=0){
		 steps = steps + "/"; 
	 }
	 steps = steps + print(groub2);
	 if(groub2.size()!=0){
		 steps = steps + "/"; 
	 }
	 steps = steps + print(groub3);
	 if(groub3.size()!=0){
		 steps = steps + "/"; 
	 }
	 steps = steps + print(groub4);
	 if(groub4.size()!=0){
		 steps = steps + "/"; 
	 }
	 steps = steps + print(groub5);
		 steps = steps + "***"; 
	System.out.println(steps);
	System.out.println("");
	 }
	
	 public String print(LinkedList<LinkedList<Integer>> list) {
	 String steps = "";
	 for (int i = 0; i < list.size(); i++) {
	 for (int j = 1; j < list.get(i).size(); j++) {
	 if (j == 1) {
	 steps += list.get(i).get(j);
	 steps += ' ';
	 if (list.get(i).size() != 2) {
	 steps += '(';
	 }
	 } else {
	 if (j == list.get(i).size() - 1) {
	 steps += list.get(i).get(j);
	 steps += ')';
	 } else {
	 steps += list.get(i).get(j);
	 steps += ',';
	 }
	 }
	 }
	 }
	
	 return steps;
	 }

	public void setmimtermspetrick() {

		for (int i = 0; i < prime_implicant.size(); i++) {
			LinkedList<Integer> temp = new LinkedList<Integer>();
			int p = prime_implicant.get(i).get(0);
			int sum = p;
			temp.add(p);
			for (int j = 1; j < prime_implicant.get(i).size(); j++) {
				sum += prime_implicant.get(i).get(j);
				temp.add(p + prime_implicant.get(i).get(j));
			}

			if (prime_implicant.get(i).size() > 2) {
				temp.add(sum);
			}
			prime_implicantpetrick.add(temp);
		}

	}

	public void createchart() {
		int n = prime_implicantpetrick.size();
		int m = minterms.size();
		Implicantchart = new Object[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				Implicantchart[i][j] = '.';
			}
		}
		int counter;
		int IndexI = 0;
		int IndexJ = 0;
		for (int j = 0; j < m; j++) {
			counter = 0;
			int minterm = minterms.get(j);
			for (int i = 0; i < n; i++) {

				if (prime_implicantpetrick.get(i).contains(minterm)) {
					Implicantchart[i][j] = 'X';
					counter++;
					IndexI = i;
					IndexJ = j;
				}

			}
			if (counter == 1) {
				Implicantchart[IndexI][IndexJ] = 'E';
			}
		}
		System.out.println("");
		System.out.println("Prime Implicant Chart");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(Implicantchart[i][j]);
			}
			System.out.print(prime_implicantpetrick.get(i));
			System.out.println(" ");
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if ((char) Implicantchart[i][j] == 'E') {
					eseential_prime_implicant.add(prime_implicant.get(i));
					for (int x = 0; x < m; x++) {
						if ((char) Implicantchart[i][x] == 'E' || (char) Implicantchart[i][x] == 'X') {
							for (int y = 0; y < n; y++) {
								Implicantchart[y][x] = '.';
							}
						}
					}
				}

			}
		}
		System.out.println("");
		System.out.println("Removing essential prime implicants");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(Implicantchart[i][j]);
			}
			System.out.println("");
		}

	}
	public LinkedList<LinkedList<Integer>> multiplyLists(LinkedList<LinkedList<Integer>> List1,
			LinkedList<LinkedList<Integer>> List2) {
		LinkedList<LinkedList<Integer>> ans = new LinkedList<LinkedList<Integer>>();
		LinkedList<Integer>[] ans2 = new LinkedList[1000];
		LinkedList<Integer> tempp1 = new LinkedList<Integer>();
		LinkedList<Integer> tempp2 = new LinkedList<Integer>();
		LinkedList<Integer> tempp3 = new LinkedList<Integer>();

		int counter = 0;
		for (int w = 0; w < List1.size(); w++) {
			tempp1 = List1.get(w);
			
			for (int l = 0; l < List2.size(); l++) {
				for (int b = 0; b < tempp1.size(); b++) {
					tempp3.addLast(tempp1.get(b));
				}

				tempp2 = List2.get(l);
				
				for (int r = 0; r < tempp2.size(); r++) {
				
				}
				for (int b = 0; b < tempp2.size(); b++) {
					if (!tempp3.contains(tempp2.get(b))) {
						tempp3.addLast(tempp2.get(b));

					}
				}
				
				for (int r = 0; r < tempp3.size(); r++) {
					if (ans2[counter] == null) {
						ans2[counter] = new LinkedList<Integer>();
					}
					ans2[counter].addLast(tempp3.get(r));
					

				}
				ans.addLast(ans2[counter]);
				counter++;
				tempp3.clear();
			}

		}
		
		return ans;

	}
	 public String tovariable(LinkedList<Integer> temp) {
	 String st = "";
	 LinkedList<Integer> arr = new LinkedList<Integer>();
	
	 String s = Integer.toBinaryString(temp.get(0));
	 for (int i = 0; i < num_variable; i++) {
	 if (i < s.length()) {
	 if (s.charAt(i) == '0')
	 arr.add(0);
	 else {
	 arr.add(1);
	 }
	 } else {
	 arr.addFirst(0);
	 }
	 }
	 for (int i = 1; i < temp.size(); i++) {
	 arr.set((num_variable-1-binary(temp.get(i))), -1);
	 }
	 for (int i = 0; i < arr.size(); i++) {
	 if (arr.get(i) != -1) {
	 st+=variable(i);
	 if(arr.get(i)==0){
	 st+='’';
	 }
	 }
	 }
	 return st;
	 }
	

	public void output(LinkedList<LinkedList<Integer>> temp6) {
		String minimum = "";
		answer=new String[temp6.size()+eseential_prime_implicant.size()];
		for (int i = 0; i < temp6.size(); i++) {
			minimum = "";
			for (int j = 0; j <temp6.get(i).size(); j++) {
				int k=temp6.get(i).get(j);
				if(j!=0){
				minimum+="+";
				}
				minimum+=tovariable(prime_implicant.get(k));
			
				
			}
			
			for (int p = 0; p < eseential_prime_implicant.size(); p++) {
					minimum+="+";
				minimum+=tovariable(eseential_prime_implicant.get(p));
				
		}
		answer[answersize]=minimum;
		answersize++;
		}
		for (int p = 0; p < eseential_prime_implicant.size(); p++) {
			System.out.println(eseential_prime_implicant.get(p));
		}
		if(temp6.size()==0){
			for (int p = 0; p < eseential_prime_implicant.size(); p++) {
				if(p!=0){
					minimum+="+";
					}
					minimum+=tovariable(eseential_prime_implicant.get(p));
					
				
			}
			answer[answersize]=minimum;
			answersize++;
			}

		
	

	}
	public int binary(int x) {
		switch (x) {
		case 1:
			return 0;
		case 2:
			return 1;
		case 4:
			return 2;
		case 8:
			return 3;
		case 16:
			return 4;
		case 32:
			return 5;
		case 64:
			return 6;
		default:
			throw new RuntimeException("the prime implicant is wrong");
		}

	}
	
	public char variable(int x) {
		switch (x) {
		case 6:
			return 'G';
		case 5:
			return 'F';
		case 4:
			return 'E';
		case 3:
			return 'D';
		case 2:
			return 'C';
		case 1:
			return 'B';
		case 0:
			return 'A';
		default:
			throw new RuntimeException("the prime implicant is wrong");
		}

	}
	public void petrick() {

		int n = prime_implicantpetrick.size();
		int m = minterms.size();
		Queue<LinkedList<LinkedList<Integer>>> qe = new LinkedList<LinkedList<LinkedList<Integer>>>();
		for (int j = 0; j < m; j++) {
			LinkedList<LinkedList<Integer>> temp = new LinkedList<LinkedList<Integer>>();
			for (int i = 0; i < n; i++) {
				LinkedList<Integer> temp1 = new LinkedList<Integer>();
				if ((char) Implicantchart[i][j] == 'X') {
					temp1.add(i);
					temp.add(temp1);
				}

			}
			if(temp.size()!=0){
			qe.add(temp);
			}
			
		}
		LinkedList<LinkedList<Integer>> temp5 = new LinkedList<LinkedList<Integer>>();
		if(qe.size()>0){
		while(qe.size()!=1){
			LinkedList<LinkedList<Integer>> temp1 = new LinkedList<LinkedList<Integer>>();
			LinkedList<LinkedList<Integer>> temp2 = new LinkedList<LinkedList<Integer>>();
			LinkedList<LinkedList<Integer>> temp3 = new LinkedList<LinkedList<Integer>>();
			temp1 = qe.peek();
			qe.remove();
			temp2 = qe.peek();
			qe.remove();
			temp3 = multiplyLists(temp1,temp2);
			
			qe.add(temp3);
			
		}
		
		
		// remove dublicate
		LinkedList<LinkedList<Integer>> temp4 = new LinkedList<LinkedList<Integer>>();
		temp4 = qe.peek();
		int minsize = temp4.size();
		for(int z=0;z<temp4.size();z++){
			LinkedList<Integer> tempp1 = new LinkedList<Integer>();
			tempp1 = temp4.get(z);
			int size = tempp1.size();
			if(size <minsize){
				minsize = size;
			}
			
		}
		
		
		
		for(int z=0;z<temp4.size();z++){
			LinkedList<Integer> temppp1 = new LinkedList<Integer>();
			temppp1 = temp4.get(z);
			int size = temppp1.size();
			if(size ==minsize){
				temp5.add(temppp1);
			}
		}
		System.out.println("");
		for(int z=0;z<temp5.size();z++){
			System.out.println(temp5.get(z));
			
		}
		}
		System.out.println("");
	   System.out.println("OUTPUTS:");
		output(temp5);
		for(int z=0;z<answersize;z++){
			System.out.println(answer[z]);
			
		}
		
		
	}
	

}
