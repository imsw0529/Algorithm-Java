package study.week6;

import java.io.*;
import java.util.*;

public class boj_14499_LimSW {
	static final int[] dx = {0, 0, -1, 1}, dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// 주사위
		// x, y, z 축으로 처리
		// x축: 0 앞뒤, y축: 1 좌우, z축: 2 상하
		int[][] dice = new int[3][2];
		int[][] map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int k = 0; k < K; k++) {
			int d = Integer.parseInt(st.nextToken())-1;
			int nx = x + dx[d], ny = y + dy[d];
			if (!(0 <= nx && nx < N && 0 <= ny && ny < M))
				continue;
			x = nx; y = ny;
			rotate(dice, d);
			if (map[x][y] == 0) {
				map[x][y] = dice[2][0];
			} else {
				dice[2][0] = map[x][y];
				map[x][y] = 0;
			}
			sb.append(dice[2][1]).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	
	static void swap(int[][] dice, int a1, int a2, int b1, int b2) {
		dice[a1][a2] ^= dice[b1][b2];
		dice[b1][b2] ^= dice[a1][a2];
		dice[a1][a2] ^= dice[b1][b2];
	}
	
	static void rotate(int[][] dice, int d) {
		switch(d) {
		// 동
		case 0:
			swap(dice, 1, 1, 2, 0);
			swap(dice, 1, 1, 2, 1);
			swap(dice, 1, 0, 2, 1);
			break;
		// 서
		case 1:
			swap(dice, 1, 0, 2, 1);
			swap(dice, 1, 1, 2, 1);
			swap(dice, 1, 1, 2, 0);
			break;
		// 북
		case 2:
			swap(dice, 0, 0, 2, 0);
			swap(dice, 0, 0, 2, 1);
			swap(dice, 0, 1, 2, 1);
			break;
		// 남
		case 3:
			swap(dice, 0, 1, 2, 1);
			swap(dice, 0, 0, 2, 1);
			swap(dice, 0, 0, 2, 0);
			break;
		}
	}
}
