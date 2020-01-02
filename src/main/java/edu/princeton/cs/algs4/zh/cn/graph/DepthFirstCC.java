package edu.princeton.cs.algs4.zh.cn.graph;

import edu.princeton.cs.algs4.zh.cn.graph.undirected.Graph;

/**
 * 使用深度优先搜索找出图中的所有连通分量(连通分量 4.1.6 算法4.3)
 * {@link edu.princeton.cs.algs4.CC}
 *
 * @author FXYGR @date 2020-01-02
 */
public class DepthFirstCC extends ConnectedComponents {

	private boolean[] markeds;
	private int count;
	private int[] ids;

	/**
	 * 预处理构造函数
	 *
	 * @param G
	 */
	public DepthFirstCC(Graph G) {
		markeds = new boolean[G.V()];
		ids = new int[G.V()];

		for (int v = 0; v < G.V(); ++v) {
			dfs(G, v);
			++count;
		}
	}

	private void dfs(Graph G, int v) {
		markeds[v] = true;
		ids[v] = count;
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

}
