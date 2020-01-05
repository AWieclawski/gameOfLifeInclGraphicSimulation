package gameOfLifeInclGraphSims;

import gameOfLifeInclGraphSims.GraphSimulation;

public class LifeGenerator {

	static int LIVE = 1, DEAD = 0, NEIGHBORHOODREVIVE = 3, OVERCROWDED = 3, UNDERPOPULATED = 2;

	public static int[][] getGeneration(int[][] cells, int generations) {

		if (generations < 1) // primary array
			return cropAroundLiveCells(cells);

		int[][] tempArray = moveCellsToTempArray(cells);

		int[][] nextGeneration = newTempArray(cells);

		boolean aboveSearchable = false, 
				belowSearchable = false, 
				leftSearchable = false, 
				rightSearchable = false;

		int testedCell = 0, 
			lifeInNeighborhood = 0, 
			startVertical = 0, 
			startHorizontal = 0, 
			endVertical = 0,
			endHorizontal = 0;

		for (int tempY = 0; tempY < tempArray.length; tempY++) {
			// Cell with coordinates [0,0] is in the top left corner of the array,
			// 'y' coordinate value increases towards the bottom of the array,
			// 'x' coordinate value increases towards the right of the array

			if (tempY - 1 >= 0) // check if there are any cells above 'testedCell'
				aboveSearchable = true; // for tempY = 1
			else
				aboveSearchable = false;

			if (tempY + 1 < tempArray.length) // check if there are any cells below 'testedCell'
				belowSearchable = true; // for tempY = tempArray.length - 2
			else
				belowSearchable = false;

			for (int tempX = 0; tempX < tempArray[tempY].length; tempX++) {

				testedCell = tempArray[tempY][tempX];

				if (tempX - 1 >= 0) // check if there are any cells left to the 'testedCell'
					leftSearchable = true; // for tempX = 1, leftSearchable
				else
					leftSearchable = false;

				if (tempX + 1 < tempArray[tempY].length) // check if there are any cells right to the 'testedCell'
					rightSearchable = true; // for tempX = tempArray.length - 2
				else
					rightSearchable = false;

				// boundary coordinates of the tested area around 'testedCell'
				lifeInNeighborhood = 0;
				if (aboveSearchable)
					startVertical = tempY - 1;
				else
					startVertical = tempY;
				if (belowSearchable)
					endVertical = tempY + 1;
				else
					endVertical = tempY;
				if (leftSearchable)
					startHorizontal = tempX - 1;
				else
					startHorizontal = tempX;
				if (rightSearchable)
					endHorizontal = tempX + 1;
				else
					endHorizontal = tempX;

				// Life research around the 'testedCell'
				for (int vertical = startVertical; vertical <= endVertical; vertical++)
					for (int horizontal = startHorizontal; horizontal <= endHorizontal; horizontal++)
						lifeInNeighborhood += tempArray[vertical][horizontal];

				// 'lifeInNeighborhood' number of LIVE cells includes 'testedCell' value,
				// as well
				lifeInNeighborhood -= testedCell; // if 'testedCell' is LIVE, the value is reduced by '1'

				if (testedCell == LIVE && (lifeInNeighborhood < UNDERPOPULATED 
						|| lifeInNeighborhood > OVERCROWDED))
					nextGeneration[tempY][tempX] = DEAD;
				else if (testedCell == DEAD && lifeInNeighborhood == NEIGHBORHOODREVIVE)
					nextGeneration[tempY][tempX] = LIVE;
				else
					nextGeneration[tempY][tempX] = testedCell;
			}
		}
		nextGeneration = cropAroundLiveCells(nextGeneration); // crops temporary array

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

		for (int tempY = 0; tempY < cells.length; tempY++)
			for (int tempX = 0; tempX < cells[tempY].length; tempX++)
				if (cells[tempY][tempX] == 1) {

					if (tempY < uppermost)
						uppermost = tempY; // new upper limit coordinate of 'cropped' array

					if (tempY > lowest)
						lowest = tempY; // new lower limit coordinate of 'cropped' array

					if (tempX < maxLeft)
						maxLeft = tempX; // new left limit coordinate of 'cropped' array

					if (tempX > maxRight)
						maxRight = tempX; // new right limit coordinate of 'cropped' array
				}

		int[][] cropped = new int[lowest - uppermost + 1][maxRight - maxLeft + 1];
		for (int y = uppermost; y <= lowest; y++)
			for (int x = maxLeft; x <= maxRight; x++)
				cropped[y - uppermost][x - maxLeft] = cells[y][x];
		return cropped;
	}

	public static void main(String[] args) {

		GraphSimulation.clearScreen();
		int iterations = 22;
		int[][] startGlider = { { 1, 0, 0 }, { 0, 1, 1 }, { 1, 1, 0 } };
		for (int i = 0; i < iterations; i++) {
			int[][] animedGlider = getGeneration(startGlider, i);
			System.out.println("\n" + GraphSimulation.htmlize(animedGlider));
			GraphSimulation.pause(1000);
			GraphSimulation.clearScreen();
		}
	}
}
