import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.TypeLiteral;
import org.graalvm.polyglot.Value;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TestGraalMap {
    static String JS_CODE = "(function myFun(){ return { listProperty: ['listValue']};})";

    @Test
    public void testList() {
        try (Context context = Context.create()) {
            Value value = context.eval("js", JS_CODE);
            Value result = value.execute();
            Map<String,Object> resultMap = result.as(Map.class);
            assertThat(resultMap).extractingByKey("listProperty").asList().containsExactly("listValue");
        }
    }
}
