package edu.princeton.cs.algs4.zh.cn.graph.directed;

/**
 * 拓扑排序(4.2.4.3 算法4.5)
 * {@link edu.princeton.cs.algs4.Topological}
 *
 * @author FXYGR @date 2020-01-08
 */
public class TopologicalSort extends Topological {

	/**
	 * 顶点的拓扑顺序
	 */
	private Iterable<Integer> order;

	/**
	 * 拓扑排序的构造函数
	 *
	 * @param G
	 */
	public TopologicalSort(DirectedGraph G) {
		DirectedCycle directedCycle = new DefaultDirectedCycle(G);
		if (directedCycle.hasCycle()) {
			return;
		}
		DepthFirstSearchVertexOrder depthFirstOrder =
				new DepthFirstSearchVertexOrder(G);
		order = depthFirstOrder.reversePost();
	}

	/**
	 * G 是有向无环图吗
	 *
	 * @return
	 */
	@Override
	public boolean isDAG() {
		return order != null;
	}

	/**
	 * 拓扑有序的所有顶点
	 *
	 * @return
	 */
	@Override
	public Iterable<Integer> order() {
		return order;
	}

}
