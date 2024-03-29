package y2023.m04.d18;

import java.io.*;
import java.util.*;

public class Main_bj_1781_컵라면 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<int[]> pq = new PriorityQueue<>(N, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				int r = Integer.compare(o1[0], o2[0]);
				if (r == 0) r = -Integer.compare(o1[1], o2[1]);
				return r;
			}
		});
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			pq.offer(new int[] {a, b});
		}
		PriorityQueue<int[]> sol = new PriorityQueue<>(N, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1], o2[1]);
			}
		});
		for (int t = 1; !pq.isEmpty(); t++) {
			while (!pq.isEmpty() && pq.peek()[0] < t) {
				if (!sol.isEmpty() && pq.peek()[1] > sol.peek()[1]) {
					sol.poll();
					sol.offer(pq.poll());
				} else {
					pq.poll();
				}
			}
			if (pq.isEmpty()) break;
			sol.offer(pq.poll());
		}
		int ans = 0;
		while (!sol.isEmpty())
			ans += sol.poll()[1];
		System.out.println(ans);
		br.close();
	}
}
