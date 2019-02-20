import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleShell {
	public static void main(String[] args) throws java.io.IOException{
    Commands commands = new Commands();	
		String arguments;
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		
		ProcessBuilder processor = new ProcessBuilder();
		List<String> allHistory = new ArrayList<String>();
  
		while(true){	
			System.out.print("jsh>");
			arguments = console.readLine();
			
      if (arguments.equals("") || Character.isWhitespace(arguments.charAt(0))) continue;
			 
			String[] argsArray = arguments.split(" ");
			List<String> list = new ArrayList<String>();
			
			for(int i = 0;i<argsArray.length;i++){
				list.add(argsArray[i]);
				
			}
      allHistory.addAll(list);
			
      try{

        if(allHistory.size() > 0 && list.size()>0){
				if(list.get(list.size()-1).equals("history")){
					commands.histories(allHistory);
					continue;
				}
			
			if(list.contains("cd")){
            commands.changeDirectories(processor, list);
            continue;
				 }

			if(list.get(list.size()-1).equals("!!")){

        if(allHistory.size()-2 < 0){
          System.out.println("Undefined Operation");
          continue;
        }

     else{

        if(allHistory.get(allHistory.size()-2).equals("history")){
				  commands.histories(allHistory);
          continue;
          }
        if(allHistory.get(allHistory.size()-2).equals("cd")){
				  commands.changeDirectories(processor, list);
          continue;
          }
        if(allHistory.get(allHistory.size()-2).equals("!!")){
          System.out.println("Previous Command was !!..Choose another command");
          continue;
        }
      }  
				
			}
			else if			
			(list.get(list.size()-1).charAt(0) == '!' && allHistory.size() > 0){
        String cNum = "";
        for(int i = 1; i <list.get(list.size()-1).length(); i++ ){
				 cNum+= list.get(list.size()-1).charAt(i);
        
         }
        int num = Integer.parseInt(cNum);
        
        if(num == allHistory.size()){
          System.out.println("Undefined Operation");
          continue;
        }

        if(num>allHistory.size()){
          System.out.println("No such history number..check allHistory index");
          continue;
          }
          
        else{
        if(allHistory.get(num).equals("history")){
				  commands.histories(allHistory);
          continue;
          }
        if(allHistory.get(num).equals("cd")){
				  commands.changeDirectories(processor, list);
          continue;
          }
        if(allHistory.get(num).equals("!!")){
          System.out.println("Previous Command was !!");
          continue;
        }
        if(allHistory.get(num).charAt(0) == '!'){
          System.out.println("Command Not Defined...Please Fetch Another Command");
          continue;
        }
        }
			}

			else{
			processor.command(list);
			}
        }




			Process process = processor.start();
			
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			String line;
			while((line = br.readLine()) != null)
				System.out.println(line);
			br.close();
			}
			catch (Exception e){
				System.out.println("Wrong Input...Please Check your input");
			}
		}
		
		

	}

}

