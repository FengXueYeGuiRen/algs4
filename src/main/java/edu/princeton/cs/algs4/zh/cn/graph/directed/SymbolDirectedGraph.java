package edu.princeton.cs.algs4.zh.cn.graph.directed;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.zh.cn.graph.SymbolGraph;
import edu.princeton.cs.algs4.zh.cn.searching.SeparateChainingHashSymbolTable;
import edu.princeton.cs.algs4.zh.cn.searching.SymbolTable;

/**
 * 符号有向图(用符号做为顶点名的有向图)
 * {@link edu.princeton.cs.algs4.SymbolDigraph}
 * {@link edu.princeton.cs.algs4.zh.cn.graph.undirected.SymbolUndirectedGraph}
 *
 * @author FXYGR @date 2020-01-08
 */
public class SymbolDirectedGraph extends SymbolGraph {

	/**
	 * 符号名 -> 索引
	 */
	private SymbolTable<String, Integer> symbolTable;
	/**
	 * 索引 -> 符号名
	 */
	private String[] keys;
	/**
	 * 无向图表示
	 */
	private DirectedGraph G;

	/**
	 * 根据 fileName 指定的文件构造图，使用 delim 来分割顶点名
	 *
	 * @param fileName 图文件
	 * @param delim    分割符
	 */
	public SymbolDirectedGraph(String fileName, String delim) {
		//  基于拉链法的散列表(3.4.2 算法3.5)
		symbolTable = new SeparateChainingHashSymbolTable();

		In in = new In(fileName);
		while (in.hasNextLine()) {
			String[] a = in.readLine().split(delim);
			for (int i = 0; i < a.length; ++i) {
				if (!contains(a[i])) {
					symbolTable.put(a[i], symbolTable.size());
				}
			}
		}
		keys = new String[symbolTable.size()];
		for (String name : symbolTable.keys()) {
			keys[symbolTable.get(name)] = name;
		}
		G = new DefaultDirectedGraph(symbolTable.size());
		in = new In(fileName);
		while (in.hasNextLine()) {
			String[] a = in.readLine().split(delim);
			int v = symbolTable.get(a[0]);
			for (int i = 1; i < a.length; ++i) {
				G.addEdge(v, symbolTable.get(a[i]));
			}
		}
	}

	/**
	 * key 是一个顶点吗
	 *
	 * @param key
	 * @return
	 */
	@Override
	public boolean contains(String key) {
		return symbolTable.contains(key);
	}

	/**
	 * key 的索引
	 *
	 * @param key
	 * @return
	 */
	@Override
	public int index(String key) {
		if (key == null || key.trim().equals("")) {
			return Integer.MIN_VALUE;
		}
		Integer index = symbolTable.get(key.trim());
		return index == null ? Integer.MIN_VALUE : index;
	}

	/**
	 * 索引 v 的顶点名
	 *
	 * @param v
	 * @return
	 */
	@Override
	public String name(int v) {
		if (v < 0 || v >= keys.length) {
			return "";
		}
		return keys[v];
	}

	/**
	 * 隐藏的 Graph 对象
	 *
	 * @return
	 */
	@Override
	public DirectedGraph G() {
		return G;
	}

}
