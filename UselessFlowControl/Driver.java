/**
 * @author Timotej Kovacka
 * @GUID 2478424k
 * 
 */
package detectors;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitor;

public class Driver {
	
	public static void main(String args[]) {
		try {
			JavaParser.getStaticConfiguration().setAttributeComments(false);
			CompilationUnit cu = JavaParser.parse(new FileInputStream(args[0]));
			
			VoidVisitor<List<Breakpoints>> statementVisitor = new UselessControlFlowDetector();
			VoidVisitor<List<Breakpoints>> recursionVisitor = new RecursionDetector();
			List<Breakpoints> controlFlowList = new ArrayList<Breakpoints>();
			List<Breakpoints> recursionList = new ArrayList<Breakpoints>();
			
			statementVisitor.visit(cu, controlFlowList);
			recursionVisitor.visit(cu, recursionList);
			
			System.out.println("UselessControlFlow");
			controlFlowList.forEach(x->{
				System.out.println(x.toString());
			});
			
			System.out.println("RecursionMethods");
			recursionList.forEach(x->{
				System.out.println(x.toString());
			});
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
