package graph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import alg.Result;

public class GraphWriter {

	final static String SUM = "SUM\t";
	final static String TIME = "TIME\t";
	
	public static void writeResultGraph(Result result, String fileName) {
		int[][] graph = result.getGraph();
		int sum = result.getSum();
		long nanoTime = result.getNanoTime();
		
		final File file = new File(fileName);
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			final FileWriter fw = new FileWriter(file.getAbsoluteFile());
			final BufferedWriter bw = new BufferedWriter(fw);
			bw.write(TIME + nanoTime + " ns\n");
			bw.write(SUM + sum + "\n");
			
			for(int aIdx = 0; aIdx < graph.length; ++ aIdx)
				for(int bIdx = 0; bIdx < graph.length; ++ bIdx)
					if(graph[aIdx][bIdx] > 0)
						bw.write((aIdx + 1) + "\t" + (bIdx + graph.length + 1) + "\t" + graph[aIdx][bIdx] + "\n");
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
