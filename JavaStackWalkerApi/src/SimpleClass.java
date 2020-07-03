import java.lang.StackWalker.Option;
import java.lang.StackWalker.StackFrame;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleClass {
	public void methodOne() {
		this.methodTwo();
	}
	
	public void methodTwo() {
		this.methodThree();
	}
	
	public void methodThree() {
		List<StackFrame> stackTrace = StackWalker.getInstance(Option.SHOW_HIDDEN_FRAMES)
			.walk(this::walkerDemo);
		
		stackTrace.stream()
		.peek(stack -> System.out.println(stack.toString())).collect(Collectors.toList());
		
		StackWalker.getInstance()
				.walk(this::walkerDemo).stream()
				.filter(frame -> frame.getClassName().contains("SimpleClass"))
				.findFirst()
				.map(f -> f.getClassName() + "." + f.getMethodName() + " -> " + f.getLineNumber())
				.orElse("Unknown caller ...");
	}
	
	public List<StackFrame> walkerDemo(Stream<StackFrame> stackFrameStream) {
		return stackFrameStream.collect(Collectors.toList());
	}
}