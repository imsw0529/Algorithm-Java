package y2023.m02.d15;

import java.io.*;
import java.util.*;

public class Main_bj_2632_피자판매 {
	static int M, N, S, ans;
	static int[] A, B;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int S = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] A = new int[2*M+1]; // 원형
		int[] B = new int[2*N+1];
		
		for (int i = 1; i <= M; i++) {
			A[i] = Integer.parseInt(br.readLine());
			A[M+i] = A[i];
		}
		for (int i = 1; i <= 2*M; i++)
			A[i] += A[i-1];
		for (int i = 1; i <= N; i++) {
			B[i] = Integer.parseInt(br.readLine());
			B[N+i] = B[i];
		}
		for (int i = 1; i <= 2*N; i++)
			B[i] += B[i-1];
		
		Map<Integer, Integer> Acomb = new HashMap<>();
		Map<Integer, Integer> Bcomb = new HashMap<>();
		Acomb.put(0, 1); Bcomb.put(0, 1);
		if (A[M] <= S) Acomb.put(A[M], 1);
		if (B[N] <= S) Bcomb.put(B[N], 1);
		for (int i = M+1; i <= 2*M; i++) {
			int p = Arrays.binarySearch(A, A[i]-S);
			if (p < 0) p = ~p;
			if (p <= i-M) p = i-M+1;
			for (int j = p; j < i; j++)
				Acomb.put(A[i]-A[j], Acomb.getOrDefault(A[i]-A[j], 0)+1);
		}
		for (int i = N+1; i <= 2*N; i++) {
			int p = Arrays.binarySearch(B, B[i]-S);
			if (p < 0) p = ~p;
			if (p <= i-N) p = i-N+1;
			for (int j = p; j < i; j++)
				Bcomb.put(B[i]-B[j], Bcomb.getOrDefault(B[i]-B[j], 0)+1);
		}
		
		ans = 0;
		for (int key : Acomb.keySet()) {
			if (!Bcomb.containsKey(S-key)) continue;
			ans += Acomb.get(key) * Bcomb.get(S-key);
		}
		
		System.out.println(Arrays.toString(A));
		System.out.println(Acomb);
		System.out.println(Arrays.toString(B));
		System.out.println(Bcomb);
		System.out.println(ans);
	}
}
