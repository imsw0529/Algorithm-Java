package y2023.m02.d22;

import java.io.*;
import java.util.*;

public class Main_bj_20058_마법사상어와파이어스톰 {
	static final int[] di = {-1, 1, 0, 0}, dj = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = 1 << Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		int ice = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				ice += map[i][j];
			}
		}
		Deque<int[]> q = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine(), " ");
		//
		for (int k = 0; k < Q; k++) {
			int L = 1 << Integer.parseInt(st.nextToken());
			// 회전
			for (int i = 0; i < N; i+=L) {
				for (int j = 0; j < N; j+=L) {
					rotate(map, i, j, L);
				}
			}
			// 얼음
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 0) continue;
					int cnt = 0;
					for (int d = 0; d < 4; d++) {
						int ni = i + di[d], nj = j + dj[d];
						if (!(0 <= ni && ni < N && 0 <= nj && nj < N)) continue;
						if (map[ni][nj] != 0) cnt++;
					}
					if (cnt < 3) q.offer(new int[] {i, j});
				}
			}
			while(!q.isEmpty()) {
				int[] ij = q.poll();
				int i = ij[0], j = ij[1];
				map[i][j]--;
				ice--;
			}
		}
		// 덩어리
		int size = 0;
		boolean[][] v = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (v[i][j] || map[i][j] == 0) continue;
				int tmp = bfs(i, j, N, map, v);
				if (size < tmp) size = tmp;
			}
		}
		System.out.println(ice);
		System.out.println(size);
		br.close();
	}
	
	static void rotate(int[][] map, int i, int j, int L) {
		L--;
		for (int r = 0; r < L; r++) {
			for (int c = r; c < L-r; c++) {
				int tmp = map[i+r][j+c];
				map[i+r][j+c] = map[i+L-c][j+r];
				map[i+L-c][j+r] = map[i+L-r][j+L-c];
				map[i+L-r][j+L-c] = map[i+c][j+L-r];
				map[i+c][j+L-r] = tmp; // map[i+r][j+c];
			}
		}
	}
	
	static int dfs(int i, int j, int N, int[][] map, boolean[][] v) {
		int res = 1;
		v[i][j] = true;
		for (int d = 0; d < 4; d++) {
			int ni = i + di[d], nj = j + dj[d];
			if (!(0 <= ni && ni < N && 0 <= nj && nj < N)) continue;
			if (v[ni][nj] || map[ni][nj] == 0) continue;
			res += dfs(ni, nj, N, map, v);
		}
		return res;
	}
	
	static int bfs(int i, int j, int N, int[][] map, boolean[][] v) {
		int res = 1;
		Deque<int[]> q = new ArrayDeque<>();
		v[i][j] = true;
		q.offer(new int[] {i, j});
		while (!q.isEmpty()) {
			int[] ij = q.poll();
			i = ij[0]; j = ij[1];
			for (int d = 0; d < 4; d++) {
				int ni = i + di[d], nj = j + dj[d];
				if (!(0 <= ni && ni < N && 0 <= nj && nj < N)) continue;
				if (v[ni][nj] || map[ni][nj] == 0) continue;
				v[ni][nj] = true;
				res++;
				q.offer(new int[] {ni, nj});
			}
		}
		return res;
	}
}
