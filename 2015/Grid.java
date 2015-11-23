package competition2015;

import java.util.*;

public class Grid {
	
	// Class for holding the points to search
	public static class Point {
		int x, y, depth, jump;
		
		public Point(int xx, int yy, int j, int d) {
			x = xx;
			y = yy;
			jump = j;
			depth = d;
		}
	}
	
	public static void main(String... args) {
		// Read the grid into an array of integers
		
		// --------- All of this is just for reading in the grid ---------
		Scanner s = new Scanner(System.in);
		final int N = s.nextInt(), M = s.nextInt();
		s.nextLine();
		
		int[][] grid = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			char[] c = s.nextLine().toCharArray();
			for (int j = 0; j < c.length; j++) {
				grid[i][j] = c[j] - '0';
			}
		}
		
		s.close();
		// --------- End of the reading section ---------
		
		// Now that the grid is loaded, we can run our BFS algorithm
		
		// Deque to hold the points we want to search
		Deque<Point> toSearch = new ArrayDeque<>();
		toSearch.addLast(new Point(0, 0, grid[0][0], 0));
		
		// Set to keep track of points we've already visited, as strings formatted as "x y"
		Set<String> visited = new HashSet<>();
		
		// This is a reader-friendly way to store our search parameters
		int[] x_search = new int[] { -1, 1, 0, 0 };
		int[] y_search = new int[] { 0, 0, -1, 1 };
		
		while (!toSearch.isEmpty()) {
			
			// Breadth-first search
			Point p = toSearch.removeFirst();
			
			// Check new points. If the point is within bounds and unvisited, add it
			for (int i = 0; i < 4; i++) {
				int n_x = p.x + x_search[i] * p.jump, n_y = p.y + y_search[i] * p.jump;
				
				// Check if we've found the ending point
				if (n_x == N - 1 && n_y == M - 1) {
					System.out.println(p.depth+1);
					return;
				}
				
				// This is how points are stored in the "visited" set
				String hash = n_x + " " + n_y;
				
				// Make sure the point is within bounds and has not yet been visited
				if (n_x < 0 || n_x >= N || n_y < 0 || n_y >= M || visited.contains(hash)) {
					continue;
				}
				
				toSearch.addLast(new Point(n_x, n_y, grid[n_x][n_y], p.depth + 1));
				visited.add(hash);
			}
		}
		
		// If we get here that means the point wasn't found
		System.out.println(-1);
	}
}
