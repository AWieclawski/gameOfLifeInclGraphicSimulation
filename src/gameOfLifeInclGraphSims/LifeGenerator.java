package gameOfLifeInclGraphSims;

public class LifeGenerator {

	static int LIVE = 1, DEAD = 0, NEIGHBORHOODREVIVE = 3, OVERCROWDED = 3, UNDERPOPULATED = 2;

	public static int[][] getGeneration(int[][] cells, int generations) {

		int[][] nextGeneration = new int[cells.length][cells[0].length];

		return getGeneration(nextGeneration, generations - 1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
