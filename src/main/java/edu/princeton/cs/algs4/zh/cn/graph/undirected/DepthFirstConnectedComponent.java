package edu.princeton.cs.algs4.zh.cn.graph.undirected;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.zh.cn.graph.ConnectedComponent;
import edu.princeton.cs.algs4.zh.cn.graph.Graph;

/**
 * 使用深度优先搜索找出图中的所有连通分量(连通分量 4.1.6 算法4.3)
 * {@link edu.princeton.cs.algs4.CC}
 *
 * @author FXYGR @date 2020-01-02
 */
public class DepthFirstConnectedComponent extends ConnectedComponent {

	private boolean[] markeds;
	private int count;
	private int[] ids;
	private int[] sizes;

	/**
	 * 预处理构造函数
	 *
	 * @param G
	 */
	public DepthFirstConnectedComponent(Graph G) {
		markeds = new boolean[G.V()];
		ids = new int[G.V()];
		sizes = new int[G.V()];

		for (int v = 0; v < G.V(); ++v) {
			if (!markeds[v]) {
				dfs(G, v);
				++count;
			}
		}
	}

	private void dfs(Graph G, int v) {
		markeds[v] = true;
		ids[v] = count;
		++sizes[count];
		for (int w : G.adj(v)) {
			if (!markeds[w]) {
				dfs(G, w);
			}
		}
	}

	/**
	 * v 和 w 连通吗？
	 *
	 * @param v
	 * @param w
	 * @return
	 */
	@Override
	public boolean connected(int v, int w) {
		if (v < 0 || v >= ids.length
				|| w < 0 || w >= ids.length) {
			return false;
		}
		return ids[v] == ids[w];
	}

	/**
	 * 连通分量数
	 *
	 * @return
	 */
	@Override
	public int count() {
		return count;
	}

	/**
	 * v 所在的连通分量的标识符(0 ~ count()-1)
	 *
	 * @param v
	 * @return
	 */
	@Override
	public int id(int v) {
		if (v < 0 || v >= ids.length) {
			return -1;
		}
		return ids[v];
	}

	public int size(int v) {
		if (v < 0 || v >= ids.length) {
			return 0;
		}
		return sizes[ids[v]];
	}

	public static void main(String[] args) {
		Graph graph = new AdjacencyListsGraph(new In(args[0]));

		DepthFirstConnectedComponent cc = new DepthFirstConnectedComponent(graph);

		int components = cc.count();
		StdOut.println(components + " components");
		Bag<Integer>[] bags = new Bag[components];
		for (int component = 0; component < components; ++component) {
			bags[component] = new Bag<>();
		}
		for (int v = 0; v < graph.V(); ++v) {
			bags[cc.id(v)].add(v);
		}
		for (Bag<Integer> bag : bags) {
			for (int v : bag) {
				StdOut.print(v + " ");
			}
			StdOut.println();
		}
	}

}
