package com.valanikevin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class nonIntelligentGame
 */
@WebServlet("/nonIntelligentGame")
public class nonIntelligentGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Scanner scan = new Scanner(System.in);
	static int[] rows={-2,-2,-1,-1,+1,+1,+2,+2};
    static int[] cols={-1,+1,-2,+2,-2,+2,-1,+1};
    static int[][] gameTable = new int[8][8];
    static int currentRow=0;
    static int currentCol=0;
    static int moveCounter=0;
 
    public nonIntelligentGame() {
        super();
     
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userRow = Integer.parseInt(request.getParameter("gameRow"));
		int userCol = Integer.parseInt(request.getParameter("gameCol"));
		if(!((userRow <0 || userCol >8)&&(userCol<0 || userCol > 8))) {
			startGameNonIntelligent(Integer.parseInt(request.getParameter("nonIntTimes")), userRow, userCol);
		}
		else {
			startGameNonIntelligent(Integer.parseInt(request.getParameter("nonIntTimes")), 0, 0);
		}
		
		
		com.valanikevin.gameTable.setGameTable(gameTable);
		com.valanikevin.gameTable.setTempValue(moveCounter);
		response.sendRedirect("nonIntelligentGame.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	static void makeTableEmpty(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                gameTable[i][j]=-1;
            }
        }
        //gameTable[currentRow][currentCol]=0;
    }
	
	static void startGameNonIntelligent(int runGameTimes, int userRow, int userCol){
        makeTableEmpty();
        currentRow=userRow;
        currentCol=userCol;
        gameTable[currentRow][currentCol]=0;
        int runTimes = runGameTimes;
        for(int h=0;h<runTimes;h++){
            for(int i=0;i<64;i++){
            isMoveable();
        }
            System.out.println("Trial "+h+" Total Moves: "+moveCounter);
            if(h!=runTimes-1){
              makeTableEmpty();
            moveCounter=0;  
            }
            
        }
        //printTable();
        //System.out.println("Total Moves: "+moveCounter);
    }
	
	static int isMoveable(){
	       int result=0;
	       for(int i=0;i<8;i++){
	           
	        if((currentRow+rows[i]>=0&&currentCol+cols[i]>=0)&&(currentRow+rows[i]<8&&currentCol+cols[i]<8)){
	            if(gameTable[currentRow+rows[i]][currentCol+cols[i]]==-1){
	                 currentRow=currentRow+rows[i];
	                 currentCol=currentCol+cols[i];
	                 moveCounter++;
	                 gameTable[currentRow][currentCol]=moveCounter;
	            }
	       }
	        else{
	            //gameTable[currentRow][currentCol]=-1;
	        }
	       }
	       
	      return result;
	    }
	
	static void printTable(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                System.out.print("\t"+gameTable[i][j]);
            }
            System.out.println("");
        }
        System.out.println("------------");
    }

}
