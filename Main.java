
/*************************************************************************
 *  Compilation:  javac Main.java
 *  Execution:    java Main
 *************************************************************************/
import java.util.*;
 
public class Main {

	final static int testIteration = 100;
	final static int elementCount = 5;
	private static int testNum = 0;
  
	private static int[][][] readInput(){
		
		int[][][] result = null;
		String input = null;
		try {
			
			Scanner in = new Scanner(System.in);

			// read first input: number of tests
			testNum = in.nextInt();
			
			result = new int[testNum][elementCount][elementCount];
			
			for (int i = 0; i < testNum; i++){
				for (int y = 0; y < elementCount; y++){
					String testElement = in.next();
					for (int x = 0; x < testElement.length(); x++){
						result[i][y][x] = Character.getNumericValue(testElement.charAt(x));
					}
				}
			}
		} catch (Exception e){
			  e.printStackTrace();
		}
		return result;
	}

	private static int[][] iterateTest(int[][] test){
		int[][] result = new int[elementCount][elementCount];
		
		for (int y = 0; y < elementCount; y++) {
			for (int x = 0; x < elementCount; x++){
				int res = checkSurroundingCell(test, y, x);
				result[y][x] = res;
			}
		}
		
		return result;
	}
	
	private static int checkSurroundingCell(int[][] test, int y, int x){
		int neighbourCell = 0;
		int tx = 0;
		int ty = 0;
		
		// 1st neighbour cell
		tx = (x - 1 >= 0) ? x - 1 : elementCount - 1;
		ty = (y - 1 >= 0) ? y - 1 : elementCount - 1;
		neighbourCell += test[ty][tx];
		
		// 2nd neighbour cell
		tx = x;
		ty = (y - 1 >= 0) ? y - 1 : elementCount - 1;
		neighbourCell += test[ty][tx];
		
		// 3rd neighbour cell
		tx = (x + 1 < elementCount) ? x + 1 : 0;
		ty = (y - 1 >= 0) ? y - 1 : elementCount - 1;
		neighbourCell += test[ty][tx];
		
		// 4th neighbour cell
		tx = (x - 1 >= 0) ? x - 1 : elementCount - 1;
		ty = y;
		neighbourCell += test[ty][tx];
		
		// 5th neighbour cell
		tx = (x + 1 < elementCount) ? x + 1 : 0;
		ty = y;
		neighbourCell += test[ty][tx];
		
		// 6st neighbour cell
		tx = (x - 1 >= 0) ? x - 1 : elementCount - 1;
		ty = (y + 1 < elementCount) ? y + 1 : 0;
		neighbourCell += test[ty][tx];
		
		// 7nd neighbour cell
		tx = x;
		ty = (y + 1 < elementCount) ? y + 1 : 0;
		neighbourCell += test[ty][tx];
		
		// 8rd neighbour cell
		tx = (x + 1 < elementCount) ? x + 1 : 0;
		ty = (y + 1 < elementCount) ? y + 1 : 0;
		neighbourCell += test[ty][tx];
		
		
		// Game of Life rule is applied here!!!
		int result = (
			// check whether the cell itself alive or not
			(test[y][x] == 1) ? 
			// if yes, check whether it is surrounded by less than 2 or more than 3 alive cells
			((neighbourCell < 2 || neighbourCell > 3) ? 0 : 1) 
			: 
			// if not, check whether the dead cell surrounded by exactly 3 alive cells
			((neighbourCell == 3) ? 1 : 0)
		);
		return result;
	}
	
	private static boolean checkTestResult(int[][] test){
		boolean result = false;
		
		for (int y = 0; y < elementCount && !result; y++) {
			for (int x = 0; x < elementCount && !result; x++){
				result = (test[y][x] > 0);
			}
		}
		return result;
	}
	
    public static void main(String[] args) {
		
		int[][][] tests = readInput();
		
		// Loop through test.
		for (int k = 0; k < tests.length; k++) {
			int[][] test = tests[k];
			
			
			for (int l = 0; l < testIteration; l++){
				test = iterateTest(test);
			}
			
			boolean testResult = checkTestResult(test);
			System.out.println((testResult) ? "YES" : "NO");
		}
    }

}
