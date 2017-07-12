import java.util.Random;

public class MoveLeftTest {
	/**
	 * Perform a move in direction left on row blocks[i].
	 * Return scores gained in this movement (-1 if no movement is made).
	 **/
	public int moveLeft(int[][] blocks, int i) {
		int size = blocks[i].length; // in typical case: 4
		int score = 0;
		/* blocks[i][0..h-1] has its final value and cannot be changed again */
		int h = 0;
		boolean moveMade = false;
		/*
		 * loop invariant: 
		 * |-------row: blocks[i]--------|
		 * |######<x>????????????????????|
		 * |0------h---j-------------size| 
		 * #: have their final values and cannot be changed 
		 * <x>: the current value to be finalized (still mergable) 
		 * ?: still waiting to be finalized
		 * h: index of the current position to be finalized
		 * j: index of the next value which is not 0 after block[h]
		 */
		for (int j = 1; j < size; j++) {
			if (blocks[i][j] == 0)
				continue; /* skip blanks */
			if (blocks[i][j] == blocks[i][h]) {
				score += (blocks[i][h++] += blocks[i][j]);
				/*
				 * merge with the previous same value: 
				 * <x>, <0>, ..., <x>, ... --> (<x> + <x>), <0>, ..., <0>, ...
				 */
				moveMade = true;
				blocks[i][j] = 0;
			} else {
				/*
				 * different value, so that the value x at h already has its final value
				 */
				if (blocks[i][h] == 0) {
					blocks[i][h] = blocks[i][j];
					/*
					 * in this case, <0>, <0>, ..., <y>, ... --> <y>, <0>, ... <0>, ...
					 */
					blocks[i][j] = 0;
				} else {
					blocks[i][++h] = blocks[i][j];
					/*
					 * in this case, <x>, <0>, ..., <y>, ... --> <x>, <y>, ..., <0>, ...
					 */
					if (h != j) {
						moveMade = true;
						blocks[i][j] = 0;
					}
					/* if h == j, nothing is changed */
				}
			}
		}
		return moveMade ? score : -1;
	}

	public static void main(String[] args) {
		/* A simple test */
		MoveLeftTest game = new MoveLeftTest();
		int[] array={0,2,4,8,16};
		Random random=new Random();
		int[][] grid = new int[][] { new int[] 
				{ array[random.nextInt(5)], array[random.nextInt(5)], array[random.nextInt(5)], array[random.nextInt(5)]} };
		System.out.println("Before moving left, the array is: ");
		for (int j = 0; j < grid[0].length; j++)
			System.out.printf("%d\t", grid[0][j]);
		int score=0;
		score=game.moveLeft(grid, 0);
		System.out.println("\nAfter moving left, the array is:");
		for (int j = 0; j < grid[0].length; j++)
			System.out.printf("%d\t", grid[0][j]);
		System.out.printf("\nScore is %d\n", score);
	}
}
