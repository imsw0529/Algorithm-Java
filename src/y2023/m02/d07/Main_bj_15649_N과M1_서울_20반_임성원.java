package y2023.m02.d07;

import java.io.*;
import java.util.*;

public class Main_bj_15649_N과M1_서울_20반_임성원 {
	static int N, M;
	static int[] ans;
	static boolean[] v;
	static StringBuilder sb;
	
	static void perm(int cnt, int size) {
		if (cnt == size) {
			for (int a : ans) {
				sb.append(a).append(" ");
			}
			sb.setLength(sb.length()-1);
			sb.append("\n");
			return;
		}
		for (int i = 0; i < N; i++) {
			if (v[i]) continue;
			v[i] = true;
			ans[cnt] = i+1;
			perm(cnt+1, size);
			v[i] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ans = new int[M];
		v = new boolean[N];
		perm(0, M);
		System.out.print(sb.toString());
	}
}
