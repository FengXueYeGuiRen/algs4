package edu.princeton.cs.algs4.zh.cn.graph.directed;

import edu.princeton.cs.algs4.Queue;

import java.util.Stack;

/**
 * 有向图中基于深度优先搜索的顶点排序(4.2.4.3)
 * {@link edu.princeton.cs.algs4.DepthFirstOrder}
 *
 * @author FXYGR @date 2020-01-08
 */
public class DepthFirstSearchVertexOrder {
	private boolean[] markeds;
	/**
	 * 所有顶点的前序排列
	 */
	private Queue<Integer> pre;
	/**
	 * 所有顶点的后序排列
	 */
	private Queue<Integer> post;
	/**
	 * 所有顶点的逆后序排列
	 */
	private Stack<Integer> reversePost;

	public DepthFirstSearchVertexOrder(DirectedGraph G) {
		markeds = new boolean[G.V()];

		pre = new Queue<>();
		post = new Queue<>();
		reversePost = new Stack<>();

		for (int v = 0; v < G.V(); ++v) {
			if (!markeds[v]) {
				dfs(G, v);
			}
		}
	}

	private void dfs(DirectedGraph G, int v) {
		markeds[v] = true;

		pre.enqueue(v);
		for (int w : G.adj(v)) {
			if (!markeds[w]) {
				dfs(G, w);
			}
		}
		post.enqueue(v);
		reversePost.push(v);
	}

	public Queue<Integer> pre() {
		return pre;
	}

	public Queue<Integer> post() {
		return post;
	}

	public Stack<Integer> reversePost() {
		return reversePost;
	}

}
