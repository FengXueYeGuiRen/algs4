package edu.princeton.cs.algs4.zh.cn.graph.undirected;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.zh.cn.searching.SeparateChainingHashSymbolTable;
import edu.princeton.cs.algs4.zh.cn.searching.SymbolTable;

/**
 * 符号图(用符号做为顶点名的图)数据类型(4.1.7)
 * {@link edu.princeton.cs.algs4.SymbolGraph}
 *
 * @author FXYGR @date 2020-01-03
 */
public class DefaultSymbolGraph extends SymbolGraph {

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
	private Graph G;

	/**
	 * 根据 fileName 指定的文件构造图，使用 delim 来分割顶点名
	 *
	 * @param fileName 图文件
	 * @param delim    分割符
	 */
	public DefaultSymbolGraph(String fileName, String delim) {
		//  基于拉链法的散列表(3.4.2 算法3.5)
		symbolTable = new SeparateChainingHashSymbolTable();
		//  第一遍
		In in = new In(fileName);
		//  构造索引
		while (in.hasNextLine()) {
			//  读取字符串
			String[] a = in.readLine().split(delim);
			//  为每个不同的字符串关联一个索引
			for (int i = 0; i < a.length; ++i) {
				if (!symbolTable.contains(a[i])) {
					symbolTable.put(a[i], i);
				}
			}
		}
		//  用来获得顶点名的反向索引是一个数组
		keys = new String[symbolTable.size()];
		for (String name : symbolTable.keys()) {
			keys[symbolTable.get(name)] = name;
		}
		G = new AdjacencyListsGraph(symbolTable.size());
		//  第二遍
		in = new In(fileName);
		//  构造图
		while (in.hasNextLine()) {
			//  将每一行的第一个顶点和该行的其他顶点相连
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
	public Graph G() {
		return G;
	}

	public static void main(String[] args) {
		String fileName = args[0];
		String delim = args[1];

		SymbolGraph symbolGraph = new DefaultSymbolGraph(fileName, delim);

		Graph G = symbolGraph.G();

		while (StdIn.hasNextLine()) {
			String source = StdIn.readLine();
			StdOut.print(source + ":");
			for (int v : G.adj(symbolGraph.index(source))) {
				StdOut.println(" " + symbolGraph.name(v));
			}
		}
	}

}
