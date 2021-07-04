/**
 * @author Timotej Kovacka
 * @GUID 2478424k
 * 
 */

package detectors;

import java.util.List;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.EmptyStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class UselessControlFlowDetector extends VoidVisitorAdapter <List<Breakpoints>> {
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
	public void visit(IfStmt is, List<Breakpoints> collector) {
		for (Node node : is.getChildNodes()) {
			if (node instanceof EmptyStmt || (node.getChildNodes().size()==1 && node.getChildNodes().get(0) instanceof EmptyStmt)){
				collector.add(new Breakpoints(this.className,this.methodName,is.getRange().get().begin.line,is.getRange().get().end.line));
			} else if (node.getChildNodes().isEmpty()) {
				collector.add(new Breakpoints(this.className,this.methodName,node.getRange().get().begin.line,node.getRange().get().end.line));
			}
		}
		super.visit(is, collector);
	}

	@Override
	public void visit(ForStmt fs, List<Breakpoints> collector) {
		for(Node node : fs.getChildNodes()) {
			if (node instanceof BlockStmt && (node.getChildNodes().isEmpty() || node.getChildNodes().size()==1 && node.getChildNodes().get(0) instanceof EmptyStmt)) {
				collector.add(new Breakpoints(this.className,this.methodName,fs.getRange().get().begin.line,fs.getRange().get().end.line));
			}
		}
		super.visit(fs, collector);
	}
	
	@Override
	public void visit(DoStmt ds, List<Breakpoints> collector) {
		for(Node node : ds.getChildNodes()) {
			if (node instanceof BlockStmt && (node.getChildNodes().isEmpty() || node.getChildNodes().size()==1 && node.getChildNodes().get(0) instanceof EmptyStmt)) {
				collector.add(new Breakpoints(this.className,this.methodName,ds.getRange().get().begin.line,ds.getRange().get().end.line));
			}
		}
		super.visit(ds, collector);
	}
	
	@Override
	public void visit(WhileStmt ws, List<Breakpoints> collector) {
		for(Node node : ws.getChildNodes()) {
			if (node instanceof BlockStmt && (node.getChildNodes().isEmpty() || node.getChildNodes().size()==1 && node.getChildNodes().get(0) instanceof EmptyStmt)) {
				collector.add(new Breakpoints(this.className,this.methodName,ws.getRange().get().begin.line,ws.getRange().get().end.line));
			}
		}
		super.visit(ws, collector);
	}
	
	@Override
	public void visit(SwitchEntry ses, List<Breakpoints> collector) {
		int uselessnodes=1;
		for(Node node : ses.getChildNodes()) {
			if (node instanceof BlockStmt && (node.getChildNodes().isEmpty() || (node.getChildNodes().size()==1 && node.getChildNodes().get(0) instanceof EmptyStmt)) || node instanceof EmptyStmt) {
				uselessnodes++;
			}
		}
		if(uselessnodes==ses.getChildNodes().size()) {
			collector.add(new Breakpoints(this.className,this.methodName,ses.getRange().get().begin.line,ses.getRange().get().end.line));
		}
		super.visit(ses, collector);
	}
}
