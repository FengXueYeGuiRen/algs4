package edu.princeton.cs.algs4.zh.cn.graph.directed;

import edu.princeton.cs.algs4.zh.cn.graph.DirectedGraph;
import edu.princeton.cs.algs4.zh.cn.graph.StronglyConnectedComponent;

/**
 * 计算强连通分量的 Kosaraju 算法(4.2.5.3 算法4.6)
 * {@link edu.princeton.cs.algs4.KosarajuSharirSCC}
 * {@link edu.princeton.cs.algs4.zh.cn.graph.undirected.DepthFirstConnectedComponent}
 *
 * @author FXYGR @date 2020-01-09
 */
public class KosarajuStronglyConnectedComponent extends StronglyConnectedComponent {

	/**
	 * 已访问过的顶点
	 */
	private boolean[] markeds;
	/**
	 * 强连通分量的数量
	 */
	private int count;
	/**
	 * 强连通分量的标识符
	 */
	private int[] ids;

	/**
	 * 预处理构造函数
	 *
	 * @param G
	 */
	public KosarajuStronglyConnectedComponent(DirectedGraph G) {
		markeds = new boolean[G.V()];
		ids = new int[G.V()];

		DepthFirstSearchVertexOrder reverseDepthFirstOrder =
				new DepthFirstSearchVertexOrder(G.reverse());

		for (int v : reverseDepthFirstOrder.reversePost()) {
			if (!markeds[v]) {
				dfs(G, v);
				++count;
			}
		}
	}

	private void dfs(DirectedGraph G, int v) {
		markeds[v] = true;
		ids[v] = count;

		for (int w : G.adj(v)) {
			if (!markeds[w]) {
				dfs(G, w);
			}
		}
	}

	/**
	 * v 和 w 是强连通的吗？
	 *
	 * @param v
	 * @param w
	 * @return
	 */
	@Override
	public boolean stronglyConnected(int v, int w) {
		if (v < 0 || v >= ids.length
				|| w < 0 || w >= ids.length) {
			return false;
		}
		return ids[v] == ids[w];
	}

	/**
	 * 图中的强连通分量的总数
	 *
	 * @return
	 */
	@Override
	public int count() {
		return count;
	}

	/**
	 * v 所在的强连通分量的标识符(0 至 count()-1 之间)
	 *
	 * @param v
	 * @return
	 */
	@Override
	public int id(int v) {
		if (v < 0 || v >= ids.length) {
			return Integer.MIN_VALUE;
		}
		return ids[v];
	}

}
