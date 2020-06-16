package core.lib.base;

import core.lib.network.model.BaseGson;

public interface BindViewHolder<I extends BaseGson> {
    void bind(I item);
}