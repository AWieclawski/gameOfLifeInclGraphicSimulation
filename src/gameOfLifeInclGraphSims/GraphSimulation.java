package gameOfLifeInclGraphSims;

public class GraphSimulation {
	
	static int LIVE = 1;
	static int DEAD = 0;
	
	public static String htmlize(int[][] cells) {

		StringBuffer sb = new StringBuffer();
		int lenY = cells.length;
		int lenX = cells[0].length;

		for (int j = 0; j < lenY; j++) {
			for (int i = 0; i < lenX; i++) {

				int i1 = cells[j][i];

				if (i1 == LIVE) {
					sb.append("▓▓");

				} else if (i1 == DEAD) {
					sb.append("░░");
				}
			}
			sb.append("\n");
		}

		return sb.toString();
	}
	
	public static void clearScreen() {
		for (int clear = 0; clear < 55; clear++) {
			System.out.println("");
		}
	}

	public static void pause(int milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
