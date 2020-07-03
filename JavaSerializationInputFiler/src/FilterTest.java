import java.io.ObjectInputFilter;
import java.io.ObjectInputFilter.FilterInfo;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class FilterTest {
	public static void testFilter(String name, int value) {
		Class<?> arrayClass = new int[0].getClass();
		
		String pattern = String.format("%s=%d;%s=%d", name, value, name, value -1);
		ObjectInputFilter filter = ObjectInputFilter.Config.createFilter(pattern);
		
		filter.checkInput(new FilterValues(arrayClass, value, value, value, value));
	}
	
	static class FilterValues implements ObjectInputFilter.FilterInfo {

		private final Class<?> classVar;
		private final long arrayLength;
		private final long depth;
		private final long references;
		private final long streamBytes;
		
		public FilterValues(Class<?> classVar, long arrayLength, long depth, 
						                       long references, long streamBytes) {
			this.classVar = classVar;
			this.arrayLength = arrayLength;
			this.depth = depth;
			this.references = references;
			this.streamBytes = streamBytes;
		}
		
		@Override
		public Class<?> serialClass() {
			return this.classVar;
		}

		@Override
		public long arrayLength() {
			return this.arrayLength;
		}

		@Override
		public long depth() {
			return this.depth;
		}

		@Override
		public long references() {
			return this.references;
		}

		@Override
		public long streamBytes() {
			return streamBytes;
		}
		
	}
	
	public ObjectInputFilter.Status checkInput(FilterInfo filter) {
		List<Class<?>> classes = new ArrayList<>();	
		if (filter.serialClass() != null) {
			if (filter.serialClass().getName().contains("$$Lambda$")) {
				classes.add(SerializedLambda.class);
				
			} else if (Proxy.isProxyClass(filter.serialClass())) {
				classes.add(Proxy.class);
			}
			else {
				classes.add(filter.getClass());
			}
		}
		
		return ObjectInputFilter.Status.ALLOWED;
	}
}