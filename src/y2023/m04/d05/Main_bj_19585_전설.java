package y2023.m04.d05;

import java.io.*;
import java.util.*;

public class Main_bj_19585_전설 {
	static class Node {
		boolean end;
		Node[] child = new Node['z'-'a'+1];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		Node color = new Node();
		Node nickn = new Node();
		for (int i = 0; i < C; i++)
			insert(color, br.readLine().toCharArray(), false);
		for (int i = 0; i < N; i++)
			insert(nickn, br.readLine().toCharArray(), true);

		int Q = Integer.parseInt(br.readLine());
		for (int i = 0; i < Q; i++) {
			char[] carr = br.readLine().toCharArray();
			if (find(color, nickn, carr))
				sb.append("Yes\n");
			else sb.append("No\n");
		}

		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}

	static void insert(Node root, char[] carr, boolean reverse) {
		Node node = root;
		for (int size = carr.length, i = 0; i < size; i++) {
			int idx = carr[reverse ? size-1-i : i]-'a';
			if (node.child[idx] == null)
				node.child[idx] = new Node();
			node = node.child[idx];
		}
		node.end = true;
	}

	static boolean find(Node color, Node nickn, char[] carr) {
		int size = carr.length;
		Node node = color;
		List<Integer> idx = new ArrayList<>();
		for (int i = 0; node != null;) {
			if (node.end) idx.add(i);
			if ( i == 1000 || i == size) break;
			node = node.child[carr[i++]-'a'];
		}
		node = nickn;
		for (int i = 0; node != null;) {
			if (node.end) {
				if (Collections.binarySearch(idx, size-i) >= 0)
					return true;
			};
			if ( i == 1000 || i == size) break;
			node = node.child[carr[size-1-(i++)]-'a'];
		}
		return false;
	}
}
