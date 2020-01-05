package gameOfLifeInclGraphSims;

public class LifeGenerator {

	static int LIVE = 1, DEAD = 0, NEIGHBORHOODREVIVE = 3, OVERCROWDED = 3, UNDERPOPULATED = 2;

	public static int[][] getGeneration(int[][] cells, int generations) {

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
