package gameOfLifeInclGraphSims;

public class LifeGenerator {

	static int LIVE = 1, DEAD = 0, NEIGHBORHOODREVIVE = 3, OVERCROWDED = 3, UNDERPOPULATED = 2;

	public static int[][] getGeneration(int[][] cells, int generations) {
		
		int[][] tempArray = moveCellsToTempArray(cells);

		int[][] nextGeneration = newTempArray(cells);

		return getGeneration(nextGeneration, generations - 1);
	}
	
	private static int[][] newTempArray(int[][] cells) {
		// New temporary array for neighborhood tests for 'cells' array
		// It has one more cell around i.e +2 more in each dimension,
		// than primary array.
		int[][] tempArray = new int[cells.length + 2][cells[0].length + 2];
		
		return tempArray;
	}

	private static int[][] moveCellsToTempArray(int[][] cells) {
		int[][] tempArray = newTempArray(cells);
		for (int y = 0; y < cells.length; y++)
			for (int x = 0; x < cells[y].length; x++)
				// put the primary array at center of a new hypothetical research one
				tempArray[y + 1][x + 1] = cells[y][x];

		return tempArray;
	}
	
	private static int[][] cropAroundLiveCells(int[][] cells) {
		// remove DEAD cells boundary from temporary array
		int maxLeft = cells[0].length, maxRight = 0, uppermost = cells.length, lowest = 0;

		for (int y = 0; y < cells.length; y++)
			for (int x = 0; x < cells[y].length; x++)
				if (cells[y][x] == 1) {

					if (y < uppermost)
						uppermost = y; // new upper limit coordinate of 'cropped' array

					if (y > lowest)
						lowest = y; // new lower limit coordinate of 'cropped' array

					if (x < maxLeft)
						maxLeft = x; // new left limit coordinate of 'cropped' array

					if (x > maxRight)
						maxRight = x; // new right limit coordinate of 'cropped' array
				}

		int[][] cropped = new int[lowest - uppermost + 1][maxRight - maxLeft + 1];
// cutting
		return cropped;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
