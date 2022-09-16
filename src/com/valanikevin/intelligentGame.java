package com.valanikevin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/intelligentGame")
public class intelligentGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Scanner scan = new Scanner(System.in);
	static int[][] gameTableIntPriority = new int[8][8];
    static int[][] gameTableIntCount = new int[8][8];
    static int currentRowInt=0;
    static int currentColInt=0;
    static int moveCounterInt=0;
    static int[] rowsInt={-2,-2,-1,-1,+1,+1,+2,+2};
    static int[] colsInt={-1,+1,-2,+2,-2,+2,+1,-1};

    public intelligentGame() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		int userRow = Integer.parseInt(request.getParameter("gameRow"));
		int userCol = Integer.parseInt(request.getParameter("gameCol"));
		if(!((userRow <0 || userCol >8)&&(userCol<0 || userCol > 8))) {
			startGameIntelligent(userRow, userCol);
			out.println("<center><h2>Correct Entry. Set The Incorrect Value To 0</h2></center>");
		}
		else {
			out.println("<center><h2>Incorrect Entry. Set The Incorrect Value To 0</h2></center>");
			startGameIntelligent(0,0);
		}
		
		for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                out.print("\t"+gameTableIntCount[i][j]);
            }
            out.println("");
        }
        out.println("------------");
        gameTable.setTempValue(moveCounterInt);
        gameTable.setGameTable(gameTableIntCount);
        
		//request.setAttribute("gameTableCount", gameTableIntCount);
		RequestDispatcher rd = request.getRequestDispatcher("intelligentGame.jsp");
		rd.forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	static void startGameIntelligent(int userRow, int userCol){
        makeTableEmptyInt();
        givePriorityNumber();
        currentRowInt = userRow;
        currentColInt = userCol;
        gameTableIntCount[currentRowInt][currentColInt]=0;
        
        int runTimes = 64;
        for(int i=0;i<runTimes;i++){
           isMoveable2(); 
           System.out.println("Trial "+(i+1)+" : The Knight was able to successfully touch "+moveCounterInt+" squares");
        }
        
        printTableInt();
    }
	
	/////////////=================================
	
	static int isMoveable2(){
        int[] priorityID={99,99,99,99,99,99,99,99};
        int[] priorityCollector = {99,99,99,99,99,99,99,99};
        for(int i=0;i<8;i++){
            if(currentRowInt+rowsInt[i]>=0&&currentRowInt+rowsInt[i]<8){
                if(currentColInt+colsInt[i]>=0&&currentColInt+colsInt[i]<8){
                    if(gameTableIntCount[currentRowInt+rowsInt[i]][currentColInt+colsInt[i]]==-1){
                        priorityCollector[i]=gameTableIntPriority[currentRowInt+rowsInt[i]][currentColInt+colsInt[i]];
                        priorityID[i]=i;
                    }
                }
            }
        }
        findSmallestNumber(priorityID, priorityCollector);
        if(findSmallestNumber(priorityID, priorityCollector)!=99){
            System.out.println("Smallest Number: "+findSmallestNumber(priorityID, priorityCollector));
            currentColInt=currentColInt+colsInt[findSmallestNumber(priorityID, priorityCollector)];
            currentRowInt=currentRowInt+rowsInt[findSmallestNumber(priorityID, priorityCollector)];
            moveCounterInt++;
            gameTableIntCount[currentRowInt][currentColInt]=moveCounterInt;
            printTableInt();
        }
        
        return 1;
    }
	
	//////================================
	
	static int findSmallestNumber(int[] priorityID, int[] priorityArray){
        int smallestNumber=priorityArray[0];
        int smallestNumberID=priorityID[0];
        for(int i=1;i<8;i++){
            if(priorityArray[i]<smallestNumber){
                smallestNumber=priorityArray[i];
                smallestNumberID=i;
            }
        }
        
        return smallestNumberID;
    }
	
	////=======================
	
	static boolean givePriorityNumber(){
        boolean result=false;
        int[] priorityNumber = {2,3,4,6,8};
        for(int i=0; i<8;i++){
           if(i==0||i==7){
                   gameTableIntPriority[i][0]=priorityNumber[0];
                   gameTableIntPriority[i][1]=priorityNumber[0+1];
                   gameTableIntPriority[i][2]=priorityNumber[0+2];
                   gameTableIntPriority[i][3]=priorityNumber[0+2];
                   gameTableIntPriority[i][4]=priorityNumber[0+2];
                   gameTableIntPriority[i][5]=priorityNumber[0+2];
                   gameTableIntPriority[i][6]=priorityNumber[0+1];
                   gameTableIntPriority[i][7]=priorityNumber[0];   
           }
           if(i==1||i==6){
                 gameTableIntPriority[i][0]=priorityNumber[1];
                   gameTableIntPriority[i][1]=priorityNumber[1+1];
                   gameTableIntPriority[i][2]=priorityNumber[1+2];
                   gameTableIntPriority[i][3]=priorityNumber[1+2];
                   gameTableIntPriority[i][4]=priorityNumber[1+2];
                   gameTableIntPriority[i][5]=priorityNumber[1+2];
                   gameTableIntPriority[i][6]=priorityNumber[1+1];
                   gameTableIntPriority[i][7]=priorityNumber[1];
           }
           
           if(i==2||i==3||i==4||i==5){               
                   gameTableIntPriority[i][0]=priorityNumber[2];
                   gameTableIntPriority[i][1]=priorityNumber[2+1];
                   gameTableIntPriority[i][2]=priorityNumber[2+2];
                   gameTableIntPriority[i][3]=priorityNumber[2+2];
                   gameTableIntPriority[i][4]=priorityNumber[2+2];
                   gameTableIntPriority[i][5]=priorityNumber[2+2];
                   gameTableIntPriority[i][6]=priorityNumber[2+1];
                   gameTableIntPriority[i][7]=priorityNumber[2];
           }
        }        
        return result;
    }
	
	
	static void makeTableEmptyInt(){
        for(int i=0;i<8;i++){
           for(int j=0;j<8;j++){
               gameTableIntCount[i][j]=-1;
           }
       }
       //gameTableIntCount[currentRowInt][currentColInt]=0;
   }
	
	
	////===============
	
	static void printTableInt(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                System.out.print("\t"+gameTableIntCount[i][j]);
            }
            System.out.println("");
        }
        System.out.println("------------");
    }

}
