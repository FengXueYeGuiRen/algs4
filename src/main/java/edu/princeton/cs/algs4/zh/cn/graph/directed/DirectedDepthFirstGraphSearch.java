package edu.princeton.cs.algs4.zh.cn.graph.directed;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.zh.cn.graph.DepthFirstGraphSearch;

/**
 * 有向图的可达性(4.2.3 算法4.4)
 * {@link edu.princeton.cs.algs4.DirectedDFS}
 *
 * @author FXYGR @date 2020-01-06
 */
public class DirectedDepthFirstGraphSearch extends DepthFirstGraphSearch {

	private boolean[] markeds;
	private int count;

	public DirectedDepthFirstGraphSearch() {
	}

	/**
	 * 在 G 中找到从 s 可达的所有顶点
	 *
	 * @param G
	 * @param s
	 */
	public DirectedDepthFirstGraphSearch(DirectedGraph G, int s) {
		markeds = new boolean[G.V()];
		if (s < 0 || s >= markeds.length) {
			throw new IllegalArgumentException(
					"vertex " + s + " is not between 0 and " + (markeds.length - 1));
		}
		dfs(G, s);
	}

	/**
	 * 在 G 中找到从 sources 中的所有顶点可达的所有顶点
	 *
	 * @param G
	 * @param sources
	 */
	public DirectedDepthFirstGraphSearch(DirectedGraph G,
	                                     Iterable<Integer> sources) {
		markeds = new boolean[G.V()];
		for (int s : sources) {
			if (!markeds[s]) {
				dfs(G, s);
			}
		}
	}

	private void dfs(DirectedGraph G, int v) {
		markeds[v] = true;
		++count;
		for (int w : G.adj(v)) {
			if (!markeds[w]) {
				dfs(G, w);
			}
		}
	}

	/**
	 * v 从 s 是可达的吗
	 *
	 * @param v
	 * @return
	 */
	@Override
	public boolean marked(int v) {
		if (v < 0 || v >= markeds.length) {
			return false;
		}
		return markeds[v];
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
		DirectedGraph graph = new DefaultDirectedGraph(new In(args[0]));

		Bag<Integer> sources = new Bag();
		for (int i = 1; i < args.length; ++i) {
			sources.add(Integer.parseInt(args[i]));
		}
		DirectedDepthFirstGraphSearch reachable =
				new DirectedDepthFirstGraphSearch(graph, sources);
		for (int v = 0; v < graph.V(); ++v) {
			if (reachable.markeds[v]) {
				StdOut.print(v + " ");
			}
			StdOut.println();
		}
	}

}
