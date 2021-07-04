/**
 * @author Timotej Kovacka
 * @GUID 2478424k
 * 
 */
package detectors;

import java.util.List;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class RecursionDetector extends VoidVisitorAdapter <List<Breakpoints>>{
	private String methodName;
	private String className;
	
	@Override
	public void visit(ClassOrInterfaceDeclaration cid, List<Breakpoints> collector) {
		this.className=cid.getName().toString();
		super.visit(cid, collector);
	}
	
	@Override
	public void visit(MethodDeclaration md, List<Breakpoints> collector) {
		this.methodName=md.getName().asString();
		super.visit(md, collector);
	}
	
	@Override
	public void visit(MethodCallExpr mce, List<Breakpoints> collector) {
		if(mce.getName().asString().equals(this.methodName)) {
			Node lookUpNode= mce;
			do {
				lookUpNode=lookUpNode.getParentNode().get();
			}while(!(lookUpNode instanceof MethodDeclaration));
			collector.add(new Breakpoints(this.className,this.methodName,lookUpNode.getRange().get().begin.line,lookUpNode.getRange().get().end.line));
		}
	}
}
