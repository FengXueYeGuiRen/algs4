package edu.princeton.cs.algs4.zh.cn.graph.undirected;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.zh.cn.graph.Graph;
import edu.princeton.cs.algs4.zh.cn.graph.GraphPaths;

import java.util.Collections;
import java.util.Iterator;

/**
 * 使用深度优先搜索查找途中的路径(寻找路径 4.1.4 算法4.1)
 * {@link edu.princeton.cs.algs4.DepthFirstPaths}
 *
 * @author FXYGR @date 2019-12-31
 */
public class DepthFirstGraphPaths extends GraphPaths {

	private static final int INFINITY = Integer.MAX_VALUE;
	/**
	 * 起点
	 */
	private int s;
	/**
	 * 这个顶点上调用过dfs()了吗？
	 */
	private boolean[] marked;
	/**
	 * 从起点到一个顶点的已知路径上的最后一个顶点
	 */
	private int[] edgeTo;

	/**
	 * 在 G 中找出所有起点为 s 的路径
	 *
	 * @param G
	 * @param s
	 */
	public DepthFirstGraphPaths(Graph G, int s) {
		if (s < 0 || s >= G.V()) {
			throw new IllegalArgumentException(
					"vertex " + s + " is not between 0 and " + (G.V() - 1));
		}
		this.s = s;
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		for (int v = 0; v < G.V(); ++v) {
			edgeTo[v] = INFINITY;
		}
		dfs(G, s);
	}

	private void dfs(Graph G, int v) {
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			}
		}
	}

	/**
	 * 是否存在从 s 到 v 的路径
	 *
	 * @param v
	 * @return
	 */
	@Override
	public boolean hasPathTo(int v) {
		if (s < 0 || s >= marked.length) {
			return false;
		}
		return marked[v];
	}

	/**
	 * s 到 v 的路径，如果不存在则返回 emptyIterable
	 *
	 * @param v
	 * @return
	 */
	@Override
	public Iterable<Integer> pathTo(int v) {
		if (!hasPathTo(v)) {
			return emptyIterable();
		}
		Stack<Integer> path = new Stack();
		for (int x = v; x != s; x = edgeTo[x]) {
			path.push(x);
		}
		path.push(s);
		return path;
	}

	private static Iterable<Integer> emptyIterable() {
		return new Iterable<Integer>() {
			@Override
			public Iterator<Integer> iterator() {
				return Collections.emptyIterator();
			}
		};
	}

	public static void main(String[] args) {
		AdjacencyListsGraph graph = new AdjacencyListsGraph(new In(args[0]));
		int s = Integer.parseInt(args[1]);

		GraphPaths paths = new DepthFirstGraphPaths(graph, s);
		for (int v = 0; v < graph.V(); ++v) {
			StdOut.print(s + " to " + v + ": ");
			if (!paths.hasPathTo(v)) {
				continue;
			}
			for (int w : paths.pathTo(v)) {
				if (w == s) {
					StdOut.print(w);
				} else {
					StdOut.print("-" + w);
				}
			}
			StdOut.println();
		}
	}

}
