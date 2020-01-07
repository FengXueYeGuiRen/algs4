package edu.princeton.cs.algs4.zh.cn.graph.directed;

import java.util.Stack;

/**
 * 寻找有向环(有向图中的环 4.2.4.2)
 * {@link edu.princeton.cs.algs4.DirectedCycle}
 *
 * @author FXYGR @date 2020-01-07
 */
public class DefaultDirectedCycle extends DirectedCycle {

	private boolean[] markeds;
	private int[] edgeTos;
	/**
	 * 递归调用的栈上的所有顶点
	 */
	private boolean[] onStack;
	/**
	 * 有向环中的多有顶点(如果存在)
	 */
	private Stack<Integer> cycle;

	/**
	 * 寻找有向环的构造函数
	 *
	 * @param G
	 */
	public DefaultDirectedCycle(DirectedGraph G) {
		markeds = new boolean[G.V()];
		edgeTos = new int[G.V()];
		onStack = new boolean[G.V()];

		for (int v = 0; v < G.V(); ++v) {
			if (!markeds[v] && !hasCycle()) {
				dfs(G, v);
			}
		}
	}

	private void dfs(DirectedGraph G, int v) {
		onStack[v] = true;
		markeds[v] = true;

		for (int w : G.adj(v)) {
			if (hasCycle()) {
				break;
			}
			if (!markeds[w]) {
				edgeTos[w] = v;
				dfs(G, w);

				continue;
			}
			//  markeds[w] == true
			if (onStack[w]) {
				cycle = new Stack();
				for (int x = v; x != w; x = edgeTos[x]) {
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
			}
		}
		onStack[v] = false;
	}

	/**
	 * G 是否含有有向环
	 *
	 * @return
	 */
	@Override
	public boolean hasCycle() {
		return cycle != null;
	}

	/**
	 * 有向环中的所有顶点(如果存在的话)
	 *
	 * @return
	 */
	@Override
	public Iterable<Integer> cycle() {
		return cycle;
	}

}
