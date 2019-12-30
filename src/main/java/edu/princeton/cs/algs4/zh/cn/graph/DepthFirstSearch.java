package edu.princeton.cs.algs4.zh.cn.graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.zh.cn.graph.undirected.AdjacencyListsGraph;
import edu.princeton.cs.algs4.zh.cn.graph.undirected.Graph;

/**
 * 深度优先搜索
 *
 * @author FXYGR @date 2019-12-30
 */
public class DepthFirstSearch extends GraphSearch {

	private boolean[] marked;
	private int count;

	public DepthFirstSearch() {
	}

	/**
	 * 找到和起点 s 连通的所有顶点
	 *
	 * @param G
	 * @param s
	 */
	public DepthFirstSearch(Graph G, int s) {
		marked = new boolean[G.V()];
		dfs(G, s);
	}

	private void dfs(Graph G, int v) {
		marked[v] = true;
		++count;
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w);
			}
		}
	}

	/**
	 * v 和 s 是连通的吗
	 *
	 * @param v
	 * @return
	 */
	@Override
	public boolean marked(int v) {
		return marked[v];
	}

	/**
	 * 与 s 连通的顶点总数
	 *
	 * @return
	 */
	@Override
	public int count() {
		return count;
	}

	public static void main(String[] args) {
		Graph G = new AdjacencyListsGraph(new In(args[0]));
		int s = Integer.parseInt(args[1]);
		GraphSearch search = new DepthFirstSearch(G, s);

		for (int v = 0; v < G.V(); ++v) {
			if (search.marked(v)) {
				StdOut.print(v + " ");
			}
		}
		StdOut.println();
		if (search.count() != G.V()) {
			StdOut.print("NOT ");
		}
		StdOut.println("connected");
	}

}
