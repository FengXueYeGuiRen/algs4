package edu.princeton.cs.algs4.zh.cn.graph.directed;

/**
 * 顶点对可达性(4.2.5.4)
 * {@link edu.princeton.cs.algs4.TransitiveClosure}
 *
 * @author FXYGR @date 2020-01-09
 */
public class Pairsreachability extends TransitiveClosure {

	private DirectedDepthFirstGraphSearch[] all;

	/**
	 * 预处理的构造函数
	 *
	 * @param G
	 */
	public Pairsreachability(DirectedGraph G) {
		all = new DirectedDepthFirstGraphSearch[G.V()];

		for (int v = 0; v < G.V(); ++v) {
			all[v] = new DirectedDepthFirstGraphSearch(G, v);
		}
	}

	/**
	 * w 是从 v 可达的吗
	 *
	 * @param v
	 * @param w
	 * @return
	 */
	@Override
	public boolean reachable(int v, int w) {
		if (v < 0 || v >= all.length
				|| w < 0 || w >= all.length) {
			return false;
		}
		return all[v].marked(w);
	}

}
