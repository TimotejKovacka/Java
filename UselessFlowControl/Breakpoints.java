/**
 * @author Timotej Kovacka
 * @GUID 2478424k
 * 
 */
package detectors;

import java.util.List;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class Breakpoints {
	private String className;
	private String methodName;
	private int start;
	private int end;
	
	
	public Breakpoints (String className, String methodName, int start, int end) {
		this.className=className;
		this.methodName=methodName;
		this.start=start;
		this.end=end;
	}
	
	@Override
	public String toString () {
		return "className="+className+",methodName="+methodName+",startLine="+start+",endLine="+end;
	}
}
