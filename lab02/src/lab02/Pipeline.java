package lab02;

import java.util.ArrayList;
import java.util.List;

public class Pipeline<T, R> {

    private T value;
    private List<Transformer> transformers = new ArrayList<>();

    public Pipeline(T value) {
        this.value = value;
    }

    public <V> Pipeline<T, V> addTransformer(Transformer<R, V> transformer) {
        Pipeline<T, V> newPipeline = new Pipeline<>(value);
        newPipeline.transformers.addAll(this.transformers);
        newPipeline.transformers.add(transformer);
        return newPipeline;
    }

    public Object execute() {
        Object result = value;

        for (Transformer transformer : transformers) {
            result = transformer.transform(result);
        }

        return result;
    }
}
