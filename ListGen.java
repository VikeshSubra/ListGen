import java.io.*;

public class ListGen{
	public static void main(String args[]) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader("listfiles/input.txt"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("listfiles/output.txt"));
		String line;
		int wordlength = 0;
		int maxchars = 0;
		int longestSeries = 0;
		int repetitions = 1;
		int[] worddata;
		int[] currentPositions;
		String[][] characters;
		while((line = reader.readLine()) != null){
			if(line.equals("$SYM$")){
				line = "~,`,!,@,#,$,%,^,&,*,(,),_,-,+,=,{,[,},],|,\\,:,;,\",',<,>,.,?,/, , ";
			}
			if(line.equals("$END$")){
				line = ".,!, ";
			}
			if(line.split(",").length > longestSeries){
				longestSeries = line.split(",").length;
			}
			wordlength++;
		}
		System.out.println(longestSeries);
		worddata = new int[wordlength];
		currentPositions = new int [wordlength];
		characters = new String[wordlength][longestSeries];
		reader.close();
		reader = new BufferedReader(new FileReader("listfiles/input.txt"));
		int count = 0;
		while((line = reader.readLine()) != null){
			boolean addBlank = false;
			if(line.equals("$SYM$")){
				line = "~,`,!,@,#,$,%,^,&,*,(,),_,-,+,=,{,[,},],|,\\,:,;,\",',<,>,.,?,/, , ";
				addBlank = true;
			}
			if(line.equals("$END$")){
				line = ".,!, ";
				addBlank = true;
			}
			currentPositions[count] = 0;
			characters[count] = line.split(",");
			worddata[count] = line.split(",").length;
			if(addBlank)
			characters[count][worddata[count]-1] = "";
			count++;
		}
		for(int x = 0; x < worddata.length; x++){
			repetitions *= worddata[x];
		}
		System.out.println(repetitions);
		int num = 0;
		for(int x = 0; x < repetitions; x++){
			boolean continueLoop = false;
			String password= "";
			for(int y = 0; y < worddata.length; y++){
				password = password.concat(characters[y][currentPositions[y]]);
			}
			for(int y = 0; y < worddata.length; y++){
				if (currentPositions[y]+1 == worddata[y]){
					currentPositions[y] = 0;
					continueLoop = true;
				}
				else{
					currentPositions[y]++;
					continueLoop = false;
				}
				if(!continueLoop){
					break;
				}
			}
			//System.out.println(password);
			writer.write(password, 0, password.length());
			writer.newLine();
			num++;
			if(num%1000000==0)
			System.out.println(num);
		}
writer.write("pLaY&4%R3aL!", 0, 12);
		writer.close();
		reader.close();
	}
}
